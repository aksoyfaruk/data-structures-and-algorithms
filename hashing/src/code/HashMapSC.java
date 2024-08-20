package code;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import given.AbstractHashMap;
import given.HashEntry;

/*
 * The file should contain the implementation of a hashmap with:
 * - Separate Chaining for collision handling
 * - Multiply-Add-Divide (MAD) for compression: (a*k+b) mod p
 * - Java's own linked lists for the secondary containers
 * - Resizing (to double its size) and rehashing when the load factor gets above a threshold
 *   Note that for this type of hashmap, load factor can be higher than 1
 * 
 * Some helper functions are provided to you. We suggest that you go over them.
 * 
 * You are not allowed to use any existing java data structures other than for the buckets (which have been 
 * created for you) and the keyset method
 */

public class HashMapSC<Key, Value> extends AbstractHashMap<Key, Value> {

  // The underlying array to hold hash entry Lists
  LinkedList<HashEntry<Key, Value>>[] buckets;

  // Note that the Linkedlists are still not initialized!
  @SuppressWarnings("unchecked")
  protected void resizeBuckets(int newSize) {
    // Update the capacity
    N = nextPrime(newSize);
    buckets = (LinkedList<HashEntry<Key, Value>>[]) Array.newInstance(LinkedList.class, N);
  }

  /*
   * ADD MORE FIELDS IF NEEDED
   * 
   */
  private HashEntry<Key, Value> nullEntry;


  // The threshold of the load factor for resizing
  protected float criticalLoadFactor;

  /*
   * ADD A NESTED CLASS IF NEEDED
   * 
   */

  public int hashValue(Key key, int iter) {
    return hashValue(key);
  }

  public int hashValue(Key key) {
    // TODO: Implement the hashvalue computation with the MAD method. Will be almost
    // the same as the primaryHash method of HashMapDH
    return Math.abs((a * Math.abs(key.hashCode()) + b) % P) % N;
  }

  // Default constructor
  public HashMapSC() {
    this(101);
  }

  public HashMapSC(int initSize) {
    // High criticalAlpha for representing average items in a secondary container
    this(initSize, 10f);
  }

  public HashMapSC(int initSize, float criticalAlpha) {
    N = initSize;
    criticalLoadFactor = criticalAlpha;
    resizeBuckets(N);
    
    // Set up the MAD compression and secondary hash parameters
    updateHashParams();



    /*
     * ADD MORE CODE IF NEEDED
     * 
     */
  }

  /*
   * ADD MORE METHODS IF NEEDED
   * 
   */
  public int size() {
    return n;
  }

  @Override
  public Value get(Key k) {
    // TODO Auto-generated method stub
    if (k == null) {
      return nullEntry == null ? null : nullEntry.getValue(); // Directly return the value for null key
    }

    int index = hashValue(k);
    LinkedList<HashEntry<Key, Value>> bucket = buckets[index];
    if (bucket != null) {
      for (HashEntry<Key, Value> entry : bucket) {
        if (k.equals(entry.getKey())) {
          return entry.getValue(); // return the value associated with the key
        }
      }
    }
    return null;
  }
/*
  @Override
  public Value put(Key k, Value v) {
    // TODO Auto-generated method stub
    // Do not forget to resize if needed!
    // Note that the linked lists are not initialized!
    if (k == null) {
      if (nullEntry == null) {
        nullEntry = new HashEntry<>(null, v);
        n++; // Only increment n if no previous null key existed
        return null;
      } else {
        Value oldValue = nullEntry.getValue();
        nullEntry.setValue(v);
        return oldValue;
      }
    }

    int index = hashValue(k);
    LinkedList<HashEntry<Key, Value>> bucket = buckets[index];
    if (bucket == null) {
      bucket = new LinkedList<>();
      bucket.add(new HashEntry<>(k, v));
      buckets[index] = bucket;
      n++;
    } else {
      for (HashEntry<Key, Value> entry : bucket) {
        if (k.equals(entry.getKey())) {
          Value oldValue = entry.getValue();
          entry.setValue(v);
          return oldValue;
        }
      }
      bucket.add(new HashEntry<>(k, v));
      n++;
    }
    checkAndResize();
    return null;
  }


 */
@Override
public Value put(Key k, Value v) {
  if (k == null) {
    if (nullEntry == null) {
      nullEntry = new HashEntry<>(null, v);
      n++;  // Correctly handled for null keys
      return null;
    } else {
      Value oldValue = nullEntry.getValue();
      nullEntry.setValue(v);
      return oldValue;  // Correctly does not change n if null key was already present
    }
  }

  int index = hashValue(k);
  LinkedList<HashEntry<Key, Value>> bucket = buckets[index];
  if (bucket == null) {
    bucket = new LinkedList<>();
    buckets[index] = bucket;
  }
  for (HashEntry<Key, Value> entry : bucket) {
    if (k.equals(entry.getKey())) {
      Value oldValue = entry.getValue();
      entry.setValue(v);
      return oldValue;  // Should not increment n if key exists
    }
  }
  bucket.add(new HashEntry<>(k, v));
  n++;  // Increment n only if new key is added
  checkAndResize();


  System.out.println("Putting key: " + k + " | New size: " + n);
  return null;
}

/*
  @Override
  public Value remove(Key k) {
    // TODO Auto-generated method stub
    if (k == null) {
      if (nullEntry != null) {
        Value oldValue = nullEntry.getValue();
        nullEntry = null;
        n--;
        return oldValue;
      }
      return null; // No null entry to remove
    }

    int index = hashValue(k);
    LinkedList<HashEntry<Key, Value>> bucket = buckets[index];
    if (bucket == null) {
      return null; // key is not in the hash map
    } else {
      for (HashEntry<Key, Value> entry : bucket) {
        if (k.equals(entry.getKey())) {
          Value value = entry.getValue();
          bucket.remove(entry);
          n--;
          return value; // return the value associated with the key
        }
      }
    }
    return null;
  }

 */

  @Override
  public Value remove(Key k) {
    if (k == null) {
      if (nullEntry != null) {
        Value oldValue = nullEntry.getValue();
        nullEntry = null;
        n--;  // Correctly decrement n
        return oldValue;
      }
      return null;  // No change to n if no null key was set
    }

    int index = hashValue(k);
    LinkedList<HashEntry<Key, Value>> bucket = buckets[index];
    if (bucket != null) {


      Iterator<HashEntry<Key, Value>> iter = bucket.iterator();
      while (iter.hasNext()) {
        HashEntry<Key, Value> entry = iter.next();
        if (k.equals(entry.getKey())) {
          Value value = entry.getValue();
          iter.remove();  // Use iterator's remove method to avoid ConcurrentModificationException
          n--;  // Decrement n only if key is found
          return value;
        }
      }
    }
    System.out.println("Removing key: " + k + " | New size: " + n);
    return null;
  }


  @Override
  public Iterable<Key> keySet() {
    // TODO Auto-generated method stub
    ArrayList<Key> keys = new ArrayList<>();
    for (LinkedList<HashEntry<Key, Value>> bucket : buckets) {
      if (bucket != null) {
        for (HashEntry<Key, Value> entry : bucket) {
          keys.add(entry.getKey());
        }
      }
    }
    return keys;
  }

  /**
   * checkAndResize checks whether the current load factor is greater than the
   * specified critical load factor. If it is, the table size should be increased
   * to 2*N and recreate the hash table for the keys (rehashing). Do not forget to
   * re-calculate the hash parameters and do not forget to re-populate the new
   * array!
   */


  protected void checkAndResize() {
    if (loadFactor() > criticalLoadFactor) {
      LinkedList<HashEntry<Key, Value>>[] oldBuckets = buckets;
      resizeBuckets(2 * N);
      // No need to reset n here
      for (LinkedList<HashEntry<Key, Value>> bucket : oldBuckets) {
        if (bucket != null) {
          for (HashEntry<Key, Value> entry : bucket) {
            int newIndex = hashValue(entry.getKey());  // Compute new index for rehashed entry
            if (buckets[newIndex] == null) {
              buckets[newIndex] = new LinkedList<>();
            }
            buckets[newIndex].add(entry);  // Add entry to new bucket without changing n
          }
        }
      }
    }
  }
}
