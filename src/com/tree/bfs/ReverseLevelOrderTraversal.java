package com.tree.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class ReverseLevelOrderTraversal {

	public static List<List<Integer>> traverse(TreeNode root) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		if (root == null)
			return result;

		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int levelSize = queue.size();
			List<Integer> currentLevel = new ArrayList<>(levelSize);
			for (int i = 0; i < levelSize; i++) {
				TreeNode currentNode = queue.poll();
				// add the node to the current level
				currentLevel.add(currentNode.val);
				// insert the children of current node to the queue
				if (currentNode.left != null)
					queue.offer(currentNode.left);
				if (currentNode.right != null)
					queue.offer(currentNode.right);
			}
			// append the current level at the beginning
			result.add(0, currentLevel);
		}

		return result;
	}

	public static List<List<Integer>> traverse1(TreeNode root) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();

		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);

		while (!q.isEmpty()) {
			Stack<Integer> reverseLevel = new Stack<>();
			int levelSize = q.size();

			for (int i = 0; i < levelSize; i++) {
				TreeNode node = q.poll();
				if (null != node.left)
					q.offer(node.left);
				if (null != node.right)
					q.offer(node.right);
				reverseLevel.add(node.val);
			}

			ArrayList<Integer> arrayList = new ArrayList<>();
			while (!reverseLevel.isEmpty()) {
				arrayList.add(reverseLevel.pop());
			}

			result.add(arrayList);
		}
		return result;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(12);
		root.left = new TreeNode(7);
		root.right = new TreeNode(1);
		root.left.left = new TreeNode(9);
		root.right.left = new TreeNode(10);
		root.right.right = new TreeNode(5);
		List<List<Integer>> result = ReverseLevelOrderTraversal.traverse(root);
		System.out.println("Reverse level order traversal: " + result);
	}
}
