package com.reverse.list;

public class ReverseEveryKElements {

	public static ListNode reverse(ListNode head, int k) {
		if (k <= 1 || head == null)
			return head;

		ListNode current = head, previous = null;
		while (true) {
			ListNode lastNodeOfPreviousPart = previous;
			// after reversing the LinkedList 'current' will become the last
			// node of the sub-list
			ListNode lastNodeOfSubList = current;
			ListNode next = null; // will be used to temporarily store the next
									// node
			// reverse 'k' nodes
			for (int i = 0; current != null && i < k; i++) {
				next = current.next;
				current.next = previous;
				previous = current;
				current = next;
			}

			// connect with the previous part
			if (lastNodeOfPreviousPart != null)
				lastNodeOfPreviousPart.next = previous; // 'previous' is now the
														// first node of the
														// sub-list
			else // this means we are changing the first node (head) of the
					// LinkedList
				head = previous;

			// connect with the next part
			lastNodeOfSubList.next = current;

			if (current == null) // break, if we've reached the end of the
									// LinkedList
				break;
			// prepare for the next sub-list
			previous = lastNodeOfSubList;
		}

		return head;
	}

	public static ListNode reverse1(ListNode head, int k) {

		if (head == null) {
			return null;
		}

		ListNode curr = head;
		ListNode prev = null, next = null;
		int i = 0;
		while (i < k && curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
			i++;
		}

		ListNode start = reverse(next, k);

		head.next = start;

		return prev;
	}
	
	static ListNode reverseKNodes(ListNode head, int k)
	{

		// if k is 0 or 1, then list is not changed
		if (k <= 1 || head == null)
		{
			return head;
		}

		ListNode reversed = null;
		ListNode prevTail = null;

		while (head != null && k > 0)
		{
			ListNode currentHead = null;
			ListNode currentTail = head;

			int n = k;
			while (head != null && n > 0)
			{
				ListNode temp = head.next;
				head.next = currentHead;
				currentHead = head;

				head = temp;
				n--;
			}

			if (reversed == null)
			{
				reversed = currentHead;
			}

			if (prevTail != null)
			{
				prevTail.next = currentHead;
			}
			prevTail = currentTail;
		}

		return reversed;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = new ListNode(6);
		head.next.next.next.next.next.next = new ListNode(7);
		head.next.next.next.next.next.next.next = new ListNode(8);

		ListNode result = ReverseEveryKElements.reverse(head, 3);
		System.out.print("Nodes of the reversed LinkedList are: ");
		while (result != null) {
			System.out.print(result.value + " ");
			result = result.next;
		}
	}
}