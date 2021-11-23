package com.list;

import java.util.HashMap;

public class CopyListWithRandomPointer
{

	public RandomListNode copyRandomList2(RandomListNode head)
	{
		HashMap<RandomListNode, RandomListNode> seen = new HashMap<>();

		RandomListNode oldNode = head;
		RandomListNode newNode = new RandomListNode(head.data);

		while (oldNode != null)
		{
			newNode.random = getClonedNode(seen, oldNode.random);
			newNode.next = getClonedNode(seen, oldNode.next);
			oldNode = oldNode.next;
			newNode = newNode.next;
		}

		return seen.get(head);
	}

	private RandomListNode getClonedNode(HashMap<RandomListNode, RandomListNode> seen, RandomListNode node)
	{
		if (!seen.containsKey(node))
		{
			RandomListNode copy = new RandomListNode(node.data);
			seen.put(node, copy);
		}
		return seen.get(node);
	}

	public RandomListNode copyRandomList(RandomListNode head)
	{
		RandomListNode iter = head, next;

		// First round: make copy of each node,
		// and link them together side-by-side in a single list.
		while (iter != null)
		{
			next = iter.next;

			RandomListNode copy = new RandomListNode(iter.data);
			iter.next = copy;
			copy.next = next;

			iter = next;
		}

		// Second round: assign random pointers for the copy nodes.
		iter = head;
		while (iter != null)
		{
			if (iter.random != null)
			{
				iter.next.random = iter.random.next;
			}
			iter = iter.next.next;
		}

		// Third round: restore the original list, and extract the copy list.
		iter = head;
		RandomListNode pseudoHead = new RandomListNode(0);
		RandomListNode copy, copyIter = pseudoHead;

		while (iter != null)
		{
			next = iter.next.next;

			// extract the copy
			copy = iter.next;
			copyIter.next = copy;
			copyIter = copy;

			// restore the original list
			iter.next = next;

			iter = next;
		}

		return pseudoHead.next;
	}

	static class RandomListNode
	{

		public RandomListNode random;
		public RandomListNode next;
		private int data;

		public RandomListNode(int i)
		{
			this.data = i;
		}

	}
}
