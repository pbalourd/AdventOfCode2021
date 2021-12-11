import java.io.File

fun main() {
    val inputLines = File("src/main/kotlin/input10.txt").inputStream()
    val lineList = mutableListOf<String>()
    inputLines.bufferedReader().forEachLine { lineList.add(it) }

    val lifoSize = lineList.map{ it.length }.maxOf { it }
    val incompleteLines = mutableListOf<String>()

    var errorScore = 0
    for (line in lineList) {
        val lifo = CharArray(lifoSize)
        var lifoPointer = -1
        var isCorrupted = false
        for (ch in line) {
            if ( ch in listOf('{', '[', '(', '<') ) {
                lifoPointer++
                lifo[lifoPointer] = ch
            } else {
                if ( (ch == '}' && lifo[lifoPointer] == '{') ||
                    (ch == ']' && lifo[lifoPointer] == '[') ||
                    (ch == ')' && lifo[lifoPointer] == '(') ||
                    (ch == '>' && lifo[lifoPointer] == '<')
                ) lifoPointer--
                else {
                    when (ch) {
                        ')' -> errorScore += 3
                        ']' -> errorScore += 57
                        '}' -> errorScore += 1197
                        '>' -> errorScore += 25137
                    }
                    isCorrupted = true
                    break
                }
            }
        }
        if (!isCorrupted) incompleteLines.add(line)
    }
    println(errorScore)
    // Solution 442131


    var totalScores = MutableList<Long>(incompleteLines.size) { 0 }
    for ((i, line) in incompleteLines.withIndex()) {
        val lifo = CharArray(lifoSize)
        var lifoPointer = -1
        var ts = 0L
        for (ch in line) {
            if ( ch in listOf('{', '[', '(', '<') ) {
                lifoPointer++
                lifo[lifoPointer] = ch
            } else {
                if ( (ch == '}' && lifo[lifoPointer] == '{') ||
                    (ch == ']' && lifo[lifoPointer] == '[') ||
                    (ch == ')' && lifo[lifoPointer] == '(') ||
                    (ch == '>' && lifo[lifoPointer] == '<')
                ) lifoPointer--
                else {
                    println("Error")
                    break
                }
            }
        }
        for (index in lifoPointer downTo 0) {
            ts *= 5L
            ts += when (lifo[index]) {
                '(' -> 1L
                '[' -> 2L
                '{' -> 3L
                else -> 4L
            }
        }
        totalScores[i] = ts
    }
    totalScores.sort()
    println(totalScores[incompleteLines.size / 2])
    // Solution 3646451424

}


