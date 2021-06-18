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

package challenge

import java.lang.Math.pow

fun Int.digits(): Int {
  var count = 0
  var num = this
  while (num != 0) {
    count += 1
    num /= 10
  }
  return count
}

fun Int.digit(atPosition: Int): Int? {
  val correctedPosition = (atPosition + 1).toDouble()
  if (correctedPosition > digits()) return null

  var num = this
  while (num / (pow(10.0, correctedPosition).toInt()) != 0) {
    num /= 10
  }
  return num % 10
}


fun MutableList<Int>.lexicographicalSort() {
  val newList = msdRadixSorted(this, 0)
  this.clear()
  this.addAll(newList)
}

private fun msdRadixSorted(list: MutableList<Int>, position: Int): MutableList<Int> {
  if (position >= list.maxDigits()) return list

  // 1
  val buckets = MutableList<MutableList<Int>>(10) { mutableListOf() }
  // 2
  val priorityBucket = arrayListOf<Int>()
  // 3
  list.forEach { number ->
    val digit = number.digit(position)
    if (digit == null) {
      priorityBucket.add(number)
      return@forEach
    }
    buckets[digit].add(number)
  }

  val newValues = buckets.reduce { result, bucket ->
    if (bucket.isEmpty()) return@reduce result
    result.addAll(msdRadixSorted(bucket, position + 1))
    result
  }
  priorityBucket.addAll(newValues)

  return priorityBucket
}

private fun List<Int>.maxDigits(): Int {
  return this.maxOrNull()?.digits() ?: 0
}
