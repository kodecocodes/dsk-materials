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
  val f = graph.createVertex("F")
  val g = graph.createVertex("G")
  val h = graph.createVertex("H")

  graph.add(EdgeType.UNDIRECTED, a, b, null)
  graph.add(EdgeType.UNDIRECTED, a, c, null)
  graph.add(EdgeType.UNDIRECTED, a, d, null)
  graph.add(EdgeType.UNDIRECTED, b, e, null)
  graph.add(EdgeType.UNDIRECTED, c, f, null)
  graph.add(EdgeType.UNDIRECTED, c, g, null)
  graph.add(EdgeType.UNDIRECTED, e, h, null)
  graph.add(EdgeType.UNDIRECTED, e, f, null)
  graph.add(EdgeType.UNDIRECTED, f, g, null)

  // 2
  val vertices = graph.bfs(a)
  vertices.forEach {
      println(it.data)
  }

  // 3
  println(graph.isDisconnected())

  graph.createVertex("I")

  println(graph.isDisconnected())
}