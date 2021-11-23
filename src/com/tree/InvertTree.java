package com.tree;

import java.util.LinkedList;
import java.util.Queue;

public class InvertTree
{
	public static TreeNode invertTree(TreeNode root)
	{
		if (root == null)
		{
			return root;
		}
		TreeNode right = invertTree(root.right);
		TreeNode left = invertTree(root.left);
		root.left = right;
		root.right = left;
		return root;
	}

	public static void main(String args[])
	{
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);

		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		print(root);
		System.out.println();
		print(invertTree(root));
	}

	private static void print(TreeNode root)
	{
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		while (!q.isEmpty())
		{
			int l = q.size();

			while (l-- > 0)
			{
				TreeNode node = q.poll();
				System.out.print(node.val + "\t");
				if (node.left != null)
					q.offer(node.left);
				if (node.right != null)
					q.offer(node.right);
			}
			System.out.println();

		}
	}
}
