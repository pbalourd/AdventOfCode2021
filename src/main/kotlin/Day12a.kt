package day12a

import java.io.File

var count = 0

fun main() {
    val inputLines = File("src/main/kotlin/input12.txt").inputStream()
    val lineList = mutableListOf<String>()
    inputLines.bufferedReader().forEachLine { lineList.add(it) }

    val vertices = mutableSetOf<String>()
    val edges = mutableSetOf<Pair<String, String>>()
    for (line in lineList) {
        val vs = line.trim().split("-")
        vertices.add(vs[0])
        vertices.add(vs[1])
        edges.add(Pair(vs[0], vs[1]))
    }

    val path = mutableListOf<String>()
    val vis = mutableSetOf<String>()
    visit(vertices, edges, vis,"start", "end", path)

    println(count)
}

fun visit(vertices: MutableSet<String>, edges: MutableSet<Pair<String, String>>, visited: MutableSet<String>, start: String, end: String, path: MutableList<String>) {
//    print(start)
//    print(" : ")
    val path1 = mutableListOf<String>()
    path1.addAll(path)
    path1.add(start)
    if (start == end) {
        println(path1)
        count++
    } else {
        val vis = mutableSetOf<String>()
        vis.addAll(visited)
        if (start[0].isLowerCase()) vis.add(start)
//                && start != "start" && start != "end"
//        println(vis)
        val nodes1 = edges
            .filter { it.first == start }
            .map { it.second }
            .filter { !vis.contains(it) }
        val nodes2 = edges
            .filter { it.second == start }
            .map { it.first }
            .filter { !vis.contains(it) }
        val nodes = mutableListOf<String>()
        nodes.addAll(nodes1)
        nodes.addAll(nodes2)
//        println(nodes)
//        println("---------")
        for (node in nodes) {
            visit(vertices, edges, vis, node, end, path1)
        }
    }
}

