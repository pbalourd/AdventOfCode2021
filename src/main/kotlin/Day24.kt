import java.io.File

fun main() {
    val nums = File("src/main/kotlin/input24.txt").readLines()
        .chunked(18)
        .map { listOf(
            it[4].split(" ").last().toInt(),
            it[5].split(" ").last().toInt(),
            it[15].split(" ").last().toInt(),
        ) }

    val stack = mutableListOf<Pair<Int, List<Int>>>()
    val maxN = MutableList(nums.size) { 0 }
    val minN = MutableList(nums.size) { 0 }
    for ((i, ns) in nums.withIndex()) {
        if (ns[0] == 1) stack.add(Pair(i, ns))
        else {  // ns[0] == 26
            val (j, k) = stack.removeLast()
            maxN[i] = (1..9).filter { it - ns[1] - k[2] <= 9 }.maxOrNull()!!
            maxN[j] = maxN[i] - ns[1] - k[2]
            minN[i] = (1..9).filter { it - ns[1] - k[2] >= 1 }.minOrNull()!!
            minN[j] = minN[i] - ns[1] - k[2]
        }
    }

    println(maxN.joinToString(""))
    // Solution 94992992796199

    println(minN.joinToString(""))
    // Solution 11931881141161

/*
    // Calculate z value
    fun calc(w: Long, z: Long, index: Int): Long {
        return if (z % 26 + nums[index][1] != w) (z / nums[index][0] * 26 + w + nums[index][2])
        else z / nums[index][0]
    }

    val m = maxN.joinToString("").toLong()
    if (m % 1000000000 == 0L) println(m)
    val k = m.toString().toList().map { it.toString().toLong() }
    var z = 0L
    for ((i, w) in k.withIndex()) {
        z = calc(w, z, i)
    }
    println(z)
*/

}