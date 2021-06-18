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
package heapsort

import swapAt


private fun leftChildIndex(index: Int) = (2 * index) + 1

private fun rightChildIndex(index: Int) = (2 * index) + 2

fun <T : Any> Array<T>.siftDown(
  index: Int,
  upTo: Int,
  comparator: Comparator<T>,
) {
  var parent = index
  while (true) {
    val left = leftChildIndex(parent)
    val right = rightChildIndex(parent)
    var candidate = parent
    if (left < upTo &&
      comparator.compare(this[left], this[candidate]) > 0
    ) {
      candidate = left
    }
    if (right < upTo &&
      comparator.compare(this[right], this[candidate]) > 0
    ) {
      candidate = right
    }
    if (candidate == parent) {
      return
    }
    this.swapAt(parent, candidate)
    parent = candidate
  }
}

fun <T : Any> Array<T>.heapify(comparator: Comparator<T>) {
  if (this.isNotEmpty()) {
    (this.size / 2 downTo 0).forEach {
      this.siftDown(it, this.size, comparator)
    }
  }
}

fun <T : Any> Array<T>.heapSort(comparator: Comparator<T>) {
  this.heapify(comparator) // 1
  for (index in this.indices.reversed()) { // 2
    this.swapAt(0, index) // 3
    siftDown(0, index, comparator) // 4
  }
}
