package code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import given.iMap;
import given.iBinarySearchTree;

/*
 * Implement a vanilla binary search tree using a linked tree representation
 * Use the BinaryTreeNode as your node class
 */

public class BinarySearchTree<Key, Value> implements iBinarySearchTree<Key, Value>, iMap<Key, Value> {

    /*
     *
     * YOUR CODE BELOW THIS
     *
     */

    protected BinaryTreeNode<Key, Value> root;
    protected Comparator<Key> comparator;
    protected List<BinaryTreeNode<Key, Value>> inOrderNodes;

    public BinarySearchTree() {
        this.root = null;
        this.inOrderNodes = new LinkedList<>();
    }


    // find a value based on its key
    @Override
    public Value get(Key key) {
        BinaryTreeNode<Key, Value> resultNode = locateNode(root, key);
        if (resultNode != null) {
            return resultNode.getValue();
        }
        else { // if key is not found
            return null;
        }
    }

    public BinaryTreeNode<Key, Value> locateNode(BinaryTreeNode<Key, Value> current, Key key) {
        if (current == null) {
            return null; // base case: key not found
        }
        int compareResult = comparator.compare(current.getKey(), key);
        if (compareResult == 0) {
            return current; // key found
        }
        else if (compareResult < 0) {
            return locateNode(current.getRightChild(), key); // search right subtree
        }
        else {
            return locateNode(current.getLeftChild(), key); // search left subtree
        }
    }

    public Value put(Key key, Value value) {
        if (key == null || value == null) {
            return null;
        }

        if (root == null) {
            root = new BinaryTreeNode<>(key, value); //if the tree is empty, set the new node as the root
            return null;
        }

        BinaryTreeNode<Key, Value> current = root; //start from the root
        BinaryTreeNode<Key, Value> parent = null;
        int cmp = 0; //store comparison results


        while (current != null) {
            parent = current; //update the parent
            cmp = comparator.compare(key, current.getKey());
            if (cmp < 0) {
                current = current.getLeftChild(); //move left if new key is smaller
            }
            else if (cmp > 0) {
                current = current.getRightChild(); //move right if new key is larger
            }
            else {
                Value oldValue = current.getValue();
                current.setValue(value);
                return oldValue;
            }
        }


        BinaryTreeNode<Key, Value> newNode = new BinaryTreeNode<>(key, value);
        if (cmp < 0) {
            parent.setLeftChild(newNode); //set as left child
        }
        else {
            parent.setRightChild(newNode); //set as right child
        }
        newNode.setParent(parent); //link back

        return null;
    }


    @Override
    public Value remove(Key key) {
        if (this.root == null || key == null) {
            return null;
        }

        BinaryTreeNode<Key, Value> parent = null;
        BinaryTreeNode<Key, Value> current = this.root;
        int cmp = 0;

        while (current != null) {
            cmp = comparator.compare(key, current.getKey());
            if (cmp < 0) {
                parent = current;
                current = current.getLeftChild();
            }
            else if (cmp > 0) {
                parent = current;
                current = current.getRightChild();
            }
            else {
                break;
            }
        }

        if (current == null) {
            return null;
        }
        Value oldValue = current.getValue();

        //node to remove has two children
        if (current.getLeftChild() != null && current.getRightChild() != null) {
            BinaryTreeNode<Key, Value> successor = current.getRightChild();
            BinaryTreeNode<Key, Value> successorParent = current;
            while (successor.getLeftChild() != null) {
                successorParent = successor;
                successor = successor.getLeftChild();
            }
            current.setKey(successor.getKey());
            current.setValue(successor.getValue());
            current = successor;
            parent = successorParent;
        }

        //current has at most one child
        BinaryTreeNode<Key, Value> child = (current.getLeftChild() != null) ? current.getLeftChild() : current.getRightChild();

        if (parent == null) {
            this.root = child;
        }
        else {
            if (current == parent.getLeftChild()) {
                parent.setLeftChild(child);
            }
            else {
                parent.setRightChild(child);
            }
        }

        if (child != null) {
            child.setParent(parent);
        }
        return oldValue;
    }

    @Override
    public Iterable<Key> keySet() {
        List<Key> keys = new ArrayList<>();
        fillKeys(root, keys); //collect keys in order
        return keys;
    }

    private void fillKeys(BinaryTreeNode<Key, Value> node, List<Key> keysList) {
        if (node != null) {
            fillKeys(node.getLeftChild(), keysList); //recursively add left subtree
            keysList.add(node.getKey());
            fillKeys(node.getRightChild(), keysList); //add right subtree
        }
    }

    @Override
    public int size() {
        return countNodes(root); // count nodes starting from the root
    }

    private int countNodes(BinaryTreeNode<Key, Value> node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.getLeftChild()) + countNodes(node.getRightChild());
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public BinaryTreeNode<Key, Value> getRoot() {
        return root;
    }

    @Override
    public BinaryTreeNode<Key, Value> getParent(BinaryTreeNode<Key, Value> node) {
        if (node!=null){
            return node.getParent();
        }
        else {
            return null;
        }
    }

    @Override
    public boolean isInternal(BinaryTreeNode<Key, Value> node) {
        if (node!=null){
            return (node.getRightChild() != null || node.getLeftChild() != null);
        }
        else {
            return false;
        }
    }

    @Override
    public boolean isExternal(BinaryTreeNode<Key, Value> node) {
        if (node!=null){
            return (node.getRightChild() == null && node.getLeftChild() == null);
        }
        else {
            return true;
        }
    }

    @Override
    public boolean isRoot(BinaryTreeNode<Key, Value> node) {
        return this.root.equals(node);
    }

    @Override
    public BinaryTreeNode<Key, Value> getNode(Key k) {
        return locateNode(this.root, k);
    }


    @Override
    public Value getValue(Key key) {
        BinaryTreeNode<Key, Value> node = this.getNode(key);
        return (node != null) ? node.getValue() : null;
    }

    @Override
    public BinaryTreeNode<Key, Value> getLeftChild(BinaryTreeNode<Key, Value> node) {
        return (node != null) ? node.getLeftChild() : null;
    }

    @Override
    public BinaryTreeNode<Key, Value> getRightChild(BinaryTreeNode<Key, Value> node) {
        return (node != null) ? node.getRightChild() : null;
    }

    @Override
    public BinaryTreeNode<Key, Value> sibling(BinaryTreeNode<Key, Value> node) {
        if (node == null || node.getParent() == null) return null;
        BinaryTreeNode<Key, Value> parent = node.getParent();
        return (parent.getLeftChild() != null && parent.getLeftChild().equals(node)) ? parent.getRightChild() : parent.getLeftChild();
    }

    @Override
    public boolean isLeftChild(BinaryTreeNode<Key, Value> node) {
        return node != null && node.getParent() != null && node.equals(node.getParent().getLeftChild());
    }

    @Override
    public boolean isRightChild(BinaryTreeNode<Key, Value> node) {
        return node != null && node.getParent() != null && node.equals(node.getParent().getRightChild());
    }


    @Override
    public List<BinaryTreeNode<Key, Value>> getNodesInOrder() {
        this.inOrderNodes.clear();
        inOrderTraversalAdd(this.root);
        return new ArrayList<>(this.inOrderNodes);
    }

    private void inOrderTraversalAdd(BinaryTreeNode<Key, Value> currentNode) {
        if (currentNode == null) {
            return;
        }
        inOrderTraversalAdd(currentNode.getLeftChild());
        this.inOrderNodes.add(currentNode);
        inOrderTraversalAdd(currentNode.getRightChild());
    }


    @Override
    public void setComparator(Comparator<Key> comparator) {
        this.comparator = comparator;
    }

    @Override
    public Comparator<Key> getComparator() {
        return this.comparator;
    }

    @Override
    public BinaryTreeNode<Key, Value> ceiling(Key key) {
        BinaryTreeNode<Key, Value> target = null;
        BinaryTreeNode<Key, Value> temp = this.root;

        while (temp != null) {
            int compareResult = this.comparator.compare(temp.getKey(), key);
            if (compareResult == 0) {
                return temp;
            } else if (compareResult < 0) {
                temp = temp.getRightChild();
            } else {
                target = temp;
                temp = temp.getLeftChild();
            }
        }
        return target;
    }

    @Override
    public BinaryTreeNode<Key, Value> floor(Key key) {
        BinaryTreeNode<Key, Value> target = null;
        BinaryTreeNode<Key, Value> temp = this.root;

        while (temp != null) {
            int compareResult = this.comparator.compare(temp.getKey(), key);
            if (compareResult == 0) {
                return temp;
            } else if (compareResult > 0) {
                temp = temp.getLeftChild();
            } else {
                target = temp;
                temp = temp.getRightChild();
            }
        }
        return target;
    }









}






