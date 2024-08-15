package code;

import given.AbstractArraySort;

/*
 * Implement the merge-sort algorithm here. You can look at the slides for the pseudo-codes.
 * Make sure to use the swap and compare functions given in the AbstractArraySort!
 * 
 * You may need to create an Array of K (Hint: the auxiliary array). 
 * Look at the previous assignments on how we did this!
 * 
 */

public class MergeSort<K extends Comparable<K>> extends AbstractArraySort<K> {

  // Add any fields here
  private K[] temp;
  public MergeSort() {
    name = "Mergesort";

    // Initialize anything else here

  }

  @Override
  public void sort(K[] inputArray) {
    // TODO: Implement the merge-sort algorithm
    temp = (K[]) new Comparable[inputArray.length];
    sort(inputArray, 0, inputArray.length - 1);

  }

  // Public since we are going to check its output!
  public void merge(K[] inputArray, int lo, int mid, int hi) {
    // TODO: Implement the merging algorithm
    int a = lo;
    int b = mid + 1;

    for (int i = lo; i <= hi; i++) {
      temp[i] = inputArray[i];
    }

    for (int i = lo; i <= hi; i++) {
      if (a > mid) {
        inputArray[i] = temp[b++];
      } else if (b > hi) {
        inputArray[i] = temp[a++];
      } else if (compare(temp[b], temp[a]) < 0) {
        inputArray[i] = temp[b++];
      } else {
        inputArray[i] = temp[a++];
      }
    }
  }
  
  // Feel free to add more methods
  private void sort(K[] inputArray, int lo, int hi) {
    if (hi <= lo) {
      return;
    }

    int mid = lo + (hi - lo) / 2;
    sort(inputArray, lo, mid);
    sort(inputArray, mid + 1, hi);
    merge(inputArray, lo, mid, hi);
  }

}
