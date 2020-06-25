typealias Visitor<T> = (T) -> Unit

class BinaryNode<T>(val value: T) {

  var leftChild: BinaryNode<T>? = null
  var rightChild: BinaryNode<T>? = null

  override fun toString() = diagram(this)

  private fun diagram(node: BinaryNode<T>?,
                      top: String = "",
                      root: String = "",
                      bottom: String = ""): String {
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






