/*
 * Copyright (c) 2021 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

fun main() {
  val graph = AdjacencyList<String>()

  val a = graph.createVertex("A")
  val b = graph.createVertex("B")
  val c = graph.createVertex("C")
  val d = graph.createVertex("D")
  val e = graph.createVertex("E")

  graph.add(EdgeType.DIRECTED, a, b, 1.0)
  graph.add(EdgeType.DIRECTED, a, e, 21.0)
  graph.add(EdgeType.DIRECTED, a, c, 12.0)
  graph.add(EdgeType.DIRECTED, b, d, 9.0)
  graph.add(EdgeType.DIRECTED, b, c, 8.0)
  graph.add(EdgeType.DIRECTED, d, e, 2.0)
  graph.add(EdgeType.DIRECTED, c, e, 2.0)

  val dijkstra = Dijkstra(graph)
  val pathsFromA = dijkstra.getAllShortestPath(a) // 2
  
  pathsFromA.forEach { (vertex, path) ->
    println("Path to ${vertex.data} from ${a.data}")
    path.forEach {
      println("${it.source.data} --|${it.weight ?: 0.0}|--> ${it.destination.data}")
    }
    println()
  }
}