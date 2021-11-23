package com.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BSTfromPreOrder {

	public static TreeNode bstFromPreorder(int[] preorder) {
		if (preorder == null || preorder.length == 0) {
			return null;
		}
		Stack<TreeNode> stack = new Stack<>();
		TreeNode root = new TreeNode(preorder[0]);
		stack.push(root);
		for (int i = 1; i < preorder.length; i++) {
			TreeNode node = new TreeNode(preorder[i]);
			if (preorder[i] < stack.peek().val) {
				stack.peek().left = node;
			} else {
				TreeNode parent = stack.peek();
				while (!stack.isEmpty() && preorder[i] > stack.peek().val) {
					parent = stack.pop();
				}
				parent.right = node;
			}
			stack.push(node);
		}
		return root;
	}

	public static TreeNode bstFromPreorder_Mine(int[] preorder) {

		TreeNode root = new TreeNode(preorder[0]);

		for (int i = 1; i < preorder.length; i++) {
			helper(preorder, i, root);
		}

		return root;
	}

	private static TreeNode helper(int[] preorder, int i, TreeNode root) {

		if (root == null) {
			return new TreeNode(preorder[i]);
		}

		if (preorder[i] < root.val) {
			root.left = helper(preorder, i, root.left);
		} else {
			root.right = helper(preorder, i, root.right);
		}

		return root;
	}

	static void printLevelOrderTraversal(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();

		queue.offer(root);

		while (false == queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				TreeNode node = queue.remove();
				if (node != null) {
					System.out.print(node.val + "\t");

					if (null != node.left || null != node.right) {
						queue.offer(node.left);
						queue.offer(node.right);
					}
				} else {
					System.out.print("null" + "\t");
				}

			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		TreeNode node = bstFromPreorder(new int[] { 8, 5, 1, 7, 10, 12 });
		printLevelOrderTraversal(node);
	}

}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}