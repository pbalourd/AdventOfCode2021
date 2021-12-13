import java.io.File

fun main() {
    val inputLines1 = File("src/main/kotlin/input13a.txt").inputStream()
    val lineList1 = mutableListOf<String>()
    inputLines1.bufferedReader().forEachLine { lineList1.add(it) }

    var points = mutableListOf<Pair<Int, Int>>()
    for (line in lineList1) {
        val tp = line.split(",").map{ it.toInt() }
        points.add(Pair(tp[0], tp[1]))
    }
    println(points.size)

    val inputLines2 = File("src/main/kotlin/input13b.txt").inputStream()
    val lineList2 = mutableListOf<String>()
    inputLines2.bufferedReader().forEachLine { lineList2.add(it)}

    val folds = mutableListOf<Pair<String, Int>>()
    for (line in lineList2) {
        val temp = line.split("=")
        val xy = temp[0].substringAfter("fold along ")
        folds.add(Pair(xy, temp[1].toInt()))
    }

    for (fold in folds) {
        var points2 = mutableSetOf<Pair<Int, Int>>()
        if (fold.first == "x") {
            for (point in points) {
                if (point.first < fold.second) {
                    if (!points2.contains(point)) points2.add(point)
                } else {
                    val p = Pair(2* fold.second - point.first, point.second)
                    if (!points2.contains(p)) points2.add(p)
                }
            }
        } else {
            for (point in points) {
                if (point.second < fold.second) {
                    if (!points2.contains(point)) points2.add(point)
                } else {
                    val p = Pair(point.first, 2* fold.second - point.second)
                    if (!points2.contains(p)) points2.add(p)
                }
            }
        }
        println(points2.size)
        points.clear()
        points.addAll(points2)
    }

    var sizeX = points.map { it.first }.maxByOrNull { it }
    var sizeY = points.map { it.second }.maxByOrNull { it }
    println(sizeX)
    println(sizeY)

    for (y in 0..sizeY!!) {
        for (x in 0..sizeX!!) {
            if (points.contains(Pair(x, y))) print("@")
            else print(" ")
        }
        println()
    }
}


