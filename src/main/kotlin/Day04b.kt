import java.io.File
import java.io.InputStream
import kotlin.system.exitProcess

fun main() {
    val inputStream: InputStream = File("src/main/kotlin/input04.txt").inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    val nums = lineList[0].split(",")
    for (i in nums) print("$i ")
    println()

    var last = mutableListOf<MutableList<String>>()
    var lastwn = mutableListOf<String>()

    val numsOfBoard = mutableSetOf<Int>()
    for (d in 0..nums.lastIndex) {

        var numOfBoard = 0
        var ln = 2
        label@ while (ln < lineList.lastIndex) {
//            if (numsOfBoard.contains(numOfBoard)) {
//                ln +=6
//                break@label
//            }
            val table = mutableListOf<MutableList<String>>()

            for (i in 0..4) {
                table.add( lineList[ln].split(" ").filter{ it != " " && it != ""}.toMutableList() )
                ln++
            }

            val wn = mutableListOf<String>()
            for (j in 0..d) {
                wn.add(nums[j])
            }
            for (j in 0..4) {
                val t = mutableListOf<String>()
                for (l in 0..4) t.add(table[l][j])
                if (wn.containsAll(t)) {
//                    println("Column")
//                    println(j)
//                    println(wn)
//                    println(table)
//                    println(t)
                    if ( !numsOfBoard.contains(numOfBoard) ) {
                            last = table
                            lastwn = wn
                            numsOfBoard.add(numOfBoard)
                    }
                }
                if (wn.containsAll(table[j])) {
//                    println("Row")
//                    println(j)
//                    println(wn)
//                    println(wn.sorted())
//                    println(table)
                    if ( !numsOfBoard.contains(numOfBoard) ) {
                        last = table
                        lastwn = wn
                        numsOfBoard.add(numOfBoard)
                    }
                }

            }

            ln++
            numOfBoard++
        }

//        if (numsOfBoard.size == 100) break
    }
    println(lastwn)
    println(last)
    println(numsOfBoard.size)

}


