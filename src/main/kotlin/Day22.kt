import java.io.File
import kotlin.math.max
import kotlin.math.min

const val firstItems = 20

data class Cube(val onOff: Boolean, val x1: Int, val x2: Int, val y1: Int, val y2: Int,val z1: Int, val z2: Int)

fun main() {
    val input = File("src/main/kotlin/input22.txt").readLines()
    val allCubes = input
        .asSequence()
        .map { it.replace("on ", "1") }
        .map { it.replace("off ", "0") }
        .map { it.replace("(x=|,y=|,z=|\\.\\.)".toRegex(), " ") }
        .map { it.split(" ").map { item -> item.toInt() } }
        .map { Cube(it[0] == 1, it[1], it[2], it[3], it[4], it[5], it[6]) }
        .toList()

    println(calculateCubesOn(allCubes.take(firstItems)))
    // Solution 647076

    println(calculateCubesOn(allCubes))
    // Solution 1233304599156793
}

private fun calculateCubesOn(allCubes: List<Cube>): Long {
    val cubeList = mutableListOf<Cube>()

    for (cube in allCubes) {
        val newCubes = mutableListOf<Cube>()
        for (cb in cubeList) {
            if (doCubesIntersect(cube, cb))
                newCubes.add(
                    Cube(!cb.onOff,
                        max(cube.x1, cb.x1), min(cube.x2, cb.x2),
                        max(cube.y1, cb.y1), min(cube.y2, cb.y2),
                        max(cube.z1, cb.z1), min(cube.z2, cb.z2))
                )
        }
        cubeList.addAll(newCubes)
        if (cube.onOff) cubeList.add(cube)
    }

    var count = 0L
    for (c in cubeList) {
        if (c.onOff) {
            count += (c.x2 - c.x1 + 1).toLong() * (c.y2 - c.y1 + 1) * (c.z2 - c.z1 + 1)
        } else count -= (c.x2 - c.x1 + 1).toLong() * (c.y2 - c.y1 + 1) * (c.z2 - c.z1 + 1)
    }
    return count
}

fun doCubesIntersect(a: Cube, b: Cube): Boolean {
    return a.x2 >= b.x1 && b.x2 >= a.x1 &&
            a.y2 >= b.y1 && b.y2 >= a.y1 &&
            a.z2 >= b.z1 && b.z2 >= a.z1
}
