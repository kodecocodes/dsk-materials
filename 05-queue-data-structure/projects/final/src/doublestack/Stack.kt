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
package doublestack

/**
 * The Stack interface.
 */
interface Stack<Element> {

  /**
   * Push of an Element into the stack.Stack
   */
  fun push(element: Element)

  /**
   * Pops an element from the stack.Stack if any or returns null.
   */
  fun pop(): Element?

  val count: Int
    get

  fun peek(): Element?

  val isEmpty: Boolean
    get() = count == 0
}

/**
 * Simple stack.Stack implementation using an ArrayList
 */
class StackImpl<Element> : Stack<Element> {

  private val storage = arrayListOf<Element>()

  companion object {
    fun <Element> create(items: Iterable<Element>): Stack<Element> {
      val stack = StackImpl<Element>()
      for (item in items) {
        stack.push(item)
      }
      return stack
    }
  }

  override fun push(element: Element) {
    storage.add(element)
  }

  override fun pop(): Element? {
    if (isEmpty) {
      return null
    }
    return storage.removeAt(count - 1)
  }

  override fun peek(): Element? {
    return storage.lastOrNull()
  }

  override val count: Int
    get() = storage.size

  override fun toString() = buildString {
    appendln("----top----")
    storage.asReversed().forEach {
      appendln("$it")
    }
    appendln("-----------")
  }
}

fun <Element> stackOf(vararg elements: Element): Stack<Element> {
  return StackImpl.create(elements.asList())
}