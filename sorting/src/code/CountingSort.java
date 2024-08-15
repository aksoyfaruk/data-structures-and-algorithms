package code;

import given.AbstractArraySort;

/*
 * Implement the c algorithm here. You can look at the slides for the pseudo-codes.
 * You do not have to use swap or compare from the AbstractArraySort here
 * 
 * You may need to cast any K to Integer
 * 
 */

public class CountingSort<K extends Comparable<K>> extends AbstractArraySort<K> {

  //Add any fields here
  
  public CountingSort()
  {
    name = "Countingsort";
  }
  
  @Override
  public void sort(K[] inputArray) {
    
    if(inputArray == null)
    {
      System.out.println("Null array");
      return;
    }
    if(inputArray.length < 1)
    {
      System.out.println("Empty array");
      return;
    }   
    
    if(!(inputArray[0] instanceof Integer)) {
      System.out.println("Can only sort integers!");
      return;
    }
    
    //TODO:: Implement the counting-sort algorithm here
    Integer[] array = (Integer[]) inputArray;

    int maxValue = array[0];
    for (int i = 1; i < array.length; i++) {
      if (array[i] > maxValue) {
        maxValue = array[i];
      }
    }

    int[] count = new int[maxValue + 1];

    for (int i = 0; i < array.length; i++) {
      count[array[i]]++;
    }

    int index = 0;
    for (int a = 0; a < count.length; a++) {
      for (int c = 0; c < count[a]; c++) {
        array[index] = a;
        index++;
      }
    }

    System.arraycopy(array, 0, inputArray, 0, array.length);

  }

}
