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

/*:
 # Linked List Challenges
 ## Challenge 4:
 Create a function that takes two sorted linked lists and merges them into a single sorted linked list
 */

fun <T : Comparable<T>> LinkedList<T>.mergeSorted(
  otherList: LinkedList<T>
): LinkedList<T> {
  if (this.isEmpty()) return otherList
  if (otherList.isEmpty()) return this

  val result = LinkedList<T>()

  var left = nodeAt(0)
  var right = otherList.nodeAt(0)

  while (left != null && right != null) {
    if (left.value < right.value) {
      left = append(result, left)
    } else {
      right = append(result, right)
    }
  }

  while (left != null) {
    left = append(result, left)
  }

  while (right != null) {
    right = append(result, right)
  }

  return result
}

private fun <T : Comparable<T>> append(
  result: LinkedList<T>,
  node: Node<T>
): Node<T>? {
  result.append(node.value)
  return node.next
}
