package code;

import given.AbstractArraySort;

/*
 * Implement the quick-sort algorithm here. You can look at the slides for the pseudo-codes.
 * Make sure to use the swap and compare functions given in the AbstractArraySort!
 * 
 */

public class QuickSort<K extends Comparable<K>> extends AbstractArraySort<K> {  
  //Add any fields here
  
  public QuickSort()
  {
    name = "Quicksort";
    
  //Initialize anything else here
  }
  
  //useful if we want to return a pair of indices from the partition function.
  //You do not need to use this if you are just returning and integer from the partition
  public class indexPair {
    public int p1, p2;
    
    indexPair(int pos1, int pos2)
    {
      p1 = pos1;
      p2 = pos2;
    }
    
    public String toString()
    {
      return "(" + Integer.toString(p1) + ", " + Integer.toString(p2) + ")";
    }
  }
  
  
  @Override
  public void sort(K[] inputArray)
  {
    //TODO:: Implement the quicksort algorithm here
    quickSort(inputArray, 0, inputArray.length - 1);

  }

  // Public since we are going to check its output!
  /*
  public indexPair partition(K[] inputArray, int lo, int hi, int p)
  {
  //TODO:: Implement a partitioning function here
    K pivot = inputArray[p];
    int a = lo;
    int b = hi;

    while(a <= b) {
      while(compare(inputArray[a], pivot) < 0) {
        a++;
      }
      while(compare(inputArray[b], pivot) > 0) {
        b--;
      }
      if(a <= b) {
        swap(inputArray, a, b);
        a++;
        b--;
      }
  }
  return new indexPair(a, b);
  }

   */

  
   //Alternative, if you are just returning an integer
  public int partition(K[] inputArray, int lo, int hi, int p)
  {
    //TODO:: Implement a partitioning function here
    K pivot = inputArray[hi];
    int i = lo - 1;
    for (int j = lo; j < hi; j++) {
      if (compare(inputArray[j], pivot) <= 0) {
        i++;
        swap(inputArray, i, j);
      }
    }
    swap(inputArray, i + 1, hi);
    return i + 1;
  }
  
  //The below methods are given given as suggestion. You do not need to use them.
  // Feel free to add more methods  
  protected int pickPivot(K[] inpuArray, int lo, int hi)
  {
    //TODO: Pick a pivot selection method and implement it
    K start = inpuArray[lo];
    K mid = inpuArray[(lo + hi) / 2];
    K end = inpuArray[hi];

    if (compare(start, mid) > 0) {
      if (compare(start, end)> 0) {
        if (compare(mid, end) > 0) {
          return (lo + hi) / 2;
        } else {
          return hi;
        }
      } else {
        return lo;
      }
    }
    else {
      if (compare(mid, end) > 0) {
        if (compare(start, end) > 0) {
          return lo;
        } else {
          return hi;
        }
      } else {
        return (lo + hi) / 2;
      }
    }
  }

  private void quickSort(K[] inputArray, int low, int high) {
    if (low < high) {
      int pivotIndex = pickPivot(inputArray, low, high);
      int pivotNewIndex = partition(inputArray, low, high, pivotIndex);
      quickSort(inputArray, low, pivotNewIndex - 1);
      quickSort(inputArray, pivotNewIndex + 1, high);
    }
  }


}

