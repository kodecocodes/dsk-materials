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

interface Graph<T: Any> {

  fun createVertex(data: T): Vertex<T>
  fun addDirectedEdge(source: Vertex<T>, destination: Vertex<T>, weight: Double?)
  fun addUndirectedEdge(source: Vertex<T>, destination: Vertex<T>, weight: Double?) {
    addDirectedEdge(source, destination, weight)
    addDirectedEdge(destination, source, weight)
  }

  fun add(edge: EdgeType, source: Vertex<T>, destination: Vertex<T>, weight: Double?) {
    when (edge) {
      EdgeType.DIRECTED -> addDirectedEdge(source, destination, weight)
      EdgeType.UNDIRECTED -> addUndirectedEdge(source, destination, weight)
    }
  }

  fun edges(source: Vertex<T>): ArrayList<Edge<T>>
  fun weight(source: Vertex<T>, destination: Vertex<T>): Double?

  fun numberOfPaths(source: Vertex<T>, destination: Vertex<T>): Int {
    val numberOfPaths = Ref(0) // 1
    val visited: ArrayList<Vertex<T>> = arrayListOf() // 2
    paths(source, destination, visited, numberOfPaths) // 3
    return numberOfPaths.value
  }

  fun paths(source: Vertex<T>, destination: Vertex<T>, visited: ArrayList<Vertex<T>>, pathCount: Ref<Int>) {
    visited.add(source) // 1
    if (source == destination) { // 2
      pathCount.value += 1
    } else {
      val neighbors = edges(source) // 3
      neighbors.forEach { edge ->
        // 4
        if (!visited.contains(edge.destination)) {
          paths(edge.destination, destination, visited, pathCount)
        }
      }
    }
    // 5
    visited.remove(source)
  }

  fun breadthFirstSearch(source: Vertex<T>): ArrayList<Vertex<T>> {
    val queue = QueueStack<Vertex<T>>()
    val enqueued = ArrayList<Vertex<T>>()
    val visited = ArrayList<Vertex<T>>()
    queue.enqueue(source) // 1
    enqueued.add(source)
    while (true) {
      val vertex = queue.dequeue() ?: break // 2
      visited.add(vertex) // 3
      val neighborEdges = edges(vertex) // 4
      neighborEdges.forEach {
        if (!enqueued.contains(it.destination)) { // 5
          queue.enqueue(it.destination)
          enqueued.add(it.destination)
        }
      }
    }
    return visited
  }

  fun depthFirstSearch(source: Vertex<T>): ArrayList<Vertex<T>> {
    val stack = StackImpl<Vertex<T>>()
    val visited = arrayListOf<Vertex<T>>()
    val pushed = mutableSetOf<Vertex<T>>()

    stack.push(source)
    pushed.add(source)
    visited.add(source)

    outer@ while (true) {
      if (stack.isEmpty) break

      val vertex = stack.peek()!!
      val neighbors = edges(vertex)

      if (neighbors.isEmpty()) {
        stack.pop()
        continue
      }

      for (i in 0 until neighbors.size) {
        val destination = neighbors[i].destination
        if (destination !in pushed) {
          stack.push(destination)
          pushed.add(destination)
          visited.add(destination)
          continue@outer
        }
      }
      stack.pop()
    }

    return visited
  }

  // Solution 2
  fun depthFirstSearchRecursive(start: Vertex<T>): ArrayList<Vertex<T>> {
    val visited = arrayListOf<Vertex<T>>()
    val pushed = mutableSetOf<Vertex<T>>()

    depthFirstSearch(start, visited, pushed)

    return visited
  }

  fun depthFirstSearch(
    source: Vertex<T>,
    visited: ArrayList<Vertex<T>>,
    pushed: MutableSet<Vertex<T>>
  ) {
    pushed.add(source)
    visited.add(source)

    val neighbors = edges(source)
    neighbors.forEach {
      if (it.destination !in pushed) {
        depthFirstSearch(it.destination, visited, pushed)
      }
    }
  }

  // Solution 3
  fun hasCycle(source: Vertex<T>): Boolean {
    val pushed = mutableSetOf<Vertex<T>>()
    return hasCycle(source, pushed)
  }

  fun hasCycle(source: Vertex<T>, pushed: MutableSet<Vertex<T>>): Boolean {
    pushed.add(source)

    val neighbors = edges(source)
    neighbors.forEach {
      if (it.destination !in pushed && hasCycle(it.destination, pushed)) {
        return true
      } else if (it.destination in pushed) {
        return true
      }
    }

    pushed.remove(source)
    return false
  }

}

enum class EdgeType {
  DIRECTED,
  UNDIRECTED
}