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

package linkedlist

class LinkedListIterator<T>(
  private val list: LinkedList<T>
) : Iterator<T>, MutableIterator<T> {

  private var index = 0
  private var lastNode: Node<T>? = null

  override fun hasNext(): Boolean {
    return index < list.size
  }

  override fun next(): T {
    // 1
    if (index >= list.size) throw IndexOutOfBoundsException()
    // 2
    lastNode = if (index == 0) {
      list.nodeAt(0)
    } else {
      lastNode?.next
    }
    // 3
    index++
    return lastNode!!.value
  }

  override fun remove() {
    // 1
    if (index == 1) {
      list.pop()
    } else {
      // 2
      val prevNode = list.nodeAt(index - 2) ?: return
      // 3
      list.removeAfter(prevNode)
      lastNode = prevNode
    }
    index--
  }
}