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
  val graph = AdjacencyList<Int>()
  val one = graph.createVertex(1)
  val two = graph.createVertex(2)
  val three = graph.createVertex(3)
  val four = graph.createVertex(4)
  val five = graph.createVertex(5)
  val six = graph.createVertex(6)

  graph.add(EdgeType.UNDIRECTED, one, two, 6.0)
  graph.add(EdgeType.UNDIRECTED, one, three, 1.0)
  graph.add(EdgeType.UNDIRECTED, one, four, 5.0)
  graph.add(EdgeType.UNDIRECTED, two, three, 5.0)
  graph.add(EdgeType.UNDIRECTED, two, five, 3.0)
  graph.add(EdgeType.UNDIRECTED, three, four, 5.0)
  graph.add(EdgeType.UNDIRECTED, three, five, 6.0)
  graph.add(EdgeType.UNDIRECTED, three, six, 4.0)
  graph.add(EdgeType.UNDIRECTED, four, six, 2.0)
  graph.add(EdgeType.UNDIRECTED, five, six, 6.0)

  val (cost, mst) = Prim.produceMinimumSpanningTree(graph)
  println("cost: $cost")
  println("mst:")
  println(mst)
}