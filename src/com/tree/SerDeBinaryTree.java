package com.tree;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class SerDeBinaryTree {
	private static final String spliter = ",";
	private static final String NN = "X";

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		buildString(root, sb);
		return sb.toString();
	}

	private void buildString(TreeNode node, StringBuilder sb) {
		if (node == null) {
			sb.append(NN).append(spliter);
		} else {
			sb.append(node.val).append(spliter);
			buildString(node.left, sb);
			buildString(node.right, sb);
		}
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		Deque<String> nodes = new LinkedList<>();
		nodes.addAll(Arrays.asList(data.split(spliter)));
		return buildTree(nodes);
	}

	private TreeNode buildTree(Deque<String> nodes) {
		String val = nodes.remove();
		if (val.equals(NN))
			return null;
		else {
			TreeNode node = new TreeNode(Integer.valueOf(val));
			node.left = buildTree(nodes);
			node.right = buildTree(nodes);
			return node;
		}
	}
	
	private static void print(TreeNode root) {
		if (root != null) {
			System.out.print(root.val + "\t");
			print(root.left);
			print(root.right);
		}
	}
	
	public static void main(String[] args) {
			TreeNode root = new TreeNode(1);
			root.left = new TreeNode(2);
			root.right = new TreeNode(3);

			root.left.right = new TreeNode(4);

			root.right.right = new TreeNode(5);

			SerDeBinaryTree b = new SerDeBinaryTree();
			String serialize = b.serialize(root);
			
			System.out.println(serialize);
			
			TreeNode treeNode = b.deserialize(serialize);
			System.out.println(b.serialize(treeNode));
			print(treeNode);

	}
	
	
}