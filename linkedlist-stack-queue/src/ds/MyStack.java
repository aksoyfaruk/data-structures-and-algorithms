package ds;

import java.util.EmptyStackException;

public class MyStack {
	private Node top;
	private int size = 0;

	// Adds a new element to the top of the stack
	public void push(String data) {
		// TODO
		
		Node stackNode = new Node(data);
	    stackNode.setNext(top); 
	    top = stackNode; 
	    size++; 
	}

	
	
	// Removes and returns the top element of the stack
	public String pop() {
		// TODO
//		return null;
		if (isEmpty()) {
			return null; 
	    }
		
	    String data = top.getData(); 
	    top = top.getNext(); 
	    size--; 
	    
	    return data;

	}

	
	
	// Returns the top element of the stack without removing it
	public String peek() {
		// TODO
//		return null;
		if (isEmpty()) {
	        return null; 
	    }
	    return top.getData();
		
	}

	// Checks if the stack is empty
	public boolean isEmpty() {
		// TODO
		return top == null;
	}

	// Returns the number of elements in the stack
	public int size() {
		// TODO
		return size;
	}

	// Checks if the index is in the range of the stack size.
	// You can use this method while implementing the other ones.
	private void checkIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}
}

