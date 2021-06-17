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

package ringbuffer

class RingBuffer<T : Any>(private val size: Int) {

  private var array = ArrayList<T?>(size)
  private var readIndex = 0
  private var writeIndex = 0

  val count: Int
    get() = availableSpaceForReading

  private val availableSpaceForReading: Int
    get() = (writeIndex - readIndex)

  val first: T?
    get() = array.getOrNull(readIndex)

  val isEmpty: Boolean
    get() = (count == 0)

  private val availableSpaceForWriting: Int
    get() = (size - availableSpaceForReading)

  val isFull: Boolean
    get() = (availableSpaceForWriting == 0)

  fun write(element: T): Boolean {
    return if (!isFull) {
      if (array.size < size) {
        array.add(element)
      } else {
        array[writeIndex % size] = element
      }
      writeIndex += 1
      true
    } else {
      false
    }
  }

  fun read(): T? {
    return if (!isEmpty) {
      val element = array[readIndex % size]
      readIndex += 1
      element
    } else {
      null
    }
  }

  override fun toString(): String {
    val values = (0 until availableSpaceForReading).map { offset ->
      "${array[(readIndex + offset) % size]!!}"
    }
    return values.joinToString(prefix = "[", separator = ", ", postfix = "]")
  }

}
