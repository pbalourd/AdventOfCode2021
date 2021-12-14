import java.io.File

fun main() {
    val input = File("src/main/kotlin/input14.txt").readLines()

    val inputLines1 = input
        .takeWhile { it.isNotBlank() }

    var polymer = inputLines1[0].split("").filter { it != "" }

    val rules = input
        .takeLastWhile { it.isNotBlank() }
        .map {
            val (pair, add) = it.split(" -> ")
            val (f, s) = pair.split("").filter { it != "" }
            Pair(Pair(f, s), add)
        }

    val polymerPairs = mutableMapOf<Pair<String, String>, Long>()

    for (rule in rules) {
        polymerPairs[rule.first] = 0
    }

    for (index in 0 until polymer.lastIndex) {
        val a = Pair(polymer[index], polymer[index + 1])
        val num = polymerPairs[a]!! + 1
        polymerPairs[a] = num
    }

    println(polymerPairs)
    repeat(40) {
        val p2 = mutableMapOf<Pair<String, String>, Long>()
        p2.putAll(polymerPairs)
        for (key in p2.keys) p2[key] = 0L
        for((key, value) in polymerPairs) {
            if (value != 0L) {
                val add = rules.find { it.first == key }!!.second
                var a = Pair(key.first, add)
                var e = p2[a]!! + value
                p2[a] = e
                a = Pair(add, key.second)
                e = p2[a]!! + value
                p2[a] = e
            }
        }
        polymerPairs.clear()
        polymerPairs.putAll(p2)
    }

    val counts = MutableList<Long>(10) { 0 }

    for (p in polymerPairs) {
        val a = p.key.first
        val value = p.value
        when (a) {
            "B" -> counts[0] += value
            "C" -> counts[1] += value
            "F" -> counts[2] += value
            "H" -> counts[3] += value
            "K" -> counts[4] += value
            "N" -> counts[5] += value
            "O" -> counts[6] += value
            "P" -> counts[7] += value
            "S" -> counts[8] += value
            "V" -> counts[9] += value
        }
    }
    counts[1]++
    println(counts)
    println(counts.maxOf { it } - counts.minOf { it })
}


