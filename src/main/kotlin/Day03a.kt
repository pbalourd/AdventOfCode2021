import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("src/main/kotlin/input03.txt").inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    val result = mutableListOf<Int>()
    val total = lineList.size
    for (i in 0..11) {
        var count = 0
        for (line in lineList) {
            if (line[i] == '1') count++
        }
        if (count > total / 2) result.add(1)
        else result.add(0)
    }
    println(result)

    var gamma = 0
    var epsilon = 0
    var value = 2048
    for (k in result) {
        gamma += k * value
        epsilon += (1 - k) * value
        value /= 2
    }

    println(gamma * epsilon)

}


