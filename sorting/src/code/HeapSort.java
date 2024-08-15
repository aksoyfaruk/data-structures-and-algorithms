package code;

import given.AbstractArraySort;

/*
 * Implement the heap-sort algorithm here. You can look at the slides for the pseudo-code.
 * Make sure to use the swap and compare functions given in the AbstractArraySort!
 * 
 */

public class HeapSort<K extends Comparable<K>> extends AbstractArraySort<K> {

  // Add any fields here

  public HeapSort() {
    name = "Heapsort";

    // Initialize anything else here
  }

  @Override
  public void sort(K[] inputArray) {
    // TODO: Implement the heap-sort algorithm

    heapify(inputArray);
    for (int end = inputArray.length - 1; end > 0; end--) {
      swap(inputArray, 0, end);
      downheap(inputArray, 0, end);

    }
  }

    // Public since we are going to check its output!
    public void heapify (K[]inputArray){
      // TODO: Heapify the array. See the slides for an O(n) version which uses downheap
      int size = inputArray.length;
      int start = (size / 2) - 1;

      for (int i = start; i >= 0; i--) {
        downheap(inputArray, i, size);
      }
    }

    // The below methods are given given as suggestion. You do not need to use them.
    // Feel free to add more methods


  /*
  protected void downheap(K[] inputArray, int i) {
    // TODO: Implement the downheap method to help with the algorithm
  }
   */
    protected void downheap (K[]inputArray,int i, int size){
      int root = i;
      int left = 2 * i + 1;
      int right = 2 * i + 2;

      if (left < size && compare(inputArray[left], inputArray[root]) > 0) {
        root = left;
      }

      if (right < size && compare(inputArray[right], inputArray[root]) > 0) {
        root = right;
      }

      if (root != i) {
        swap(inputArray, i, root);
        downheap(inputArray, root, size);
      }
    }


}
