package com.tree.dfs;

import java.util.ArrayList;
import java.util.List;

public class FindAllTreePaths {

	/*
	 * Given a binary tree and a number ‘S’, find all paths from root-to-leaf
	 * such that the sum of all the node values of each path equals ‘S’.
	 */
	public static List<List<Integer>> findPaths(TreeNode root, int sum) {
		List<List<Integer>> allPaths = new ArrayList<>();
		List<Integer> currentPath = new ArrayList<Integer>();
		findPathsRecursive(root, sum, currentPath, allPaths);
		return allPaths;
	}

	private static void findPathsRecursive(TreeNode currentNode, int sum, List<Integer> currentPath,
			List<List<Integer>> allPaths) {
		if (currentNode == null)
			return;

		// add the current node to the path
		currentPath.add(currentNode.val);

		// if the current node is a leaf and its value is equal to sum, save the
		// current path
		if (currentNode.val == sum && currentNode.left == null && currentNode.right == null) {
			allPaths.add(new ArrayList<Integer>(currentPath));
		} else {
			// traverse the left sub-tree
			findPathsRecursive(currentNode.left, sum - currentNode.val, currentPath, allPaths);
			// traverse the right sub-tree
			findPathsRecursive(currentNode.right, sum - currentNode.val, currentPath, allPaths);
		}

		// remove the current node from the path to backtrack,
		// we need to remove the current node while we are going up the
		// recursive call stack.
		currentPath.remove(currentPath.size() - 1);
	}

	public static List<List<Integer>> findPaths1(TreeNode root, int sum) {
		List<List<Integer>> allPaths = new ArrayList<>();
		findAllPaths(root, sum, allPaths, new ArrayList<>());
		return allPaths;
	}

	static void findAllPaths(TreeNode root, int sum, List<List<Integer>> allPaths, List<Integer> path) {
		if (root != null) {
			path.add(root.val);
			if (sum == root.val) {
				allPaths.add(new ArrayList<>(path));
			}
			findAllPaths(root.left, sum - root.val, allPaths, path);
			findAllPaths(root.right, sum - root.val, allPaths, path);
			path.remove(path.size() - 1);
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(12);
		root.left = new TreeNode(7);
		root.right = new TreeNode(1);
		root.left.left = new TreeNode(4);
		root.right.left = new TreeNode(10);
		root.right.right = new TreeNode(5);
		int sum = 23;
		List<List<Integer>> result = FindAllTreePaths.findPaths(root, sum);
		System.out.println("Tree paths with sum " + sum + ": " + result);
	}
}
