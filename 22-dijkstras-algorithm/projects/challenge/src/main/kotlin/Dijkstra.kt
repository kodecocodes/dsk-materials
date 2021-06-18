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

class Dijkstra<T: Any>(private val graph: AdjacencyList<T>) {

  private fun route(destination: Vertex<T>, paths: HashMap<Vertex<T>, Visit<T>>): ArrayList<Edge<T>> {
    var vertex = destination
    val path = arrayListOf<Edge<T>>()

    loop@ while (true) {
      val visit = paths[vertex] ?: break

      when (visit.type) {
        VisitType.START -> break@loop
        VisitType.EDGE -> visit.edge?.let {
          path.add(it)
          vertex = it.source
        }
      }
    }

    return path
  }

  private fun distance(destination: Vertex<T>, paths: HashMap<Vertex<T>, Visit<T>>): Double {
    val path = route(destination, paths)
    return path.sumByDouble { it.weight ?: 0.0 }
  }

  fun shortestPath(start: Vertex<T>): HashMap<Vertex<T>, Visit<T>> {
    val paths: HashMap<Vertex<T>, Visit<T>> = HashMap()
    paths[start] = Visit(VisitType.START)

    val distanceComparator = Comparator<Vertex<T>>({ first, second ->
      (distance(second, paths) - distance(first, paths)).toInt()
    })

    val priorityQueue = ComparatorPriorityQueueImpl(distanceComparator)
    priorityQueue.enqueue(start)

    while (true) {
      val vertex = priorityQueue.dequeue() ?: break
      val edges = graph.edges(vertex)

      edges.forEach {
        val weight = it.weight ?: return@forEach

        if (paths[it.destination] == null
          || distance(vertex, paths) + weight < distance(it.destination, paths)) {
          paths[it.destination] = Visit(VisitType.EDGE, it)
          priorityQueue.enqueue(it.destination)
        }
      }
    }

    return paths
  }

  fun shortestPath(destination: Vertex<T>, paths: HashMap<Vertex<T>, Visit<T>>): ArrayList<Edge<T>> {
    return route(destination, paths)
  }

  // Solution 2
  fun getAllShortestPath(source: Vertex<T>): HashMap<Vertex<T>, ArrayList<Edge<T>>> {
    val paths = HashMap<Vertex<T>, ArrayList<Edge<T>>>()
    val pathsFromSource = shortestPath(source)

    graph.vertices.forEach {
      val path = shortestPath(it, pathsFromSource)
      paths[it] = path
    }

    return paths
  }

}

class Visit<T: Any>(val type: VisitType, val edge: Edge<T>? = null)

enum class VisitType {

  START,
  EDGE

}