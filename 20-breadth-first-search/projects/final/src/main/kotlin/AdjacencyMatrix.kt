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

class AdjacencyMatrix<T: Any> : Graph<T> {

  private val vertices = arrayListOf<Vertex<T>>()
  private val weights = arrayListOf<ArrayList<Double?>>()

  override fun createVertex(data: T): Vertex<T> {
    val vertex = Vertex(vertices.count(), data)
    vertices.add(vertex)
    weights.forEach {
      it.add(null)
    }
    val row = ArrayList<Double?>(vertices.count())
    repeat(vertices.count()) {
      row.add(null)
    }
    weights.add(row)

    return vertex
  }

  override fun addDirectedEdge(
    source: Vertex<T>,
    destination: Vertex<T>,
    weight: Double?
  ) {
    weights[source.index][destination.index] = weight
  }

  override fun edges(source: Vertex<T>): ArrayList<Edge<T>> {
    val edges = arrayListOf<Edge<T>>()
    (0 until weights.size).forEach { column ->
      val weight = weights[source.index][column]
      if (weight != null) {
        edges.add(Edge(source, vertices[column], weight))
      }
    }
    return edges
  }

  override fun weight(
    source: Vertex<T>,
    destination: Vertex<T>
  ): Double? {
    return weights[source.index][destination.index]
  }

  override fun toString(): String {
    val verticesDescription = vertices

      .joinToString(separator = "\n") { "${it.index}: ${it.data}" }
    val grid = weights.map { row ->
      buildString {
        (0 until weights.size).forEach { columnIndex ->
          val value = row[columnIndex]
          if (value != null) {
            append("$value\t")
          } else {
            append("ø\t\t")
          }
        }
      }
    }
    val edgesDescription = grid.joinToString("\n")
    return "$verticesDescription\n\n$edgesDescription"
  }

}
