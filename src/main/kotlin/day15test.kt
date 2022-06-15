import java.io.File
import java.util.TreeSet

fun main() {
    val inputLines = File("src/main/kotlin/input15.txt").inputStream()
    val lineList = mutableListOf<String>()
    inputLines.bufferedReader().forEachLine { lineList.add(it) }

    val g = mutableListOf<Edge>()

    for ((index, line) in lineList.withIndex()) {
        val a = line.split("").filter { it != "" }.map { it.toInt() }
//        println(a)
        for ((i, n) in a.withIndex()) {
            if (i < a.lastIndex) {
                val e = Edge(Pair(index, i), Pair(index, i + 1), a[i + 1])
                g.add(e)
                println("${e.v1} ${e.v2} ${e.dist}")
            }
            if (index < lineList.lastIndex) {
                val e = Edge(Pair(index, i), Pair(index + 1, i), lineList[index + 1][i].code - 48)
                g.add(e)
                println("${e.v1} ${e.v2} ${e.dist}")
            }
        }
    }



    val START = Pair(0, 0)
    val END = Pair(lineList.lastIndex, lineList[0].length - 1)

    with (Graph(g, true)) {   // directed
        dijkstra(START)
        printPath(END)
    }
    with (Graph(g, false)) {   // directed
        dijkstra(START)
        printPath(END)
    }
}



class Edge(val v1: Pair<Int, Int>, val v2: Pair<Int, Int>, val dist: Int)

/** One vertex of the graph, complete with mappings to neighbouring vertices */
class Vertex(val name: Pair<Int, Int>) : Comparable<Vertex> {

    var dist = Int.MAX_VALUE  // MAX_VALUE assumed to be infinity
    var previous: Vertex? = null
    val neighbours = HashMap<Vertex, Int>()

    fun printPath() {
        if (this == previous) {
            print(name)
        }
        else if (previous == null) {
            print("$name(unreached)")
        }
        else {
            previous!!.printPath()
            print(" -> $name($dist)")
        }
    }

    override fun compareTo(other: Vertex): Int {
        if (dist == other.dist) return name.compareTo(other.name)
        return dist.compareTo(other.dist)
    }

    fun Pair<Int, Int>.compareTo(other: Pair<Int, Int>): Int {
        return if (this == other) 0 else 1
    }

    override fun toString() = "($name, $dist)"
}

class Graph(
    val edges: List<Edge>,
    val directed: Boolean,
    val showAllPaths: Boolean = false
) {
    // mapping of vertex names to Vertex objects, built from a set of Edges
    val graph = HashMap<Pair<Int, Int>, Vertex>(edges.size)

    init {
        // one pass to find all vertices
        for (e in edges) {
            if (!graph.containsKey(e.v1)) graph.put(e.v1, Vertex(e.v1))
            if (!graph.containsKey(e.v2)) graph.put(e.v2, Vertex(e.v2))
        }

        // another pass to set neighbouring vertices
        for (e in edges) {
            graph[e.v1]!!.neighbours.put(graph[e.v2]!!, e.dist)
            // also do this for an undirected graph if applicable
            if (!directed) graph[e.v2]!!.neighbours.put(graph[e.v1]!!, e.dist)
        }
    }

    /** Runs test03.kt.dijkstra using a specified source vertex */
    fun dijkstra(startName: Pair<Int, Int>) {
        if (!graph.containsKey(startName)) {
            println("Graph doesn't contain start vertex '$startName'")
            return
        }
        val source = graph[startName]
        val q = TreeSet<Vertex>()

        // set-up vertices
        for (v in graph.values) {
            v.previous = if (v == source) source else null
            v.dist = if (v == source)  0 else Int.MAX_VALUE
            q.add(v)
        }

        dijkstra(q)
    }

    /** Implementation of test03.kt.dijkstra's algorithm using a binary heap */
    private fun dijkstra(q: TreeSet<Vertex>) {
        while (!q.isEmpty()) {
            // vertex with shortest distance (first iteration will return source)
            val u = q.pollFirst()
            // if distance is infinite we can ignore 'u' (and any other remaining vertices)
            // since they are unreachable
            if (u.dist == Int.MAX_VALUE) break

            //look at distances to each neighbour
            for (a in u.neighbours) {
                val v = a.key // the neighbour in this iteration

                val alternateDist = u.dist + a.value
                if (alternateDist < v.dist) { // shorter path to neighbour found
                    q.remove(v)
                    v.dist = alternateDist
                    v.previous = u
                    q.add(v)
                }
            }
        }
    }

    /** Prints a path from the source to the specified vertex */
    fun printPath(endName: Pair<Int, Int>) {
        if (!graph.containsKey(endName)) {
            println("Graph doesn't contain end vertex '$endName'")
            return
        }
        print(if (directed) "Directed   : " else "Undirected : ")
        graph[endName]!!.printPath()
        println()
        if (showAllPaths) printAllPaths() else println()
    }

    /** Prints the path from the source to every vertex (output order is not guaranteed) */
    private fun printAllPaths() {
        for (v in graph.values) {
            v.printPath()
            println()
        }
        println()
    }
}