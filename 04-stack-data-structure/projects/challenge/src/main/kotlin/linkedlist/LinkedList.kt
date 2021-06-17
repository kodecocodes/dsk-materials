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

package linkedlist

class LinkedList<T : Any> : Iterable<T>, Collection<T>, MutableIterable<T>, MutableCollection<T> {

  private var head: Node<T>? = null
  private var tail: Node<T>? = null
  override var size = 0
    private set

  override fun isEmpty(): Boolean = size == 0

  override fun toString(): String {
    if (isEmpty()) {
      return "Empty list"
    } else {
      return head.toString()
    }
  }

  fun push(value: T): LinkedList<T> = apply {
    head = Node(value = value, next = head)
    if (tail == null) {
      tail = head
    }
    size++
  }

  fun append(value: T) {
    // 1
    if (isEmpty()) {
      push(value)
      return
    }
    // 2
    val newNode = Node(value = value)
    tail!!.next = newNode

    // 3
    tail = newNode
    size++
  }

  fun nodeAt(index: Int): Node<T>? {
    // 1
    var currentNode = head
    var currentIndex = 0

    // 2
    while (currentNode != null && currentIndex < index) {
      currentNode = currentNode.next
      currentIndex++
    }

    return currentNode
  }

  fun insert(value: T, afterNode: Node<T>): Node<T> {
    // 1
    if (tail == afterNode) {
      append(value)
      return tail!!
    }
    // 2
    val newNode = Node(value = value, next = afterNode.next)
    // 3
    afterNode.next = newNode
    size++
    return newNode
  }

  fun pop(): T? {
    if (isEmpty()) return null

    val result = head?.value
    head = head?.next
    size--
    if (isEmpty()) {
      tail = null
    }

    return result
  }

  fun removeLast(): T? {
    // 1
    val head = head ?: return null
    // 2
    if (head.next == null) return pop()
    // 3
    size--

    // 4
    var prev = head
    var current = head

    var next = current.next
    while (next != null) {
      prev = current
      current = next
      next = current.next
    }
    // 5
    prev.next = null
    tail = prev
    return current.value
  }

  fun removeAfter(node: Node<T>): T? {
    val result = node.next?.value

    if (node.next == tail) {
      tail = node
    }

    if (node.next != null) {
      size--
    }

    node.next = node.next?.next
    return result
  }

  override fun iterator(): MutableIterator<T> {
    return LinkedListIterator(this)
  }

  override fun contains(element: T): Boolean {
    for (item in this) {
      if (item == element) return true
    }
    return false
  }

  override fun containsAll(elements: Collection<T>): Boolean {
    for (searched in elements) {
      if (!contains(searched)) return false
    }
    return true
  }

  override fun add(element: T): Boolean {
    append(element)
    return true
  }

  override fun addAll(elements: Collection<T>): Boolean {
    for (element in elements) {
      append(element)
    }
    return true
  }

  override fun clear() {
    head = null
    tail = null
    size = 0
  }

  override fun remove(element: T): Boolean {
    // 1
    val iterator = iterator()
    // 2
    while (iterator.hasNext()) {
      val item = iterator.next()
      // 3
      if (item == element) {
        iterator.remove()
        return true
      }
    }
    // 4
    return false
  }

  override fun removeAll(elements: Collection<T>): Boolean {
    var result = false
    for (item in elements) {
      result = remove(item) || result
    }
    return result
  }

  override fun retainAll(elements: Collection<T>): Boolean {
    var result = false
    val iterator = this.iterator()
    while (iterator.hasNext()) {
      val item = iterator.next()
      if (!elements.contains(item)) {
        iterator.remove()
        result = true
      }
    }
    return result
  }
}
