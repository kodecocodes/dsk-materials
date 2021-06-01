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

fun main() {
  "max priority queue" example {
    val priorityQueue = ComparablePriorityQueueImpl<Int>()
    arrayListOf(1, 12, 3, 4, 1, 6, 8, 7).forEach {
      priorityQueue.enqueue(it)
    }
    while (!priorityQueue.isEmpty) {
      println(priorityQueue.dequeue())
    }
  }

  "min priority queue" example {
    val stringLengthComparator = Comparator<String> { o1, o2 ->
      val length1 = o1?.length ?: -1
      val length2 = o2?.length ?: -1
      length1 - length2
    }
    val priorityQueue = ComparatorPriorityQueueImpl(stringLengthComparator)
    arrayListOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine").forEach {
      priorityQueue.enqueue(it)
    }
    while (!priorityQueue.isEmpty) {
      println(priorityQueue.dequeue())
    }
  }

  "max priority array list based queue" example {
    val priorityQueue = CustomPriorityQueueArrayList<Int>()
    arrayListOf(1, 12, 3, 4, 1, 6, 8, 7).forEach {
      priorityQueue.enqueue(it)
    }
    priorityQueue.enqueue(5)
    priorityQueue.enqueue(0)
    priorityQueue.enqueue(10)
    while (!priorityQueue.isEmpty) {
      println(priorityQueue.dequeue())
    }
  }

  "concert line" example {
    val p1 = Person("Josh", 21, true)
    val p2 = Person("Jake", 22, true)
    val p3 = Person("Clay", 28, false)
    val p4 = Person("Cindy", 28, false)
    val p5 = Person("Sabrina", 30, false)
    val priorityQueue = ComparatorPriorityQueueImpl(MilitaryPersonComparator)
    arrayListOf(p1, p2, p3, p4, p5).forEach {
      priorityQueue.enqueue(it)
    }
    while (!priorityQueue.isEmpty) {
      println(priorityQueue.dequeue())
    }
  }

}
