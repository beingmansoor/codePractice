package com.tree;

import java.util.Stack;

public class BSTFromPreOrder {
	public TreeNode bstFromPreorder(int[] preorder) {

		return helper(preorder, 0, preorder.length - 1);

	}

	TreeNode helper(int[] pre, int preIndex, int end) {
		if (preIndex > end) {
			return null;
		}
		TreeNode root = new TreeNode(pre[preIndex]);
		int r = pivot(pre, pre[preIndex], preIndex, end);

		if (r != -1) {
			root.left = helper(pre, preIndex + 1, r - 1);
			root.right = helper(pre, r, end);
		} else {
			root.left = helper(pre, preIndex + 1, end);
		}

		return root;
	}

	int pivot(int[] pre, int e, int l, int end) {
		while (l <= end) {
			if (pre[l] > e) {
				return l;
			}
			l++;
		}

		return -1;
	}

	TreeNode bstFromPreorder1(int[] pre) {
		if (pre == null || pre.length == 0) {
			return null;
		}

		TreeNode root = new TreeNode(pre[0]);
		Stack<TreeNode> stack = new Stack<>();

		stack.push(root);
		for (int i = 1; i < pre.length; i++) {
			TreeNode treeNode = new TreeNode(pre[i]);
			if (false == stack.isEmpty() && stack.peek().val > pre[i]) {
				stack.peek().left = treeNode;
			} else {
				TreeNode p = null;
				while (false == stack.isEmpty() && stack.peek().val < pre[i]) {
						p = stack.pop();
				}
				if (null != p) {
					p.right = treeNode;
				}
			}
			stack.push(treeNode);
		}

		return root;
	}

	public static void main(String[] args) {
		BSTFromPreOrder b = new BSTFromPreOrder();
		TreeNode root = b.bstFromPreorder1(new int[] { 8, 5, 1, 7, 10, 12 });

		print(root);

		System.out.println();
	}

	private static void print(TreeNode root) {
		if (root != null) {
			print(root.left);
			System.out.print(root.val + "\t");
			print(root.right);
		}
	}
}
