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

class AVLTree<T : Comparable<T>> {

  var root: AVLNode<T>? = null

  fun insert(value: T) {
    root = insert(root, value)
  }

  private fun insert(node: AVLNode<T>?, value: T): AVLNode<T> {
    node ?: return AVLNode(value)

    if (value < node.value) {
      node.leftChild = insert(node.leftChild, value)
    } else {
      node.rightChild = insert(node.rightChild, value)
    }

    return node
  }

  fun remove(value: T) {
    root = remove(root, value)
  }

  private fun remove(node: AVLNode<T>?, value: T): AVLNode<T>? {
    node ?: return null

    when {
      value == node.value -> {
        // 1
        if (node.leftChild == null && node.rightChild == null) {
          return null
        }
        // 2
        if (node.leftChild == null) {
          return node.rightChild
        }
        // 3
        if (node.rightChild == null) {
          return node.leftChild
        }
        // 4
        node.rightChild?.min?.value?.let {
          node.value = it
        }

        node.rightChild = remove(node.rightChild, node.value)
      }
      value < node.value -> node.leftChild = remove(node.leftChild, value)
      else -> node.rightChild = remove(node.rightChild, value)
    }
    return node
  }

  override fun toString() = root?.toString() ?: "empty tree"

  fun contains(value: T): Boolean {
    // 1
    var current = root

    // 2
    while (current != null) {
      // 3
      if (current.value == value) {
        return true
      }

      // 4
      current = if (value < current.value) {
        current.leftChild
      } else {
        current.rightChild
      }
    }

    return false
  }

}
