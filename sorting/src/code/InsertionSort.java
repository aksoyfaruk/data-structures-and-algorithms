package code;

import given.AbstractArraySort;

public class InsertionSort<K extends Comparable<K>> extends AbstractArraySort<K> {

  public InsertionSort()
  {
    name = "Insertionsort";
  }
  
  @Override
  public void sort(K[] inputArray) 
  {   
    //TODO: Implement the insertion sort algorithm

      for (int i = 1; i<inputArray.length; i++) {
          K key = inputArray[i];
          int j = i-1;

          while (j >= 0 && compare(inputArray[j], key) > 0) {
              swap(inputArray, j, j+1);
              j = j-1;
          }
          inputArray[j+1] = key;
      }
    
  }

}
