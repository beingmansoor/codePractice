package com.slow.fast;

/*
 
 Rearrange a LinkedList (medium) #
Given the head of a Singly LinkedList, write a method to modify the LinkedList such that
 the nodes from the second half of the LinkedList are inserted alternately to the nodes 
 from the first half in reverse order. 
 So if the LinkedList has nodes 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null, 
  your method should return 1 -> 6 -> 2 -> 5 -> 3 -> 4 -> null.

Your algorithm should not use any extra space and the input LinkedList should be modified
in-place.

Example 1:

Input: 2 -> 4 -> 6 -> 8 -> 10 -> 12 -> null
Output: 2 -> 12 -> 4 -> 10 -> 6 -> 8 -> null 
Example 2:

Input: 2 -> 4 -> 6 -> 8 -> 10 -> null
Output: 2 -> 10 -> 4 -> 8 -> 6 -> null
 
 
 */
public class RearrangeList {
	
	public static void reorder(ListNode head) {
	    if (head == null || head.next == null)
	      return;

	    // find the middle of the LinkedList
	    ListNode slow = head, fast = head;
	    while (fast != null && fast.next != null) {
	      slow = slow.next;
	      fast = fast.next.next;
	    }

	    // slow is now pointing to the middle node
	    ListNode headSecondHalf = reverse(slow); // reverse the second half
	    ListNode headFirstHalf = head;

	    // rearrange to produce the LinkedList in the required order
	    while (headFirstHalf != null && headSecondHalf != null) {
	      ListNode temp = headFirstHalf.next;
	      headFirstHalf.next = headSecondHalf;
	      headFirstHalf = temp;

	      temp = headSecondHalf.next;
	      headSecondHalf.next = headFirstHalf;
	      headSecondHalf = temp;
	    }

	    // set the next of the last node to 'null'
	    if (headFirstHalf != null)
	      headFirstHalf.next = null;
	  }

	public static void reorder1(ListNode head) {
		
		ListNode middleNode = middleNode(head);
		ListNode secondHalfRev = reverse(middleNode.next);
		ListNode newHead = head;
		while(newHead !=middleNode && secondHalfRev!=null)
		{
			ListNode secondNext = secondHalfRev.next;
			ListNode next = newHead.next;
			newHead.next = secondHalfRev;
			secondHalfRev.next = next;
			secondHalfRev = secondNext;
			newHead = next;
		}
		
		 // set the next of the last node to 'null'
	    if (newHead != null)
	    	newHead.next = null;
	}
	
	static ListNode middleNode(ListNode head)
	{
		ListNode s = head;
		ListNode f = head;
		
		while(f!=null && f.next!=null)
		{
			s = s.next;
			f=f.next.next;
		}
		return s;
	}
	
	static ListNode reverse(ListNode head)
	{
		ListNode pre = null;
		ListNode curr = head;
		while(curr !=null)
		{
			ListNode next = curr.next;
			curr.next = pre;
			pre = curr;
			curr = next;
		}
		return pre;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(2);
		head.next = new ListNode(4);
		head.next.next = new ListNode(6);
		head.next.next.next = new ListNode(8);
		head.next.next.next.next = new ListNode(10);
		head.next.next.next.next.next = new ListNode(12);
		RearrangeList.reorder(head);
		while (head != null) {
			System.out.print(head.value + " ");
			head = head.next;
		}
	}
}