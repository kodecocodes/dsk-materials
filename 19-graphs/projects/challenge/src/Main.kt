/*
 * Copyright (c) 2019 Razeware LLC
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

  val vincent = graph.createVertex("vincent")
  val chesley = graph.createVertex("chesley")
  val ruiz = graph.createVertex("ruiz")
  val patrick = graph.createVertex("patrick")
  val ray = graph.createVertex("ray")
  val sun = graph.createVertex("sun")
  val cole = graph.createVertex("cole")
  val kerry = graph.createVertex("kerry")

  graph.add(EdgeType.UNDIRECTED, vincent, chesley, 0.0)
  graph.add(EdgeType.UNDIRECTED, vincent, ruiz, 0.0)
  graph.add(EdgeType.UNDIRECTED, vincent, patrick, 0.0)
  graph.add(EdgeType.UNDIRECTED, ruiz, ray, 0.0)
  graph.add(EdgeType.UNDIRECTED, ruiz, sun, 0.0)
  graph.add(EdgeType.UNDIRECTED, patrick, cole, 0.0)
  graph.add(EdgeType.UNDIRECTED, patrick, kerry, 0.0)
  graph.add(EdgeType.UNDIRECTED, cole, ruiz, 0.0)
  graph.add(EdgeType.UNDIRECTED, cole, vincent, 0.0)

  println(graph)
  println("Ruiz and Vincent both share a friend name Cole")
}
