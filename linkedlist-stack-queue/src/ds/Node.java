package ds;

/* 
 * This class represents the single node of the data structures that you will implement.
 * Do not make any modifications in this file.
 * Getters and setters are implemented for your convenience.
 * 
 * Notice that this is a relatively simple implementation. We don't even need an index 
 * variable on the nodes to implement a list, stack, or queue.
 */

class Node {
	
	String data;
	Node next;

	public Node(String data) {
		this.data = data;
		this.next = null;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
}