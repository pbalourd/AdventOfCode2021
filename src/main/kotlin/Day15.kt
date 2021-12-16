package day15

import java.io.File

val s = 499

fun main() {
    val inputLines = File("src/main/kotlin/input15.txt").inputStream()
    val lineList = mutableListOf<String>()
    inputLines.bufferedReader().forEachLine { lineList.add(it) }

    val points = mutableListOf<MutableList<Int>>()

    for ((index, line) in lineList.withIndex()) {
        var p = mutableListOf<Int>()
        val a = line.split("").filter { it != "" }.map { it.toInt() }.toMutableList()
        p.addAll(a)
//        var q = mutableListOf<Int>()
        repeat(4) {
            p = p.map { if (it < 9) it + 1 else 1 }.toMutableList()
            a.addAll(p)
        }
//        println(a)
        points.add(a)
    }

    val linesNum = points.size
    for (t in 1..4) {
        var p = mutableListOf<Int>()
        for (l in 0 until linesNum) {
            p = points[l].map { it + t }.map{ if (it < 10) it else it - 9 }.toMutableList()
            points.add(p)
        }
    }

//    println(points)

    val p = mutableListOf<Int>()

    val w = mutableMapOf<Pair<Int, Int>, Map<Pair<Int, Int>, Int>>()



    for (i in 0..s) {
        for (j in 0 .. s) {
            val m = mutableMapOf<Pair<Int, Int>, Int>()
            if (j < s) m.put(Pair(i, j + 1), points[i][j + 1])
            if (i < s) m.put(Pair(i + 1, j), points[i + 1][j])
            w.put(Pair(i, j), m)
        }
    }

//    for ((index, line) in lineList.withIndex()) {
//        val a = line.split("").filter { it != "" }.map { it.toInt() }
//
//        for ((i, n) in a.withIndex()) {
//            val m = mutableMapOf<Pair<Int, Int>, Int>()
//            if (i < a.lastIndex) m.put(Pair(index, i + 1), a[i + 1])
//            if (index < lineList.lastIndex) m.put(Pair(index + 1, i), lineList[index + 1][i].code - 48)
//
//            w.put(Pair(index, i), m)
//        }
//    }
//    println(w)
    val g = Graph2(w)
    println(g.findShortestPath(Pair(0, 0), Pair(s, s)))

}

class Graph2<T>(private val weightedPaths: Map<T, Map<T, Int>>) {

    fun findShortestPath(start: T, end: T): Int {
        val paths = recurseFindShortestPath(NodePaths(start, end)).paths
        return paths.getValue(end)
    }

    private tailrec fun recurseFindShortestPath(nodePaths: NodePaths<T>): NodePaths<T> {
        return if (nodePaths.isFinished()) nodePaths
        else {
            val nextNode = nodePaths.nextNode(weightedPaths)
            recurseFindShortestPath(nextNode)
        }
    }
}

data class NodePaths<T>(private val node: T, private  val end: T, val paths: Map<T, Int> = emptyMap()) {

    private fun updatePath(entry: Map.Entry<T, Int>): NodePaths<T> {
        val currentDistance = paths.getOrDefault(entry.key, Integer.MAX_VALUE)
        val newDistance = entry.value + paths.getOrDefault(node, 0)
        return if (newDistance < currentDistance)
            this + (entry.key to newDistance)
        else
            this
    }

    private fun updatePaths(weightedPaths: Map<T, Map<T, Int>>) = (weightedPaths[node]
        ?.map { updatePath(it) }
        ?.fold(copy()) { acc, item -> acc + item } ?: copy()) - node

    fun nextNode(weightedPaths: Map<T, Map<T, Int>>): NodePaths<T> {
        val updatedPaths = updatePaths(weightedPaths)
        val nextNode = updatedPaths.paths.minByOrNull { it.value }?.key as T
        return updatedPaths.copy(node = nextNode)
    }

    fun isFinished() = node == end

    operator fun plus(other: NodePaths<T>) = copy(paths = paths + other.paths)

    operator fun plus(pair: Pair<T, Int>) = copy(paths = paths + pair)

    operator fun minus(node: T)= copy(paths = paths - node)
}


