import java.io.File
import kotlin.math.abs

fun main() {
    val inputFile = File("src/main/kotlin/input07.txt")
    val crabList = inputFile.readText().split(",").map { it.toInt() }

    var min = crabList.minOrNull()!!
    var max = crabList.maxOrNull()!!
    println(min)
    println(max)

    var minFuel = max * crabList.size
    var position = 0
    for (target in min..max) {
        val countFuel = crabList.map { abs(target - it) }.sum()
        if (countFuel < minFuel) {
            minFuel = countFuel
            position = target
        }
    }
    println(position)
    println(minFuel)
    // Solution 328318

    minFuel = (1..max).sum() * crabList.size
    position = 0
    for (target in min..max) {
        val countFuel = crabList.map { abs(target - it) }.map{ (it * (it + 1)) / 2 }.sum()
        if (countFuel < minFuel) {
            minFuel = countFuel
            position = target
        }
    }
    println(position)
    println(minFuel)
    // Solution 89791146

}

