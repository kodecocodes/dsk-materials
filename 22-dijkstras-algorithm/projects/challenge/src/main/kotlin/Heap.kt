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
import kotlin.collections.ArrayList

interface Collection<T: Any> {
  val count: Int

  val isEmpty: Boolean
    get() = count == 0

  fun insert(element: T)

  fun remove(): T?

  fun remove(index: Int): T?
}


interface Heap<T: Any> : Collection<T> {

  fun peek(): T?
}

abstract class AbstractHeap<T: Any>() : Heap<T> {
  var elements: ArrayList<T> = ArrayList<T>()

  override val count: Int
    get() = elements.size

  override fun peek(): T? = elements.first()

  override fun insert(element: T) {
    elements.add(element) // 1
    siftUp(count - 1) // 2
  }

  private fun siftUp(index: Int) {
    var child = index
    var parent = parentIndex(child)
    while (child > 0 && compare(elements[child], elements[parent]) > 0) {
      Collections.swap(elements, child, parent)
      child = parent
      parent = parentIndex(child)
    }
  }

  override fun remove(): T? {
    if (isEmpty) return null // 1

    Collections.swap(elements, 0, count - 1) // 2
    val item = elements.removeAt(count - 1) // 3
    siftDown(0) // 4
    return item
  }

  private fun siftDown(index: Int) {
    var parent = index // 1
    while (true) { // 2
      val left = leftChildIndex(parent) //3
      val right = rightChildIndex(parent)
      var candidate = parent // 4
      if (left < count &&
        compare(elements[left], elements[candidate]) > 0
      ) {
        candidate = left //5
      }
      if (right < count &&
        compare(elements[right], elements[candidate]) > 0
      ) {
        candidate = right // 6
      }
      if (candidate == parent) {
        return // 7
      }
      Collections.swap(elements, parent, candidate) // 8
      parent = candidate
    }
  }

  override fun remove(index: Int): T? {
    if (index >= count) return null // 1

    return if (index == count - 1) {
      elements.removeAt(count - 1) // 2
    } else {
      Collections.swap(elements, index, count - 1) // 3
      val item = elements.removeAt(count - 1) // 4
      siftDown(index) // 5
      siftUp(index)
      item
    }
  }

  private fun index(element: T, i: Int): Int? {
    if (i >= count) {
      return null // 1
    }
    if (compare(element, elements[i]) > 0) {
      return null // 2
    }
    if (element == elements[i]) {
      return i // 3
    }
    val leftChildIndex = index(element, leftChildIndex(i))
    if (leftChildIndex != null) return leftChildIndex // 4
    val rightChildIndex = index(element, rightChildIndex(i))
    if (rightChildIndex != null) return rightChildIndex // 5
    return null // 6
  }

  protected fun heapify(values: ArrayList<T>) {
    elements = values
    if (!elements.isEmpty()) {
      (count / 2 downTo 0).forEach {
        siftDown(it)
      }
    }
  }

  private fun leftChildIndex(index: Int) = (2 * index) + 1

  private fun rightChildIndex(index: Int) = (2 * index) + 2

  private fun parentIndex(index: Int) = (index - 1) / 2

  abstract fun compare(a: T, b: T): Int
}

class ComparableHeapImpl<T : Comparable<T>> :
  AbstractHeap<T>() {

  companion object {
    fun <T : Comparable<T>> create(
      elements: ArrayList<T>
    ): Heap<T> {
      val heap = ComparableHeapImpl<T>()
      heap.heapify(elements)
      return heap
    }
  }

  override fun compare(a: T, b: T): Int = a.compareTo(b)
}

class ComparatorHeapImpl<T: Any>(
  private val comparator: Comparator<T>
) : AbstractHeap<T>() {

  companion object {
    fun <T: Any> create(
      elements: ArrayList<T>,
      comparator: Comparator<T>
    ): Heap<T> {
      val heap = ComparatorHeapImpl(comparator)
      heap.heapify(elements)
      return heap
    }
  }

  override fun compare(a: T, b: T): Int =
    comparator.compare(a, b)
}
