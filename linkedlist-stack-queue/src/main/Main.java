package main;

import ds.*;

public class Main {

	private static int totalScore = 0;

	public static void main(String[] args) {

		testLinkedList();
		testStack();
		testQueue();
		System.out.println("\n\n===============================");
		System.out.println("Total Score: " + totalScore + "/100");
	}

	private static void testLinkedList() {
		int linkedListScore = 0;
		MyLinkedList list = new MyLinkedList();

		System.out.println("======= LinkedList Tests =======");

		// Test 1: Add and Size
		list.add("One");
		list.add("Two");
		list.add("Three");
		list.add("Four");
		list.add("Five");
		int expectedSize = 5;
		if (list.size() == expectedSize) {
			System.out.println("PASS: Add and size test.");
			linkedListScore += 10;
		} else {
			System.out.println("FAIL: Add and size test.");
		}

		// Test 2: Insert
		list.insert(3, "Inserted");
		if ("Inserted".equals(list.get(3)) && list.size() == 6) {
			System.out.println("PASS: Insert test.");
			linkedListScore += 10;
		} else {
			System.out.println("FAIL: Insert test.");
		}

		// Test 3: Remove
		list.remove(1);
		if ("Three".equals(list.get(1)) && list.size() == 5) {
			System.out.println("PASS: Remove test.");
			linkedListScore += 10;
		} else {
			System.out.println("FAIL: Remove test.");
		}

		// Test 4: Check get() method
		boolean getMethodPass = true;
		String[] expectedValuesAfterInsertAndRemove = { "One", "Three", "Inserted", "Four", "Five" };
		for (int i = 0; i < expectedValuesAfterInsertAndRemove.length; i++) {
			if (!expectedValuesAfterInsertAndRemove[i].equals(list.get(i))) {
				getMethodPass = false;
				break;
			}
		}
		if (getMethodPass) {
			System.out.println("PASS: Get method test.");
			linkedListScore += 5;
		} else {
			System.out.println("FAIL: Get method test.");
		}

		// Test 5: General test
		list.add("Six");
		list.insert(0, "Zero");
		list.remove(3);
		String[] expectedValuesAfterAllOperations = { "Zero", "One", "Three", "Four", "Five", "Six" };
		boolean allOperationsPass = true;
		for (int i = 0; i < expectedValuesAfterAllOperations.length; i++) {
			if (!expectedValuesAfterAllOperations[i].equals(list.get(i))) {
				allOperationsPass = false;
				break;
			}
		}

		if (allOperationsPass) {
			System.out.println("PASS: General test.");
			linkedListScore += 15;
		} else {
			System.out.println("FAIL: General Test.");
		}

		totalScore += linkedListScore;
		System.out.println("LinkedList Score: " + linkedListScore + "/50");
	}

	private static void testStack() {
		int stackScore = 0;
		MyStack stack = new MyStack();

		System.out.println("\n\n========= Stack Tests =========");

		// Test 1: Push and Size
		stack.push("One");
		stack.push("Two");
		stack.push("Three");
		stack.push("Four");
		stack.push("Five");
		int expectedSize = 5;
		if (stack.size() == expectedSize) {
			System.out.println("PASS: Add and size test.");
			stackScore += 5;
		} else {
			System.out.println("FAIL: Pop and peek test.");
		}

		// Test 2: Pop and Peek
		if ("Five".equals(stack.pop()) && "Four".equals(stack.peek()) && stack.size() == 4) {
			System.out.println("PASS: Add and size test.");
			stackScore += 5;
		} else {
			System.out.println("FAIL: Add and size test.");
		}

		// Test 3: General test
		boolean passGeneralTest = true;
		String[] expectedOrder = { "Four", "Three", "Two", "One" };
		for (int i = 0; i < expectedOrder.length; i++) {
			String popped = stack.pop();
			if (!expectedOrder[i].equals(popped)) {
				passGeneralTest = false;
				System.out.println("FAIL: General test, expected " + expectedOrder[i] + ", got " + popped);
				break;
			}
		}
		if (passGeneralTest && stack.isEmpty()) {
			System.out.println("PASS: General test.");
			stackScore += 15;
		} else if (!passGeneralTest) {
			System.out.println("FAIL: General test for pop correctness.");
		} else if (!stack.isEmpty()) {
			System.out.println("FAIL: Stack should be empty.");
		}

		totalScore += stackScore;
		System.out.println("Stack Score: " + stackScore + "/25");

	}

	private static void testQueue() {
		int queueScore = 0;
		MyQueue queue = new MyQueue();

		System.out.println("\n\n========= Queue Tests =========");

		// Test 1: Enqueue and Size
		queue.enqueue("One");
		queue.enqueue("Two");
		queue.enqueue("Three");
		queue.enqueue("Four");
		queue.enqueue("Five");
		int expectedSize = 5;
		if (queue.size() == expectedSize) {
			System.out.println("PASS: Enqueue and size test.");
			queueScore += 5;
		} else {
			System.out.println("FAIL: Enqueue and size test.");
		}

		// Test 2: Dequeue and Peek
		if ("One".equals(queue.dequeue()) && "Two".equals(queue.peek()) && queue.size() == 4) {
			System.out.println("PASS: Dequeue and peek test.");
			queueScore += 5;
		} else {
			System.out.println("FAIL: Dequeue and peek test.");
		}

		// Test 3: General test
		boolean passGeneralTest = true;
		String[] expectedOrder = { "Two", "Three", "Four", "Five" };
		for (int i = 0; i < expectedOrder.length; i++) {
			String dequeued = queue.dequeue();
			if (!expectedOrder[i].equals(dequeued)) {
				passGeneralTest = false;
				System.out.println("FAIL: General test, expected " + expectedOrder[i] + ", got " + dequeued);
				break;
			}
		}
		if (passGeneralTest && queue.isEmpty()) {
			System.out.println("PASS: General test.");
			queueScore += 15;
		} else if (!passGeneralTest) {
			System.out.println("FAIL: General test for dequeue correctness.");
		} else if (!queue.isEmpty()) {
			System.out.println("FAIL: Queue should be empty.");
		}

		totalScore += queueScore;
		System.out.println("Queue Score: " + queueScore + "/25");
	}

}



