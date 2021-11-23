package com.tree;

public class GreaterTree {
	public TreeNode convertBST(TreeNode root) {

		helper(root, 0);
		return root;
	}

	private int helper(TreeNode root, int parent) {

		if (root == null) {
			return 0;
		}
		int rVal = 0;
		if (root.right != null) {
			rVal = helper(root.right, parent);
		}

		if (rVal != 0)
			root.val += rVal;
		else
			root.val += parent;

		if (root.left != null) {
			return helper(root.left, root.val);
		}

		return root.val;
	}

	public static void main(String[] args) {
		TreeNode r = new TreeNode(4);
		r.left = new TreeNode(1);
		r.left.left = new TreeNode(0);
		r.left.right = new TreeNode(2);
		r.left.right.right = new TreeNode(4);

		r.right = new TreeNode(6);
		r.right.right = new TreeNode(7);
		r.right.left = new TreeNode(5);
		r.right.right.right = new TreeNode(8);

		new GreaterTree().convertBST(r);

	}
}
