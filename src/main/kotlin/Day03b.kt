import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("src/main/kotlin/input03.txt").inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    var oxygenList = lineList.toList()
    var position = 0
    do {
        val total = oxygenList.size
        var count = 0
        for (line in oxygenList) if (line[position] == '1') count++
        val bit = if (count >= total / 2) '1'
        else '0'

        oxygenList = oxygenList.filter { it[position] == bit }

        position ++
    } while (oxygenList.size > 1)
    println(oxygenList)

    var oxygen = 0
    var value = 2048
    for (ch in oxygenList[0]) {
        if (ch == '1') oxygen += value
        value /= 2
    }
    println(oxygen)


    var coList = lineList.toList()
    position = 0
    do {
        val total = coList.size
        var count = 0
        for (line in coList) if (line[position] == '0') count++
        val bit = if (count <= total / 2) '0'
        else '1'

        coList = coList.filter { it[position] == bit }

        position ++
    } while (coList.size > 1)
    println(coList)

    var co = 0
    value = 2048
    for (ch in coList[0]) {
        if (ch == '1') co += value
        value /= 2
    }
    println(co)

    println(oxygen * co)

//    val result = mutableListOf<Int>()
//    val total = lineList.size
//    for (i in 0..11) {
//        var count = 0
//        for (line in lineList) {
//            if (line[i] == '1') count++
//        }
//        if (count > total / 2) result.add(1)
//        else result.add(0)
//    }
//    println(result)
//
//    var gamma = 0
//    var epsilon = 0
//    var value = 2048
//    for (k in result) {
//        gamma += k * value
//        epsilon += (1 - k) * value
//        value /= 2
//    }
//
//    println(gamma * epsilon)

}


