import list.ArrayListQueue
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

typealias Visitor<T> = (TreeNode<T>) -> Unit

class TreeNode<T>(val value: T) {
  private val children: MutableList<TreeNode<T>> = mutableListOf()

  fun add(child: TreeNode<T>) = children.add(child)

  fun forEachDepthFirst(visit: Visitor<T>) {
    visit(this)
    children.forEach {
      it.forEachDepthFirst(visit)
    }
  }

  fun forEachLevelOrder(visit: Visitor<T>) {
    visit(this)
    val queue = ArrayListQueue<TreeNode<T>>()
    children.forEach { queue.enqueue(it) }

    var node = queue.dequeue()
    while (node != null) {
      visit(node)
      node.children.forEach { queue.enqueue(it) }
      node = queue.dequeue()
    }
  }

  fun search(value: T): TreeNode<T>? {
    var result: TreeNode<T>? = null

    forEachLevelOrder {
      if (it.value == value) {
        result = it
      }
    }

    return result
  }
}