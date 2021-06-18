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

fun <T : Comparable<T>> ArrayList<T>.binarySearch(
  value: T,
  range: IntRange = indices
): Int? {
  // 1
  if (range.first > range.last) {
    return null
  }

  // 2
  val size = range.last - range.first + 1
  val middle = range.first + size / 2

  return when {
    // 3
    this[middle] == value -> middle
    // 4
    this[middle] > value -> binarySearch(value, range.first until middle)
    else -> binarySearch(value, (middle + 1)..range.last)
  }
}

fun <T : Comparable<T>> ArrayList<T>.findIndices(
  value: T
): IntRange? {
  val startIndex = startIndex(value, indices) ?: return null
  val endIndex = endIndex(value, indices) ?: return null

  return startIndex until endIndex
}

private fun <T : Comparable<T>> ArrayList<T>.startIndex(
  value: T,
  range: IntRange
): Int? {
  // 1
  val middleIndex = range.start + (range.last - range.start + 1) / 2

  // 2
  if (middleIndex == 0 || middleIndex == size - 1) {
    return if (this[middleIndex] == value) {
      middleIndex
    } else {
      null
    }
  }

  // 3
  return if (this[middleIndex] == value) {
    if (this[middleIndex - 1] != value) {
      middleIndex
    } else {
      startIndex(value, range.start until middleIndex)
    }
  } else if (value < this[middleIndex]) {
    startIndex(value, range.start until middleIndex)
  } else {
    startIndex(value, (middleIndex + 1)..range.last)
  }
}

private fun <T : Comparable<T>> ArrayList<T>.endIndex(
  value: T,
  range: IntRange
): Int? {
  val middleIndex = range.start + (range.last - range.start + 1) / 2

  if (middleIndex == 0 || middleIndex == size - 1) {
    return if (this[middleIndex] == value) {
      middleIndex + 1
    } else {
      null
    }
  }

  return if (this[middleIndex] == value) {
    if (this[middleIndex + 1] != value) {
      middleIndex + 1
    } else {
      endIndex(value, (middleIndex + 1)..range.last)
    }
  } else if (value < this[middleIndex]) {
    endIndex(value, range.start until middleIndex)
  } else {
    endIndex(value, (middleIndex + 1)..range.last)
  }
}
