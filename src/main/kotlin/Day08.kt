import java.io.File
import java.io.InputStream

fun main() {
    val inputLines = File("src/main/kotlin/input08.txt").inputStream()
    val lineList = mutableListOf<String>()
    inputLines.bufferedReader().forEachLine { lineList.add(it) }

    var count = 0
    for (line in lineList) {
        val segments = line.split("|")
        val numOf1478 = segments[1].split(" ")
            .count { it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7 }
        count += numOf1478
    }
    println(count)
    // Solution 470

    count = 0
    for (line in lineList) {
        val segments = line.split("|")
        val digits = MutableList<String>(10) { "" }
        val series =  segments[0].split(" ")
        for (str in series) {
            when (str.length) {
                2 -> digits[1] = str
                4 -> digits[4] = str
                3 -> digits[7] = str
                7 -> digits[8] = str
            }
        }

//        val aaa = digits[7].split("").filter { it != "" }.toMutableList()
//        aaa.removeAll(digits[1].split("").filter { it != "" })
        val four = digits[4].split("").filter { it != "" }
        val seven = digits[7].split("").filter { it != "" }
        for (str in series) {
            val temp = str.split("").filter { it != "" }
            if (str.length == 6 && temp.containsAll(four)) digits[9] = str
            else if (str.length == 5 && temp.containsAll(seven)) digits[3] = str
        }

        val nine = digits[9].split("").filter { it != "" }
        for (str in series) {
            val temp = str.split("").filter { it != "" }
            if (str.length == 5 && str != digits[3] && nine.containsAll(temp)) digits[5] = str
        }

        val five = digits[5].split("").filter { it != "" }
        for (str in series) {
            val temp = str.split("").filter { it != "" }
            if (str.length == 5 && str != digits[3] && str != digits[5]) digits[2] = str
            else if (str.length == 6  && str != digits[9] && temp.containsAll(five)) digits[6] = str
        }

        for (str in series) {
            if (str.length == 6 && str != digits[6] && str != digits[9]) digits[0] = str
        }

        val numbers =  segments[1].trim().split(" ")
        var sum = 0
        for ((index1, str) in numbers.withIndex()) {
            val temp1 = str.split("").filter { it != "" }
            for ((index2, digit) in digits.withIndex()) {
                val temp2 = digit.split("").filter { it != "" }
                if (temp1.containsAll(temp2) && temp1.size == temp2.size) {
                    var dec = index2
                    when (index1) {
                        0 -> dec *= 1000
                        1 -> dec *= 100
                        2 -> dec *= 10
                    }
                    sum += dec
                }
            }
        }
        println(sum)
        count += sum
    }
    println(count)
    // Solution 989396

}


