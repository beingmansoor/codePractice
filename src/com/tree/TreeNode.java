package com.tree;

public class TreeNode {

	public TreeNode(int i) {
		val = i;
	}

	TreeNode left, right;

	int val;

	@Override
	public String toString() {
		return val + "\t";
	}
}
