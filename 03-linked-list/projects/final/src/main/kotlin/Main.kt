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
  "creating and linking nodes" example {
    val node1 = Node(value = 1)
    val node2 = Node(value = 2)
    val node3 = Node(value = 3)

    node1.next = node2
    node2.next = node3

    println(node1)
  }

  "push" example {
    val list = LinkedList<Int>()
    list.push(3)
    list.push(2)
    list.push(1)

    println(list)
  }

  "fluent interface push" example {
    val list = LinkedList<Int>()
    list.push(3).push(2).push(1)
    println(list)
  }

  "append" example {
    val list = LinkedList<Int>()
    list.append(1)
    list.append(2)
    list.append(3)

    println(list)
  }

  "inserting at a particular index" example {
    val list = LinkedList<Int>()
    list.push(3)
    list.push(2)
    list.push(1)

    println("Before inserting: $list")
    var middleNode = list.nodeAt(1)!!
    for (i in 1..3) {
      middleNode = list.insert(-1 * i, middleNode)
    }
    println("After inserting: $list")
  }

  "pop" example {
    val list = LinkedList<Int>()
    list.push(3)
    list.push(2)
    list.push(1)

    println("Before popping list: $list")
    val poppedValue = list.pop()
    println("After popping list: $list")
    println("Popped value: $poppedValue")
  }

  "removing the last node" example {
    val list = LinkedList<Int>()
    list.push(3)
    list.push(2)
    list.push(1)

    println("Before removing last node: $list")
    val removedValue = list.removeLast()

    println("After removing last node: $list")
    println("Removed value: $removedValue")
  }

  "removing a node after a particular node" example {
    val list = LinkedList<Int>()
    list.push(3)
    list.push(2)
    list.push(1)

    println("Before removing at particular index: $list")
    val index = 1
    val node = list.nodeAt(index - 1)!!
    val removedValue = list.removeAfter(node)

    println("After removing at index $index: $list")
    println("Removed value: $removedValue")
  }

  "printing doubles" example {
    val list = LinkedList<Int>()
    list.push(3)
    list.push(2)
    list.push(1)
    println(list)

    for (item in list) {
      println("Double: ${item * 2}")
    }
  }

  "removing elements" example {
    val list: MutableCollection<Int> = LinkedList()
    list.add(3)
    list.add(2)
    list.add(1)

    println(list)
    list.remove(1)
    println(list)
  }

  "retaining elements" example {
    val list: MutableCollection<Int> = LinkedList()
    list.add(3)
    list.add(2)
    list.add(1)
    list.add(4)
    list.add(5)

    println(list)
    list.retainAll(listOf(3, 4, 5))
    println(list)
  }

  "remove all elements" example {
    val list: MutableCollection<Int> = LinkedList()
    list.add(3)
    list.add(2)
    list.add(1)
    list.add(4)
    list.add(5)

    println(list)
    list.removeAll(listOf(3, 4, 5))
    println(list)
  }

}
