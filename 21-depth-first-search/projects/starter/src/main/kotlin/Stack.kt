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

/**
 * The Stack interface.
 */
interface Stack<T: Any> {

  /**
   * Push of an T into the stack.Stack
   */
  fun push(element: T)

  /**
   * Pops an element from the stack.Stack if any or returns null.
   */
  fun pop(): T?

  val count: Int

  fun peek(): T?

  val isEmpty: Boolean
    get() = count == 0
}


/**
 * Simple stack.Stack implementation using an ArrayList
 */
class StackImpl<T: Any> : Stack<T> {

  private val storage = arrayListOf<T>()

  companion object {
    fun <T: Any> create(items: Iterable<T>): Stack<T> {
      val stack = StackImpl<T>()
      for (item in items) {
        stack.push(item)
      }
      return stack
    }
  }

  override fun push(element: T) {
    storage.add(element)
  }

  override fun pop(): T? {
    if (isEmpty) {
      return null
    }
    return storage.removeAt(count - 1)
  }

  override fun peek(): T? {
    return storage.lastOrNull()
  }

  override val count: Int
    get() = storage.size

  override fun toString() = buildString {
    appendLine("----top----")
    storage.asReversed().forEach {
      appendLine("$it")
    }
    appendLine("-----------")
  }
}

fun <T: Any> stackOf(vararg elements: T): Stack<T> {
  return StackImpl.create(elements.asList())
}