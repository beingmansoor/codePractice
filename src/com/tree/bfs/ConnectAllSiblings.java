package com.tree.bfs;

import java.util.LinkedList;
import java.util.Queue;

class TreeNode2 {
	int val;
	TreeNode2 left;
	TreeNode2 right;
	TreeNode2 next;

	TreeNode2(int x) {
		val = x;
		left = right = next = null;
	}

	// tree traversal using 'next' pointer
	public void printTree() {
		TreeNode2 current = this;
		System.out.print("Traversal using 'next' pointer: ");
		while (current != null) {
			System.out.print(current.val + " ");
			current = current.next;
		}
	}
};

public class ConnectAllSiblings {
	public static void connect(TreeNode2 root) {
		if (root == null)
			return;

		Queue<TreeNode2> queue = new LinkedList<>();
		queue.offer(root);
		TreeNode2 currentNode = null, previousNode = null;
		while (!queue.isEmpty()) {
			currentNode = queue.poll();
			if (previousNode != null)
				previousNode.next = currentNode;
			previousNode = currentNode;

			// insert the children of current node in the queue
			if (currentNode.left != null)
				queue.offer(currentNode.left);
			if (currentNode.right != null)
				queue.offer(currentNode.right);
		}
	}
	
//	public static void connect1(TreeNode2 root) {
//
//		Queue<TreeNode2> queue = new LinkedList<>();
//		queue.offer(root);
//		TreeNode2 currentNode = null;
//		while (!queue.isEmpty()) {
//			int levelSize = queue.size();
//			for (int i = 0; i < levelSize; i++) {
//				currentNode = queue.poll();
//
//				if (queue.peek() != null) {
//					currentNode.next = queue.peek();
//				}
//
//				// insert the children of current node in the queue
//				if (currentNode.left != null)
//					queue.add(currentNode.left);
//				if (currentNode.right != null)
//					queue.add(currentNode.right);
//			}
//		}
//	}

	public static void main(String[] args) {
		TreeNode2 root = new TreeNode2(12);
		root.left = new TreeNode2(7);
		root.right = new TreeNode2(1);
		root.left.left = new TreeNode2(9);
		root.right.left = new TreeNode2(10);
		root.right.right = new TreeNode2(5);
		ConnectAllSiblings.connect(root);
		root.printTree();
	}
}
