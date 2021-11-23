package com.tree;

public class TrimBST {
	public TreeNode trimBST(TreeNode root, int low, int high) {

		if(root == null)
			return null;
		
		root.left = trimBST(root.left, low, high);
		root.right = trimBST(root.right, low, high);
		
		if(root.val>high)
		{
			 TreeNode leftChild = root.left;
			 root = null;
			 return leftChild;
		}
		else if(root.val < low)
		{
			TreeNode rightChild = root.right;
			root = null;
			return rightChild;
		}
		return root;
	}
}
