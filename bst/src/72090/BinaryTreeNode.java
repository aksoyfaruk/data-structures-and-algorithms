package code;

import given.Entry;

/*
 * The binary node class which extends the entry class.
 * This will be used in linked tree implementations
 * 
 */
public class BinaryTreeNode<Key, Value> extends Entry<Key, Value> {

  /*
   *
   * YOUR CODE HERE
   *
   */

    private BinaryTreeNode<Key, Value> leftChild;
    private BinaryTreeNode<Key, Value> rightChild;
    private BinaryTreeNode<Key, Value> parent;




    public BinaryTreeNode(Key k, Value v) {
    super(k, v);

    /*
     *
     * This constructor is needed for the autograder. You can fill the rest to your liking.
     * YOUR CODE AFTER THIS:
     *
     */
    this.leftChild = null;
    this.rightChild = null;
    this.parent = null;
  }


// getters and setters
    public BinaryTreeNode<Key, Value> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTreeNode<Key, Value> leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTreeNode<Key, Value> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTreeNode<Key, Value> rightChild) {
        this.rightChild = rightChild;
    }

    public BinaryTreeNode<Key, Value> getParent() {
        return parent;
    }

    public void setParent(BinaryTreeNode<Key, Value> parent) {
        this.parent = parent;
    }




}
