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

abstract class AbstractPriorityQueueArrayList<T: Any> : Queue<T> {

  protected val elements = ArrayList<T>()

  abstract fun sort()

  override val count: Int
    get() = elements.size

  override fun enqueue(element: T): Boolean {
    elements.add(element)
    sort()
    return true
  }

  override fun dequeue() =
    if (isEmpty) null else elements.removeAt(0)

  override fun peek() = elements.firstOrNull()

  override fun toString() = elements.toString()
}

class ComparablePriorityQueueArrayList<T : Comparable<T>> : AbstractPriorityQueueArrayList<T>() {
  override fun sort() {
    Collections.sort(elements)
  }
}

class ComparatorPriorityQueueArrayList<T: Any>(
  private val comparator: Comparator<T>
) : AbstractPriorityQueueArrayList<T>() {
  override fun sort() {
    Collections.sort(elements, comparator)
  }
}

class CustomPriorityQueueArrayList<T : Comparable<T>> : AbstractPriorityQueueArrayList<T>() {
  override fun sort() {
    var index = count - 2
    while (index >= 0 &&
      elements[index + 1].compareTo(elements[index]) > 0) {
      swap(index, index + 1)
      index--;
    }
  }

  private fun swap(i: Int, j: Int) {
    val tmp = elements[i]
    elements[i] = elements[j]
    elements[j] = tmp
  }
}