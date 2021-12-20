import java.io.File

const val border = 120

fun main() {
    val input = File("src/main/kotlin/input20.txt").readLines()

    val iea = input.takeWhile { it.isNotBlank() }.first()

    val initialArray = input.takeLastWhile { it.isNotBlank() }.map { it.chunked(1) }
    val l = initialArray[0].size

    val pointsArray = mutableListOf<MutableList<String>>()
    val inf = mutableListOf<String>()
    repeat(l + 2 * border) { inf.add(".") }
    repeat(border) { pointsArray.add(inf) }
    for (row in initialArray) {
        val r = mutableListOf<String>()
        repeat(border) { r.add(".") }
        r.addAll(row)
        repeat(border) { r.add(".") }
        pointsArray.add(r)
    }
    repeat(border) { pointsArray.add(inf) }
//    for (o in pointsArray) {for (s in o) print(s); println()}

    val m = pointsArray.size
    val n = pointsArray[0].size

    repeat(50) {
        val temp = mutableListOf<MutableList<String>>()
        for (i in pointsArray) {
            val g = mutableListOf<String>()
            for (j in i) {
                g.add(j)
            }
            temp.add(g)
        }
        var cor = 1
        for (i in 1..m - 2) {
            for (j in 1..n - 2) {
                val square = mutableListOf<String>()
                square.add(pointsArray[i - 1][j - 1])
                square.add(pointsArray[i - 1][j])
                square.add(pointsArray[i - 1][j + 1])
                square.add(pointsArray[i][j - 1])
                square.add(pointsArray[i][j])
                square.add(pointsArray[i][j + 1])
                square.add(pointsArray[i + 1][j - 1])
                square.add(pointsArray[i + 1][j])
                square.add(pointsArray[i + 1][j + 1])
                var index = 0
                var d = 256
                for (t in square) {
                    if (t == "#") index += d
                    d /= 2
                }
                temp[i][j] = iea[index].toString()
            }
            if (it % 2 == 1) {
                temp[i][cor] = "."
                temp[i][n - cor - 1] = "."
            }
        }
        cor++
        pointsArray.clear()
        for (i in temp) {
            val g = mutableListOf<String>()
            for (j in i) {
                g.add(j)
            }
            pointsArray.add(g)
        }
    }
    var count = 0
    for ((in1, i) in pointsArray.withIndex())
        for ((in2, j) in i.withIndex())
            if (j == "#" && in2 > 1 && in2 < m - 2) count++
    for (o in pointsArray) {for (s in o) print(s); println()}
    println(count)
}


