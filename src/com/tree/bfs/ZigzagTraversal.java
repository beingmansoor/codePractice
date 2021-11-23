package com.tree.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZigzagTraversal {
	
	
	public static List<List<Integer>> traverse(TreeNode root) {
	    List<List<Integer>> result = new ArrayList<List<Integer>>();
	    if (root == null)
	      return result;

	    Queue<TreeNode> queue = new LinkedList<>();
	    queue.offer(root);
	    boolean leftToRight = true;
	    while (!queue.isEmpty()) {
	      int levelSize = queue.size();
	      List<Integer> currentLevel = new LinkedList<>();
	      for (int i = 0; i < levelSize; i++) {
	        TreeNode currentNode = queue.poll();

	        // add the node to the current level based on the traverse direction
	        if (leftToRight)
	          currentLevel.add(currentNode.val);
	        else
	          currentLevel.add(0, currentNode.val);

	        // insert the children of current node in the queue
	        if (currentNode.left != null)
	          queue.offer(currentNode.left);
	        if (currentNode.right != null)
	          queue.offer(currentNode.right);
	      }
	      result.add(currentLevel);
	      // reverse the traversal direction
	      leftToRight = !leftToRight;
	    }

	    return result;
	  }
	
	public static List<List<Integer>> traverse1(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		int leftToRight = 1;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		
		while(!queue.isEmpty())
		{
			int lSize = queue.size();
			ArrayList<Integer> levelElements = new ArrayList<>();
			for(int i=0;i<lSize;i++)
			{
				TreeNode node = queue.poll();
				if(null != node.left)
					queue.offer(node.left);
				if(null != node.right)
					queue.offer(node.right);
				if(leftToRight == 1)
				{
					levelElements.add(node.val);
					
				}
				else
				{
					levelElements.add(0, node.val);
				}
					
			}
			leftToRight = 1 - leftToRight;
			result.add(levelElements);
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
		root.right.left.left = new TreeNode(20);
		root.right.left.right = new TreeNode(17);
		List<List<Integer>> result = ZigzagTraversal.traverse(root);
		System.out.println("Zigzag traversal: " + result);
	}
}
