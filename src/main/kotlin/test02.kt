import java.io.File

// 2020 Day 3
fun main() {
    val inputFile = File("src/main/kotlin/test02.txt")
    val lineList = mutableListOf<String>()

    inputFile.bufferedReader().forEachLine { lineList.add(it) }

    var x = 0
    var count = 0L
    for (line in lineList) {
        if (line[x] == '#') count++
        x = (x + 3) % 31
    }
    println(count)

    x = 0
    var count1 = 0L
    for (line in lineList) {
        if (line[x] == '#') count1++
        x = (x + 1) % 31
    }
    x = 0
    var count2 = 0L
    for (line in lineList) {
        if (line[x] == '#') count2++
        x = (x + 3) % 31
    }
    x = 0
    var count3 = 0L
    for (line in lineList) {
        if (line[x] == '#') count3++
        x = (x + 5) % 31
    }
    x = 0
    var count4 = 0L
    for (line in lineList) {
        if (line[x] == '#') count4++
        x = (x + 7) % 31
    }
    x = 0
    var y = 0
    var count5 = 0L
    while (y <= lineList.lastIndex) {
        x = (x + 1) % 31
        if (lineList[y][x] == '#') count5++
        y += 2
    }

    println("$count1 $count2 $count3 $count4 $count5")
    println(count1 * count2 * count3 * count4 * count5)
}


