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

package mergesort


fun <T : Comparable<T>> List<T>.mergeSort(): List<T> {
  if (this.size < 2) return this
  val middle = this.size / 2
  val left = this.subList(0, middle).mergeSort()
  val right = this.subList(middle, this.size).mergeSort()

  return merge(left, right)
}

private fun <T : Comparable<T>> merge(left: List<T>, right: List<T>): List<T> {
  // 1
  var leftIndex = 0
  var rightIndex = 0
  // 2
  val result = mutableListOf<T>()
  // 3
  while (leftIndex < left.size && rightIndex < right.size) {
    val leftElement = left[leftIndex]
    val rightElement = right[rightIndex]
    // 4
    if (leftElement < rightElement) {
      result.add(leftElement)
      leftIndex += 1
    } else if (leftElement > rightElement) {
      result.add(rightElement)
      rightIndex += 1
    } else {
      result.add(leftElement)
      leftIndex += 1
      result.add(rightElement)
      rightIndex += 1
    }
  }
  // 5
  if (leftIndex < left.size) {
    result.addAll(left.subList(leftIndex, left.size))
  }
  if (rightIndex < right.size) {
    result.addAll(right.subList(rightIndex, right.size))
  }
  return result
}
