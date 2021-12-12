import java.io.File

// 2020 Day 2
fun main() {
    val inputFile = File("src/main/kotlin/test01.txt")
    val lineList = mutableListOf<String>()

    inputFile.bufferedReader().forEachLine { lineList.add(it) }

    var count = 0
    for (line in lineList) {
        val data = line.split("-", " ", ": ")
        val min = data[0].toInt()
        val max = data[1].toInt()
        val ch = data[2][0]
        val num = data[3].count { it == ch }
        if (num >= min && num <= max) count++
    }

    println(count)

    count = 0
    for (line in lineList) {
        val data = line.split("-", " ", ": ")
        val first = data[0].toInt()
        val second = data[1].toInt()
        val ch = data[2][0]
        val pass = data[3]
        if ( (pass[first - 1] == ch) xor (pass[second - 1] == ch) ) count++
    }

    println(count)

}