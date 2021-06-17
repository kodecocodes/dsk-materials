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

package doublestack

import base.Queue

class StackQueue<T : Any> : Queue<T> {
  private val leftStack = StackImpl<T>()
  private val rightStack = StackImpl<T>()

  override val isEmpty: Boolean
    get() = leftStack.isEmpty && rightStack.isEmpty

  override val count: Int
    get() = leftStack.count + rightStack.count

  private fun transferElements() {
    var nextElement = rightStack.pop()
    while (nextElement != null) {
      leftStack.push(nextElement)
      nextElement = rightStack.pop()
    }
  }

  override fun peek(): T? {
    if (leftStack.isEmpty) {
      transferElements()
    }
    return leftStack.peek()
  }

  override fun enqueue(element: T): Boolean {
    rightStack.push(element)
    return true
  }

  override fun dequeue(): T? {
    if (leftStack.isEmpty) { // 1
      transferElements() // 2
    }
    return leftStack.pop() // 3
  }

  override fun toString(): String {
    return "Left stack: \n$leftStack \n Right stack: \n$rightStack"
  }
}
