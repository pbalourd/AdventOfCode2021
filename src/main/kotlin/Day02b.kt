import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("src/main/kotlin/input02.txt").inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    var forward = 0
    var depth = 0
    var aim = 0

    for (line in lineList) {
        val move = line.trim().split(" ")
        if (move[0].trim() == "forward") {
            val x = move[1].toInt()
            forward += x
            depth += aim * x
        }
        else if (move[0].trim() == "down") aim += move[1].toInt()
        else aim -= move[1].toInt()
    }

    println("${forward * depth}")


}


