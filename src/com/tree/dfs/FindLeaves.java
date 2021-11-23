package com.tree.dfs;

import java.util.ArrayList;
import java.util.List;

public class FindLeaves
{
	public static void main(String[] args)
	{
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);

		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		findLeaves(root, result);
		System.out.println(result);
	}

	private static int findLeaves(TreeNode root, List<List<Integer>> list)
	{
		if (root == null)
			return -1;
		int left = findLeaves(root.left, list);
		int right = findLeaves(root.right, list);

		int current = Math.max(left, right) + 1;

		if (list.size() <= current)
		{
			list.add(new ArrayList<>());
		}

		list.get(current).add(root.val);
		return current;
	}
}
