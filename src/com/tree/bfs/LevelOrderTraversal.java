package com.tree.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {
	
	public static List<List<Integer>> traverse(TreeNode root) {
	    List<List<Integer>> result = new ArrayList<List<Integer>>();
	    if (root == null)
	      return result;

	    Queue<TreeNode> queue = new LinkedList<>();
	    queue.offer(root);
	    while (!queue.isEmpty()) {
	      int levelSize = queue.size();
	      List<Integer> currentLevel = new ArrayList<>(levelSize);
	      for (int i = 0; i < levelSize; i++) {
	        TreeNode currentNode = queue.poll();
	        // add the node to the current level
	        currentLevel.add(currentNode.val);
	        // insert the children of current node in the queue
	        if (currentNode.left != null)
	          queue.offer(currentNode.left);
	        if (currentNode.right != null)
	          queue.offer(currentNode.right);
	      }
	      result.add(currentLevel);
	    }

	    return result;
	  }
	
	public static List<List<Integer>> traverse1(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		
		LinkedList<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		
		int size = q.size();
		
		ArrayList<Integer> currentLevelElements = new ArrayList<>();
		while(size >0)
		{
			TreeNode node = q.poll();
			
			currentLevelElements.add(node.val);
			if(null != node.left)
				q.offer(node.left);
			if(null != node.right)
				q.offer(node.right);
			
			if(--size ==0 )
			{
				size = q.size();
				result.add(new ArrayList<>(currentLevelElements));
				currentLevelElements.clear();
			}
		}
		
		return result;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(12);
		root.left = new TreeNode(7);
		root.right = new TreeNode(1);
		root.left.left = new TreeNode(9);
		root.right.left = new TreeNode(10);
		root.right.right = new TreeNode(5);
		List<List<Integer>> result = LevelOrderTraversal.traverse(root);
		System.out.println("Level order traversal: " + result);
	}
}
