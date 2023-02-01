import java.io.File

data class Position(var y: Int , var x: Int)

fun main() {
    val area = File("src/main/kotlin/input25.txt").readLines()
        .map { it.toMutableList() }
    val height = area.size
    val width = area[0].size

    val eastCucumbers = mutableSetOf<Position>()
    val southCucumbers = mutableSetOf<Position>()
    area.forEachIndexed { i, chars ->
            chars.forEachIndexed { j, ch ->
                if (ch == '>') eastCucumbers.add(Position(i, j))
                else if (ch == 'v') southCucumbers.add(Position(i, j))
            }
        }

    var count = 0
    while(true) {
        var anyMovedEast = false
        var anyMovedSouth = false
        for (i in area.indices) {
            val movedEast = mutableSetOf<Position>()
            for (j in area[0].indices) {
                if (Position(i, j) in eastCucumbers &&
                    Position(i, (j + 1) % width) !in eastCucumbers &&
                    Position(i, (j + 1) % width) !in southCucumbers
                ) {
                    movedEast.add(Position(i, j))
                }
            }
            if (movedEast.isNotEmpty()) anyMovedEast = true
            movedEast.forEach {
                eastCucumbers.remove(it)
                eastCucumbers.add(Position(it.y, (it.x + 1) % width))
            }
        }

        for (j in area[0].indices) {
            val movedSouth = mutableSetOf<Position>()
            for (i in area.indices) {
                if (Position(i, j) in southCucumbers &&
                    Position((i + 1) % height, j) !in southCucumbers &&
                    Position((i + 1) % height, j) !in eastCucumbers
                ) {
                    movedSouth.add(Position(i, j))
                }
            }
            if (movedSouth.isNotEmpty()) anyMovedSouth = true
            movedSouth.forEach {
                southCucumbers.remove(it)
                southCucumbers.add(Position((it.y + 1) % height, it.x))
            }
        }
        count++
        if (!anyMovedEast && !anyMovedSouth) break
    }
    println(count)
    // Solution 278

    fun printArea() {
        for (i in area.indices) {
            for (j in area[0].indices) {
                if (Position(i, j) in eastCucumbers) print('>')
                else if (Position(i, j) in southCucumbers) print('v')
                else print('.')
            }
            println()
        }
    }

//    printArea()
}