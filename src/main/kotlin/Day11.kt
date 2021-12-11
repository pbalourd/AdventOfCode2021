import java.io.File

fun main() {
    val inputLines = File("src/main/kotlin/input11.txt").inputStream()
    val octopus = mutableListOf<MutableList<Int>>()
    inputLines.bufferedReader().forEachLine { octopus.add(it.toList().map { it.code - 48 }.toMutableList()) }

    var sum = 0
    var allFlushed = false
    var times = 0
    while (!allFlushed) {
        for (i in 0..9) {
            for (j in 0..9) {
                octopus[i][j]++
            }
        }

        do {
            var flushed = false
            for (i in 0..9) {
                for (j in 0..9) {
                    if (octopus[i][j] > 9) {
                        flushed = true
                        octopus[i][j] = 0
                        if (i > 0 && octopus[i - 1][j] != 0) octopus[i - 1][j]++
                        if (i < 9 && octopus[i + 1][j] != 0) octopus[i + 1][j]++
                        if (j > 0 && octopus[i][j - 1] != 0) octopus[i][j - 1]++
                        if (j < 9 && octopus[i][j + 1] != 0) octopus[i][j + 1]++
                        if (i > 0 && j > 0 && octopus[i - 1][j - 1] != 0) octopus[i - 1][j - 1]++
                        if (i > 0 && j < 9 && octopus[i - 1][j + 1] != 0) octopus[i - 1][j + 1]++
                        if (i < 9 && j > 0 && octopus[i + 1][j - 1] != 0) octopus[i + 1][j - 1]++
                        if (i < 9 && j < 9 && octopus[i + 1][j + 1] != 0) octopus[i + 1][j + 1]++
                    }
                }
            }
        } while (flushed)

        for (i in 0..9) {
            for (j in 0..9) {
                if (octopus[i][j] > 9) octopus[i][j] = 0
            }
        }

        sum += octopus.flatten().count { it == 0 }
        for (k in 0..9) println(octopus[k].joinToString(""))
        println(sum)
        println()

        allFlushed = true
        for (i in 0..9) {
            for (j in 0..9) {
                if (octopus[i][j] != 0) allFlushed = false
            }
        }
        times++
        println(times)
    }

}