package com.tree;

import java.util.ArrayList;
import java.util.List;

public class Cousins {
	public boolean isCousins(TreeNode root, int x, int y) {

		int l = getLevel(root, x, 1);
		System.out.println("level:" + l);
		if (l <= 0) {
			return false;
		}
		List<Integer> siblings = new ArrayList<Integer>();
		getSiblings(root, x, l - 1, 1, siblings);
		System.out.println(siblings);
		return siblings.contains(y);
	}

	void getSiblings(TreeNode root, int x, int level, int currentLevel, List<Integer> siblings) {
		if (root == null || level < currentLevel) {
			return;
		}
		if (level == currentLevel) {
			if (!((root.left != null && root.left.val == x) || (root.right != null && root.right.val == x))) {
				if (root.left != null) {
					siblings.add(root.left.val);
				}
				if (root.right != null) {
					siblings.add(root.right.val);
				}
			}
		}

		getSiblings(root.left, x, level, currentLevel + 1, siblings);
		getSiblings(root.right, x, level, currentLevel + 1, siblings);

	}

	int getLevel(TreeNode root, int x, int level) {
		if (root == null) {
			return 0;
		}
		if (root.val == x) {
			return level;
		}
		int l = getLevel(root.left, x, level + 1);
		if (l > 0) {
			return l;
		}
		return getLevel(root.right, x, level + 1);
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);

		root.left.right = new TreeNode(4);

		root.right.right = new TreeNode(5);

		Cousins c = new Cousins();

		boolean cousins = c.isCousins(root, 5, 4);
		System.out.println(cousins);

	}
}
