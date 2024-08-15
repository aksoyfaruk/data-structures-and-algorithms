package ds;

public class MyQueue {
	
    private Node head;
    private Node tail;
    private int size = 0;

    // Adds a new element to the end of the queue
    public void enqueue(String data) {
    	//TODO 
    	Node queueNode = new Node(data);
    	if (tail != null) {
    		tail.next = queueNode;
    
    	}
    	tail = queueNode;
    	if (head == null) {
    		head = queueNode;
    	}
    	size++;
    }
    
    

    // Removes and returns the element at the front of the queue
    public String dequeue() {
    	//TODO 
//    	return null;
    	String data = head.data;
    	head = head.next;
    	if (head == null) {
    		tail = null;
    	}
    	size--;
    	return data;
    }

    // Returns the element at the front of the queue without removing it
    public String peek() {
    	//TODO 
    	return head.data;
    }

    // Checks if the queue is empty
    public boolean isEmpty() {
    	//TODO 
        return head == null;
    }

    // Returns the number of elements in the queue
    public int size() {
    	//TODO 
        return size;
    }
    
    // Checks if the index is in the range of the queue size.
    // You can use this method while implementing the other ones.
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
