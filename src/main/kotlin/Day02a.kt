import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("src/main/kotlin/input02.txt").inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    var forward = 0
    var depth = 0

    for (line in lineList) {
        val move = line.trim().split(" ")
        if (move[0].trim() == "forward") forward += move[1].toInt()
        else if (move[0].trim() == "down") depth += move[1].toInt()
        else depth -= move[1].toInt()
    }
    println("${forward * depth}")


}


