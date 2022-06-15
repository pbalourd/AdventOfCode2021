import java.io.File

fun main() {
    val input = File("src/main/kotlin/input14.txt").readLines()

    val inputLines1 = input
        .takeWhile { it.isNotBlank() }

    var polymer = inputLines1[0].split("").filter { it != "" }
    println(polymer)

    val rules = input
        .takeLastWhile { it.isNotBlank() }
        .map {
            val (pair, add) = it.split(" -> ")
            val (f, s) = pair.split("").filter { it != "" }
            Pair(Pair(f,s), add)
        }

//    println(rules)

    val counts = MutableList<Long>(10) { 0 }

    repeat(10) {
        val polymer2 = mutableListOf<String>()
        for (index in 0 until polymer.lastIndex) {
            polymer2.add(polymer[index])
            val add = rules.filter { it.first.first == polymer[index] && it.first.second == polymer[index + 1] }
                .first()
                .second
            polymer2.add(add)
        }
        polymer2.add(polymer[polymer.lastIndex])
//        println(polymer2)
        polymer = polymer2
    }

    for (element in polymer) {
        when (element) {
            "B" -> counts[0]++
            "C" -> counts[1]++
            "F" -> counts[2]++
            "H" -> counts[3]++
            "K" -> counts[4]++
            "N" -> counts[5]++
            "O" -> counts[6]++
            "P" -> counts[7]++
            "S" -> counts[8]++
            "V" -> counts[9]++
        }
    }

    println(counts)
    println(counts.maxOf { it } - counts.minOf { it })

//    val elementsMax = polymer.groupingBy { it }.eachCount()
//    println(elementsMax)

}


