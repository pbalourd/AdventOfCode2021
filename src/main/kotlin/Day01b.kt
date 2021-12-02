import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("src/main/kotlin/input01.txt").inputStream()
    val lineList = mutableListOf<Int>()

    inputStream.bufferedReader().forEachLine { lineList.add(it.toInt()) }

    var count = 0
    for (index in 3..lineList.lastIndex) {
        if (lineList[index] + lineList[index - 1] + lineList[index - 2] > lineList[index - 1] + lineList[index - 2] + lineList[index - 3])
            count++
    }

    println(count)
}


