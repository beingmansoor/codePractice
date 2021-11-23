package com.slow.fast;


/*
 * Let us suppose the length of the list which does not contain the loop be s, length of the loop be t and 
 * the ratio of fast_pointer_speed to slow_pointer_speed be k.

Let the two pointers meet at a distance j from the start of the loop.

So, the distance slow pointer travels = s + j. 
Distance the fast pointer travels = s + j + m * t (where m is the number of times the fast pointer has 
completed the loop). But, the fast pointer would also have traveled a distance k * (s + j) 
(k times the distance of the slow pointer).

Therefore, we get k * (s + j) = s + j + m * t.

s + j =  (m / k-1)t.

Hence, from the above equation, length the slow pointer travels is an integer multiple of the loop length.

For greatest efficiency , (m / k-1) = 1 (the slow pointer shouldn't have traveled the loop more than once.)

therefore , m = k - 1  => k = m + 1

Since m is the no.of times the fast pointer has completed the loop , m >= 1 . For greatest efficiency , m = 1.

therefore k = 2.

if we take a value of k > 2 , more the distance the two pointers would have to travel.

Hope the above explanation helps.
 * 
 */
public class LinkedListCycle {

	public static boolean hasCycle(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (slow == fast)
				return true; // found the cycle
		}
		return false;
	}

	public static boolean hasCycle1(ListNode head) {

		ListNode slow = head;
		ListNode fast = head.next;

		while (slow != null && fast != null) {
			if (slow == fast)
				return true;
			slow = slow.next;
			if (fast.next != null)
				fast = fast.next.next;
			else
				fast = null;
		}
		return false;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = new ListNode(6);
		System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycle(head));

		head.next.next.next.next.next.next = head.next.next;
		System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycle(head));

		head.next.next.next.next.next.next = head.next.next.next;
		System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycle(head));
	}
}