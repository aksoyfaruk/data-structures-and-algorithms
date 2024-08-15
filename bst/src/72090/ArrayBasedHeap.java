package code;

import java.util.ArrayList;
import java.util.Comparator;

import given.Entry;
import given.iAdaptablePriorityQueue;

/*
 * Implement an array based heap
 * Note that you can just use Entry here!
 * 
 */


public class ArrayBasedHeap<Key , Value> implements iAdaptablePriorityQueue<Key, Value> {
  
  // Use this arraylist to store the nodes of the heap. 
  // This is required for the autograder. 
  // It makes your implementation more verbose (e.g. nodes[i] vs nodes.get(i)) but then you do not have to deal with dynamic resizing
  protected ArrayList<Entry<Key,Value>> nodes;
  
  /*
   * 
   * YOUR CODE BELOW THIS
   * 
   */

  protected Comparator<Key> comparator;

  public ArrayBasedHeap() {
      this.nodes = new ArrayList<>();
  }

  @Override
  public int size() {
      return nodes.size();
  }

  @Override
  public boolean isEmpty() {
      return nodes.isEmpty();
  }


  @Override
  public void setComparator(Comparator<Key> C) {
      this.comparator = C;
  }

  @Override
  public Comparator<Key> getComparator() {
      return comparator;
  }


  @Override
  public void insert(Key k, Value v) {
      Entry<Key, Value> newEntry = new Entry<>(k, v);
      nodes.add(newEntry);
      heapUp(nodes.size() - 1);
  }


  @Override
  public Entry<Key, Value> pop() {
      if (nodes.isEmpty()) return null;
      Entry<Key, Value> removed = nodes.get(0);
      Entry<Key, Value> last = nodes.remove(nodes.size() - 1);
      if (!nodes.isEmpty()) {
          nodes.set(0, last);
          heapDown(0);
      }
      return removed;
  }


  @Override
  public Entry<Key, Value> top() {
      if(nodes.isEmpty()) return null;
      return nodes.get(0);
  }


  @Override
  public Value remove(Key k) {
      int index = -1;
      for (int i = 0; i < nodes.size(); i++) {
          if (nodes.get(i).getKey().equals(k)) {
              index = i;
              break;
          }
      }
      if (index == -1) return null;
      Value removedValue = nodes.get(index).getValue();
      //swap with the last element and remove it
      swap(index, nodes.size() - 1);
      nodes.remove(nodes.size() - 1);
      //if not the last element, need to reheapify
      if (index < nodes.size()) {
          heapDown(index);
          heapUp(index);
      }
      return removedValue;
  }

    @Override
    public Key replaceKey(Entry<Key, Value> entry, Key newKey) {
        int index = nodes.indexOf(entry);
        if (index == -1) {
            return null;
        }
        //capture the old key before making any changes
        Key oldKey = nodes.get(index).getKey();
        //remove the entry by its old key
        this.remove(oldKey);
        //insert the new key-value pair into the heap
        this.insert(newKey, entry.getValue());
        //rebuild the heap to ensure it maintains the heap property
        reheap();
        //return the old key
        return oldKey;
    }



    @Override
    public Key replaceKey(Value value, Key newKey) {
        int index = -1;
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getValue().equals(value)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return null;
        }

        Key oldKey = nodes.get(index).getKey();
        nodes.get(index).setKey(newKey);
        if (comparator.compare(newKey, oldKey) < 0) {
            //if the new key is smaller than the old key, we heap up
            heapUp(index);
        } else {
            //if the new key is larger than the old key, we heap down
            heapDown(index);
        }

        return oldKey;
    }



  @Override
  public Value replaceValue(Entry<Key, Value> entry, Value newValue) {
      for (Entry<Key, Value> heapEntry : nodes) {
          if (heapEntry.getKey().equals(entry.getKey())) {
              Value oldValue = heapEntry.getValue();
              heapEntry.setValue(newValue);
              return oldValue;
          }
      }
      return null;
  }

  
  
  //HELPERS
  
  private void heapUp(int index) {
	    while (index > 0) {
	        int parentIndex = (index - 1) / 2;
	        if (comparator.compare(nodes.get(index).getKey(), nodes.get(parentIndex).getKey()) >= 0) {
	            break;
	        }
	        swap(index, parentIndex);
	        index = parentIndex;
	    }
	}

	private void heapDown(int index) {
	    while (index < nodes.size() / 2) { // node has at least one child
	        int leftChild = 2 * index + 1;
	        int rightChild = leftChild + 1;
	        int smallest = leftChild; // assume leftChild is smaller
	        
	        if (rightChild < nodes.size() && comparator.compare(nodes.get(rightChild).getKey(), nodes.get(leftChild).getKey()) < 0) {
	            smallest = rightChild;
	        }
	        if (comparator.compare(nodes.get(index).getKey(), nodes.get(smallest).getKey()) <= 0) {
	            break;
	        }
	        swap(index, smallest);
	        index = smallest;
	    }
	}

    private void reheap() {
        int startIdx = (nodes.size() / 2) - 1;
        for (int i = startIdx; i >= 0; i--) {
            heapDown(i);
        }
    }

	private void swap(int i, int j) {
	    Entry<Key, Value> tmp = nodes.get(i);
	    nodes.set(i, nodes.get(j));
	    nodes.set(j, tmp);
	}

  
}
