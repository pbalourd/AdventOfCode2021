import java.io.File

fun main() {
    val inputFile = File("src/main/kotlin/input06.txt")
    val fishList = inputFile.readText().split(",").map { it.toLong() }.toMutableList()

    for (i in 0..79) {
        val newFiches = mutableListOf<Long>()
        for (index in fishList.indices) {
            if (fishList[index] == 0L) {
                fishList[index] = 6L
                newFiches.add(8L)
            } else fishList[index]--
        }
        fishList.addAll(newFiches)
    }
    println(fishList.size)
    // Solution 345793

    val fishList2 = inputFile.readText().split(",")
    val fishGroups = Array<Long>(9) { 0 }
    for (fish in fishList2) fishGroups[fish.toInt()]++
    repeat(256) {
        val zero = fishGroups[0]
        for (i in 1..8)
            fishGroups[i - 1] = fishGroups[i]
        fishGroups[8] = zero
        fishGroups[6] += zero
    }
    var count = 0L
    for (i in 0..8) count += fishGroups[i]
    println(count)
    // Solution 1572643095893
}

