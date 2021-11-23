package com.tree.dfs;

import java.util.ArrayList;
import java.util.List;



public class MaxDiffBetweenNodeAndAncestor
{
	static class TreeNode
	{
		int val;
		List<TreeNode> children;

		TreeNode(int x)
		{
			val = x;
			children = new ArrayList<>();
		}
	};
	
	public static int maxDiff = 0;

	public static int maxClockSkew(TreeNode root)
	{
		if (root == null)
		{
			return 0;
		}

		maxDiff = 0;

		dfs(root, root.val, root.val);
		return maxDiff;
	}

	public static void dfs(TreeNode node, int maxiVal, int miniVal)
	{
		if (node == null)
		{
			return;
		}
		// update `maxDiff`
		int possiblemaxDiff = Math.max(Math.abs(maxiVal - node.val), Math.abs(miniVal - node.val));
		maxDiff = Math.max(maxDiff, possiblemaxDiff);

		// update the maxiVal and miniVal
		maxiVal = Math.max(maxiVal, node.val);
		miniVal = Math.min(miniVal, node.val);

		for (TreeNode child : node.children)
		{
			dfs(child, maxiVal, miniVal);
		}
		return;
	}

	public static void main(String[] args)
	{
		// Driver Code

		TreeNode root = new TreeNode(8);
		root.children.add(new TreeNode(3));
		root.children.add(new TreeNode(10));
		root.children.add(new TreeNode(12));
		root.children.get(0).children.add(new TreeNode(6));
		root.children.get(0).children.get(0).children.add(new TreeNode(1));
		root.children.get(0).children.add(new TreeNode(5));
		root.children.get(0).children.get(1).children.add(new TreeNode(2));
		root.children.get(0).children.get(1).children.add(new TreeNode(3));
		root.children.get(0).children.get(1).children.add(new TreeNode(4));
		root.children.get(2).children.add(new TreeNode(8));
		root.children.get(2).children.add(new TreeNode(7));
		root.children.get(2).children.add(new TreeNode(9));

		System.out.println("The maximum clock skew we'll encounter is: " + maxClockSkew(root) + " seconds");
	}
}
