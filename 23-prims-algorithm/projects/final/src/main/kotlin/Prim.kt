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

import java.util.*
import kotlin.Comparator
import kotlin.math.roundToInt

object Prim {

  private fun <T: Any> addAvailableEdges(
      vertex: Vertex<T>,
      graph: Graph<T>,
      visited: Set<Vertex<T>>,
      priorityQueue: AbstractPriorityQueue<Edge<T>>
  ) {
    graph.edges(vertex).forEach { edge ->
      if (edge.destination !in visited) {
        priorityQueue.enqueue(edge)
      }
    }
  }

  fun <T: Any> produceMinimumSpanningTree(
      graph: AdjacencyList<T>
  ): Pair<Double, AdjacencyList<T>> {
    var cost = 0.0
    val mst = AdjacencyList<T>()
    val visited = mutableSetOf<Vertex<T>>()
    val comparator = Comparator<Edge<T>> { first, second ->
      val firstWeight = first.weight ?: 0.0
      val secondWeight = second.weight ?: 0.0
      (secondWeight - firstWeight).roundToInt()
    }
    val priorityQueue = ComparatorPriorityQueueImpl(comparator)
    mst.copyVertices(graph)

    val start = graph.vertices.firstOrNull() ?: return Pair(cost, mst)

    visited.add(start)
    addAvailableEdges(start, graph, visited, priorityQueue)

    while (true) {
      val smallestEdge = priorityQueue.dequeue() ?: break
      val vertex = smallestEdge.destination
      if (vertex in visited) continue

      visited.add(vertex)
      cost += smallestEdge.weight ?: 0.0

      mst.add(EdgeType.UNDIRECTED, smallestEdge.source, smallestEdge.destination, smallestEdge.weight)

      addAvailableEdges(vertex, graph, visited, priorityQueue)
    }

    return Pair(cost, mst)
  }

}
