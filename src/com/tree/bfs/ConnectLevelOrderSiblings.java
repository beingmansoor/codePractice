package com.tree.bfs;

import java.util.LinkedList;
import java.util.Queue;

class TreeNode1 {
	int val;
	TreeNode1 left;
	TreeNode1 right;
	TreeNode1 next;

	TreeNode1(int x) {
		val = x;
		left = right = next = null;
	}

	// level order traversal using 'next' pointer
	void printLevelOrder() {
		TreeNode1 nextLevelRoot = this;
		while (nextLevelRoot != null) {
			TreeNode1 current = nextLevelRoot;
			nextLevelRoot = null;
			while (current != null) {
				System.out.print(current.val + " ");
				if (nextLevelRoot == null) {
					if (current.left != null)
						nextLevelRoot = current.left;
					else if (current.right != null)
						nextLevelRoot = current.right;
				}
				current = current.next;
			}
			System.out.println();
		}
	}
};

public class ConnectLevelOrderSiblings {

	public static void connect(TreeNode1 root) {
		if (root == null)
			return;

		Queue<TreeNode1> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode1 previousNode = null;
			int levelSize = queue.size();
			// connect all nodes of this level
			for (int i = 0; i < levelSize; i++) {
				TreeNode1 currentNode = queue.poll();
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
	}

	public static void connect1(TreeNode1 root) {

		Queue<TreeNode1> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int levelSize = queue.size();
			TreeNode1 currentNode = null;
			for (int i = 0; i < levelSize; i++) {
				currentNode = queue.poll();

				if (queue.peek() != null) {
					currentNode.next = queue.peek();
				}

				// insert the children of current node in the queue
				if (currentNode.left != null)
					queue.add(currentNode.left);
				if (currentNode.right != null)
					queue.add(currentNode.right);
			}
			if (currentNode != null) {
				currentNode.next = null;
			}
		}
	}

	public static void main(String[] args) {
		TreeNode1 root = new TreeNode1(12);
		root.left = new TreeNode1(7);
		root.right = new TreeNode1(1);
		root.left.left = new TreeNode1(9);
		root.right.left = new TreeNode1(10);
		root.right.right = new TreeNode1(5);
		ConnectLevelOrderSiblings.connect(root);
		System.out.println("Level order traversal using 'next' pointer: ");
		root.printLevelOrder();
	}
}
