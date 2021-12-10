import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun main() {
    val inputLines = File("src/main/kotlin/input09.txt").inputStream()
    val lineList = mutableListOf<String>()
    inputLines.bufferedReader().forEachLine { lineList.add(it) }

    val lows = mutableListOf<Pair<Int, Int>>()
    var sum = 0
    for ((y, line) in lineList.withIndex()) {
        for (x in line.indices) {
            if (x != 0) {
                if (line[x] >= line[x - 1]) continue
            }
            if (x != line.lastIndex) {
                if (line[x] >= line[x + 1]) continue
            }
            if (y != 0) {
                if (line[x] >= lineList[y - 1][x]) continue
            }
            if (y != lineList.lastIndex) {
                if (line[x] >= lineList[y + 1][x]) continue
            }
            sum += line[x].code - 48 + 1
            lows.add(Pair(y, x))
        }
    }
    println(sum)
    // Solution 562

    val firstThree = MutableList<Int>(3) { 1 }
    for (point in lows) {
        val basin = mutableSetOf<Pair<Int, Int>>()

        getAdjacent(point, basin, lineList)

        if (basin.size > firstThree[2]) firstThree[2] = basin.size
        if (basin.size > firstThree[1]) {
            firstThree[2] = firstThree[1]
            firstThree[1] = basin.size
        }
        if (basin.size > firstThree[0]) {
            firstThree[1] = firstThree[0]
            firstThree[0] = basin.size
        }
    }
    for (k in firstThree) println(k)
    println(firstThree[0] * firstThree[1] * firstThree[2])
    // Solution 1076922

    val image = BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB)
    for (y in 0..99) {
        for (x in 0..99) {
            var digit = lineList[y][x].code - 48
            val temp = (digit * 255) / 9
            val color = if (digit == 9) Color(0,128, 255)
                else Color(temp, temp, temp)
            image.setRGB(x, y, color.rgb)
        }
    }
    val outputFile = File("imageDay9.png")
    ImageIO.write(image, "png", outputFile)
}

fun getAdjacent(point: Pair<Int, Int>, basin: MutableSet<Pair<Int, Int>>, lineList: MutableList<String>) {
//    println(point)
    if (point !in basin) basin.add(point)
    if (point.first > 0 && lineList[point.first - 1][point.second] != '9' && Pair(point.first - 1, point.second) !in basin )
        getAdjacent(Pair(point.first - 1, point.second), basin, lineList)
    if (point.first < lineList.lastIndex && lineList[point.first + 1][point.second] != '9' && Pair(point.first + 1, point.second) !in basin )
        getAdjacent(Pair(point.first + 1, point.second), basin, lineList)
    if (point.second > 0 && lineList[point.first][point.second - 1] != '9' && Pair(point.first, point.second - 1) !in basin )
        getAdjacent(Pair(point.first, point.second - 1), basin, lineList)
    if (point.second < lineList[point.first].lastIndex && lineList[point.first][point.second + 1] != '9' && Pair(point.first, point.second + 1) !in basin )
        getAdjacent(Pair(point.first, point.second + 1), basin, lineList)
}
