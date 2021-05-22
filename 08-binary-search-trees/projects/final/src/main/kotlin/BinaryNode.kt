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

typealias Visitor<T> = (T) -> Unit

class BinaryNode<T>(var value: T) {

  var leftChild: BinaryNode<T>? = null
  var rightChild: BinaryNode<T>? = null

  val min: BinaryNode<T>?
    get() = leftChild?.min ?: this

  override fun toString() = diagram(this)

  private fun diagram(
    node: BinaryNode<T>?,
    top: String = "",
    root: String = "",
    bottom: String = ""
  ): String {
    return node?.let {
      if (node.leftChild == null && node.rightChild == null) {
        "$root${node.value}\n"
      } else {
        diagram(node.rightChild, "$top ", "$top┌──", "$top│ ") +
          root + "${node.value}\n" + diagram(node.leftChild, "$bottom│ ", "$bottom└──", "$bottom ")
      }
    } ?: "${root}null\n"
  }

  fun traverseInOrder(visit: Visitor<T>) {
    leftChild?.traverseInOrder(visit)
    visit(value)
    rightChild?.traverseInOrder(visit)
  }

  fun traversePreOrder(visit: Visitor<T>) {
    visit(value)
    leftChild?.traversePreOrder(visit)
    rightChild?.traversePreOrder(visit)
  }

  fun traversePostOrder(visit: Visitor<T>) {
    leftChild?.traversePostOrder(visit)
    rightChild?.traversePostOrder(visit)
    visit(value)
  }

}
