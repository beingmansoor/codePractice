package com.tree.dfs;

public class TreeDiameter {

	private static int treeDiameter = 0;

	private static int calculateHight(TreeNode currentNode) {
		if (currentNode == null) {
			return 0;
		}

		final int leftTreeHeight = calculateHight(currentNode.left);
		final int rightTreeHeight = calculateHight(currentNode.right);

		// diameter at the current node will be equal to the height of left
		// subtree +
		// the height of right sub-trees + '1' for the current node
		final int diameter = leftTreeHeight + rightTreeHeight + 1;

		// update the global tree diameter
		treeDiameter = Math.max(treeDiameter, diameter);

		// height of the current node will be equal to the maximum of the hights
		// of
		// left or right subtrees plus '1' for the current node
		return Math.max(leftTreeHeight, rightTreeHeight) + 1;
	}

	public static int[] diametre(TreeNode root)
	{
		if(root == null) {
			return new int[]{0,0};
		}
		final int[] left = diametre(root.left);
		final int[] right = diametre(root.right);
		int internal_path = Math.max(left[1],right[1]) +1;
		
		if(left[0] + right[0] +1 > internal_path)
		{
			internal_path = left[0] + right[0] +1;
		}
		
		return new int[]{Math.max(left[0], right[0])+1, internal_path};
		
	}

	/*
	 * This problem follows the Binary Tree Path Sum pattern. We can follow the
	 * same DFS approach. There will be a few differences:
	 * 
	 * 1) At every step, we need to find the height of both children of the
	 * current node. For this, we will make two recursive calls similar to DFS.
	 * 
	 * 2)The height of the current node will be equal to the maximum of the
	 * heights of its left or right children, plus ‘1’ for the current node. 3)
	 * The tree diameter at the current node will be equal to the height of the
	 * left child plus the height of the right child plus ‘1’ for the current
	 * node: diameter = leftTreeHeight + rightTreeHeight + 1. To find the
	 * overall tree diameter, we will use a class level variable. This variable
	 * will store the maximum diameter of all the nodes visited so far, hence,
	 * eventually, it will have the final tree diameter.
	 * 
	 */
	public static int findDiameter(TreeNode root) {
		calculateHight(root);
		return treeDiameter;
	}


	public static void main(String[] args) {
		final TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(6);
		System.out.println("Tree Diameter: " + TreeDiameter.findDiameter(root));
		root.left.left = null;
		root.right.left.left = new TreeNode(7);
		root.right.left.right = new TreeNode(8);
		root.right.right.left = new TreeNode(9);
		root.right.left.right.left = new TreeNode(10);
		root.right.right.left.left = new TreeNode(11);
		System.out.println("Tree Diameter: " + TreeDiameter.findDiameter(root));
		
		final int[] result = diametre(root);
		System.out.println(Math.max(result[0], result[1]) - 1); // nodes = edges
																// +1;

	}

	public int[] Diameter(TreeNode root) {
		final int DandH[] = { 0, 0 }; // initialize the height (DandH[0]) and diameter
								// as 0 (DandH[1])
		if (root != null) {

			final int[] leftResult = Diameter(root.left);
			final int[] rightResult = Diameter(root.right);

			final int height = Math.max(leftResult[0], rightResult[0]) + 1;
			final int leftDiameter = leftResult[1];
			final int rightDiameter = rightResult[1];
			final int rootDiameter = leftResult[0] + rightResult[0] + 1;

			// max of Diameter of left subtree, Diameter of right subtree, Longest path
			// between two nodes which passes through the root.
			final int finalDiameter = getMax(leftDiameter, rightDiameter, rootDiameter);

			// prepare the DandH[]
			DandH[0] = height; // update the height
			DandH[1] = finalDiameter;
			return DandH;
		}
		return DandH;
	}

	public int getMax(int a, int b, int c) {
		return Math.max(a, Math.max(b, c));
	}
}
