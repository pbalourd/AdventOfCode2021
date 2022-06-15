// https://github.com/elizarov/AdventOfCode2021/blob/main/src/Day15_2_fast.kt

import java.util.*

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

fun main() {
    val dayId = "15"
    val a0 = readInput("Day${dayId}").map { it.toCharArray().map { it.digitToInt() } }
    val n0 = a0.size
    val m0 = a0[0].size
    val n = 5 * n0
    val m = 5 * m0
    val a = Array(n) { i -> IntArray(m) { j ->
        val k = i / n0 + j / m0
        (a0[i % n0][j % m0] + k - 1) % 9 + 1
    } }
    val d = Array(n) { IntArray(m) { Int.MAX_VALUE } }
    data class Pos(val i: Int, val j: Int, val x: Int)
    val v = Array(n) { BooleanArray(m) }
    val q = PriorityQueue(compareBy(Pos::x))
    fun relax(i: Int, j: Int, x: Int) {
        if (i !in 0 until n || j !in 0 until m || v[i][j]) return
        val xx = x + a[i][j]
        if (xx < d[i][j]) {
            d[i][j] = xx
            q += Pos(i, j, xx)
        }
    }
    d[0][0] = 0
    q.add(Pos(0, 0, 0))
    while (!v[n - 1][m - 1]) {
        val (i, j, x) = q.remove()
        if (v[i][j]) continue
        v[i][j] = true
        relax(i - 1, j, x)
        relax(i + 1, j, x)
        relax(i, j - 1, x)
        relax(i, j + 1, x)
    }
    val ans = d[n - 1][m - 1]
    println(ans)
}


// Other solution
// https://github.com/SebastianAigner/advent-of-code-2021/blob/master/src/main/kotlin/Day15.kt
/*
@file:OptIn(ExperimentalStdlibApi::class)

package day15

import java.io.File
import kotlin.math.abs

val input = File("inputs/day15.txt").readLines()
val width = input[0].length
val height = input.size

fun getValFor(x: Int, y: Int): Int {
    val rX = x % width
    val rY = y % height
    val charAt = input[rY][rX].digitToInt()
    val xBlock = (x / width)
    val yBlock = (y / height)
    if (xBlock == 0 && yBlock == 0) return charAt
    val prevVal = when {
        xBlock > 0 -> getValFor(x - width, y)
        yBlock > 0 -> getValFor(x, y - height)
        else -> error("broken")
    }
    val newVal = prevVal + 1
    val realNewVal = if (newVal == 10) 1 else newVal
    return realNewVal
}

fun getNodeFor(x: Int, y: Int): Node {
    return Node(x, y, getValFor(x, y))
}

val nodes = buildList<Node> {
    repeat(5 * height) { y ->
        repeat(5 * width) { x ->
            add(getNodeFor(x, y))
        }
    }
}

data class Node(val x: Int, val y: Int, val cost: Int)

fun main() {
    val startNode = nodes[0]
    val endNode = nodes.last()
    println(startNode)
    println(endNode)
    val dijk = dijkstra(nodes, startNode, endNode)
    println(dijk.sumOf { it.cost } - nodes[0].cost)
}

// This impl is slow :) It might be worth refactoring to a solution like the one from Roman: https://github.com/elizarov/AdventOfCode2021/blob/main/src/Day15_2_fast.kt
// But it _did_ get the right solution in the end.

fun dijkstra(graph: List<Node>, source: Node, target: Node): ArrayDeque<Node> {
    val q = mutableSetOf<Node>()
    val dist = mutableMapOf<Node, Int?>()
    val prev = mutableMapOf<Node, Node?>()
    for (vertex in graph) {
        dist[vertex] = Int.MAX_VALUE
        prev[vertex] = null
        q.add(vertex)
    }
    dist[source] = 0
    while (q.isNotEmpty()) {
        val u = q.minByOrNull { dist[it]!! }!!
        q.remove(u)
        if (u == target) break
        for (v in q.filter { it.isNeighborOf(u) }) {
            val alt = dist[u]!! + length(u, v)
            if (alt < dist[v]!!) {
                dist[v] = alt
                prev[v] = u
            }
        }
    }

    // all found.

    val s = ArrayDeque<Node>()
    var u: Node? = target
    if (prev[u] != null || u == source) {
        while (u != null) {
            s.addFirst(u)
            u = prev[u]
        }
    }

    return s
}

fun Node.isNeighborOf(u: Node): Boolean {
    val xDist = abs(this.x - u.x)
    val yDist = abs(this.y - u.y)
    return xDist + yDist == 1
}

fun length(u: Node, v: Node): Int {
    // the cost is saved in v
    return v.cost
}
 */


