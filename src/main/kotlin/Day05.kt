import java.io.File
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.max

fun main() {
    val inputFile = File("src/main/kotlin/input05.txt")
    val lineList = mutableListOf<String>()

    inputFile.bufferedReader().forEachLine { lineList.add(it) }

    val area = Array(1000) { IntArray(1000) { 0 } }

    for (line in lineList) {
        val data = line.split(",", " -> ").map { it.toInt() }

        if (data[0] == data[2]) {
            for (y in min(data[1], data[3])..max(data[1], data[3])) area[data[0]][y]++
        } else if (data[1] == data[3]) {
            for (x in min(data[0], data[2])..max(data[0], data[2])) area[x][data[1]]++
        } else {
            val diff = abs(data[2] - data[0]) + 1
            val diffX = (data[2] - data[0]) / abs(data[2] - data[0])
            val diffY = (data[3] - data[1]) / abs(data[3] - data[1])
            println("$diff $diffX $diffY")
            repeat(diff) {
                area[data[0] + diffX * it][data[1] + diffY * it]++
            }
        }
    }

    var count = 0
    for (i in 0..999)
        for (j in 0..999)
            if (area[i][j] > 1) count++

    println(count)

}