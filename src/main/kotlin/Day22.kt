import java.io.File

val firstItems = 20

fun main() {
    val input = File("src/main/kotlin/input22.txt").readLines()
    val first20 = input
        .take(firstItems)
        .map { it.replace("on ", "1") }
        .map { it.replace("off ", "0") }
        .map { it.replace("(x=|,y=|,z=|\\.\\.)".toRegex(), " ") }
        .map { it.split(" ").map { it.toInt() } }.toList()

    val cubes = Array(101) { Array(101) { BooleanArray(101) } }

    for (cube in first20) {
        for (x in cube[1]..cube[2]) {
            for (y in cube[3]..cube[4]) {
                for (z in cube[5]..cube[6]) {
                    cubes[x + 50][y + 50][z + 50] = cube[0] == 1
                }
            }
        }
    }
    var count = 0L
    for (x in 0..100)
        for (y in 0..100)
            for (z in 0..100)
                if (cubes[x][y][z]) count++

    println(count)
    // Solution 647076

//    val allCubes = input
////        .asSequence()
//        .map { it.replace("on ", "1") }
//        .map { it.replace("off ", "0") }
//        .map { it.replace("(x=|,y=|,z=|\\.\\.)".toRegex(), " ") }
//        .map { it.split(" ").map { it.toInt() } }
//
//    val minX = allCubes.map { it[1] }.minOf { it }
//    val maxX = allCubes.map { it[2] }.maxOf { it }
//    val minY = allCubes.map { it[3] }.minOf { it }
//    val maxY = allCubes.map { it[4] }.maxOf { it }
//    val minZ = allCubes.map { it[5] }.minOf { it }
//    val maxZ = allCubes.map { it[6] }.maxOf { it }
//
//    val cubes2 = Array(maxX- minX + 1) { Array(maxY- minY + 1) { BooleanArray(maxZ- minZ + 1) } }
//
//    for (cube in allCubes) {
//        for (x in cube[1]..cube[2]) {
//            for (y in cube[3]..cube[4]) {
//                for (z in cube[5]..cube[6]) {
//                    cubes2[x - minX][y - minY][z - minZ] = cube[0] == 1
//                }
//            }
//        }
//    }

}


