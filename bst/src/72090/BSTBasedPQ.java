package code;

import given.Entry;
import given.iAdaptablePriorityQueue;

/*
 * Implement a binary search tree based priority queue
 * Do not try to create heap behavior (e.g. no need for a last node)
 * Just use default binary search tree properties
 */

public class BSTBasedPQ<Key, Value> extends BinarySearchTree<Key, Value> implements iAdaptablePriorityQueue<Key, Value> {

  /*
   * 
   * YOUR CODE BELOW THIS
   * 
   */

    public BSTBasedPQ(){
        super();
    }

    @Override
    public void insert(Key k, Value v) {
        this.put(k, v);
    }

    @Override
    public Entry<Key, Value> pop() {
        if(this.isEmpty()){
            return null;
        }
        Entry<Key, Value> min = this.top();
        this.remove(min.getKey());
        return min;
    }

    @Override
    public Entry<Key, Value> top() {
        if(this.isEmpty()){
            return null;
        }
        this.getNodesInOrder();
        return this.inOrderNodes.get(0);
    }

    @Override
    public Key replaceKey(Entry<Key, Value> entry, Key k) {
        Entry<Key, Value> found = locateNode(this.root, entry.getKey());
        if(found == null)return null;
        if(!entry.getValue().equals(found.getValue()))return null;

        this.remove(found.getKey());
        this.put(k, entry.getValue());

        return entry.getKey();
    }

    @Override
    public Key replaceKey(Value v, Key k) {
        this.getNodesInOrder();
        for(Entry<Key, Value> entry : this.inOrderNodes){
            if(entry.getValue().equals(v)){
                Key oldKey = entry.getKey();
                if(this.remove(entry.getKey()) == null){
                    return null;
                }
                this.put(k, v);
                return oldKey;
            }
        }
        return null;
    }

    @Override
    public Value replaceValue(Entry<Key, Value> entry, Value v) {
        Entry<Key, Value> found = locateNode(this.root, entry.getKey());
        if(found == null)return null;
        if(!entry.getValue().equals(found.getValue()))return null;

        this.remove(found.getKey());
        this.put(entry.getKey(), v);

        return entry.getValue();
    }


}



