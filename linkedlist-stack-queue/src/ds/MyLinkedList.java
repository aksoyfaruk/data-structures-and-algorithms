package ds;

public class MyLinkedList {
	private Node head;
	private int size = 0;

	public MyLinkedList() {
		this.head = null;
	}

	
	// Adds a new node with the specified data at the end of the list
	public void add(String data) {
		// TODO
		// firstly we create a new node
		Node addNode = new Node(data);
		// 2 options: empty list or filled list. 
		// if it's empty: make the node head.
		if (head == null) {
			head = addNode;
		}
		// if it's filled: check until you find the last element of the list.
		else {
			Node instant = head;
			while (instant.getNext() != null) {
				instant = instant.getNext();
			}
			instant.setNext(addNode);
			
		}
		// increment the size
		size++;
		
	}

	// Inserts a new node with the specified data at the given index
	public void insert(int index, String data) {
		// TODO
		// check index
		checkIndex(index);
		Node insertNode = new Node(data);
		
		// if index is at the beginning of the list, we may directly insert
		if (index == 0) {
			insertNode.setNext(head);
			head = insertNode;
		}
		// if index != 0, we also need to adjust the list
		else {
			Node instant = head;
			for (int i=0; i<index-1; i++) {
				instant = instant.getNext();
			}
			insertNode.setNext(instant.getNext());
			instant.setNext(insertNode);
		}
		// increment the size
		size++;
		
	}

	
	
	// Removes the node at the specified index
	public void remove(int index) {
		// TODO
		checkIndex(index);
		
		// for index=0, head is updated to next one of it
	    if (index == 0) {
	        head = head.getNext();
	    } 
	    // iterate to the before of removed one
	    else {
	        Node instant = head;
	        for (int i = 0; i < index - 1; i++) {
	        	instant = instant.getNext();
	        }
	        instant.setNext(instant.getNext().getNext());
	    }
	    size--;
	}

	
	
	
	// Returns the data of the node at the specified index
	public String get(int index) {
		// TODO
//		return null;
		// this iterates until it reaches the desired index
		checkIndex(index);
	    Node instant = head;
	    
	    for (int i = 0; i < index; i++) {
	        instant = instant.getNext();
	    }
	    return instant.getData();
	}
	
	

	// Returns the number of elements in the list
	public int size() {
		// TODO
		return size;
	}

	// Checks if the index is in the range of the list size.
	// You can use this method while implementing the other ones.
	private void checkIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}
	
//	private void checkIndexInsert(int index) {
//		if (index < 0 || index > size) {
//		    throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
//		}
//	}
	
}
