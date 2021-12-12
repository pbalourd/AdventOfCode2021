import java.io.File

fun main() {
    val inputFile = File("src/main/kotlin/input04.txt")
    val lineList = mutableListOf<String>()

    inputFile.bufferedReader().forEachLine { lineList.add(it) }

    val numsStr = lineList[0].split(",")
    val numsStrSorted = numsStr.sorted()

    val nums = lineList[0].split(",").map{ it.toInt() }
    val numsSorted = nums.sorted()

    for (i in nums) print("$i ")
    println()
    println(nums.size)
    for (i in numsSorted) print("$i ")
    println()

    val tablesStrList = mutableListOf<String>()

    var index = 1
    while (index < lineList.lastIndex) {
        index++
        val tableStr = StringBuilder()
        repeat(5) {
            tableStr.appendLine(lineList[index])
            index++
        }
        tablesStrList.add(tableStr.toString())
    }

    val tables = mutableListOf<List<List<Int>>>()

    for (tableStr in tablesStrList) {
        val lines = tableStr.trim().split("\n")

        val rows = mutableListOf<List<Int>>()
        for (line in lines) {
            val row = line.trim().split("\\s+".toRegex()).map { it.toInt() }
            rows.add(row)
        }
        tables.add(rows)
    }

    println(tables.size)

    val completedTables = mutableListOf<Pair<Int, Int>>()
    for ((numIndex, num) in nums.withIndex()) {
        val wn = nums.filterIndexed { index, num -> index <= numIndex }
        for ((tableIndex, table) in tables.withIndex()) {
            if (completedTables.map { it.first } .contains(tableIndex)) continue

            for (j in 0..4)
                if (wn.containsAll(tables[tableIndex][j])) {
                    completedTables.add(Pair(tableIndex, numIndex))
                }
            for (j in 0..4) {
                val t = mutableListOf<Int>()
                for (l in 0..4) t.add(table[l][j])
                if (wn.containsAll(t)) {
                    completedTables.add(Pair(tableIndex, numIndex))
                }
            }
        }
    }

    println(completedTables[0])
    println(tables[completedTables[0].first])
    println(completedTables[99])

    val firstTable = tables[completedTables[0].first].flatten()
    println(firstTable)
    val firstNumIndex = completedTables[0].second
    var wn = nums.filterIndexed { index, _ -> index <= firstNumIndex }
    var count = 0
    for (d in firstTable) {
        if (!wn.contains(d)) count += d
    }
    val firstLastShown = nums[firstNumIndex]
    println(count * firstLastShown)

    val lastTable = tables[completedTables[99].first].flatten()
    println(lastTable)
    val lastNumIndex = completedTables[99].second
    wn = nums.filterIndexed { index, _ -> index <= lastNumIndex }
    count = 0
    for (d in lastTable) {
        if (!wn.contains(d)) count += d
    }

    val lastLastShown = nums[lastNumIndex]
    println(count * lastLastShown)
}


