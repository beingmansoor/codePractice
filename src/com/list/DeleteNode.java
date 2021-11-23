package com.list;

public class DeleteNode {
	public void deleteNode(ListNode node) {

		if (node != null && node.next != null) {
			node.val = node.next.val;
			node.next = node.next.next;
		}
	}

	public static void main(String[] args) {
		ListNode h = new ListNode(4);
		ListNode del = new ListNode(5);
		h.next = del;
		ListNode del2 = new ListNode(1);
		h.next.next = del2;
		h.next.next.next = new ListNode(9);

		DeleteNode d = new DeleteNode();

		d.deleteNode(del2);

		while (h != null) {
			System.out.print(h.val + "->");
			h = h.next;
		}

		System.out.println("null");
	}
}
