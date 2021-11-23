package com.list;

class Node {
	int data;
	Node next;

	Node(int d) {
		data = d;
		next = null;
	}

}

public class RemoveDuplicates {
	public static Node removeDuplicates(Node head) {
		// Write your code here
		if (head == null || head.next == null) {
			return head;
		}
		Node curr = head.next;
		Node prev = head;
		while (curr != null) {
			if (curr.data != prev.data) {
				prev.next = curr;
				prev = curr;
			}
			curr = curr.next;
		}
		prev.next = null;
		return head;

	}

	public static void main(String[] args) {
		Node r = new Node(1);
		r.next = new Node(2);
		r.next.next = new Node(2);
		r.next.next.next = new Node(2);

		Node node = removeDuplicates(r);
		while (node != null) {
			System.out.print(node.data + "->");
			node = node.next;
		}
		System.out.println("null");
	}

}
