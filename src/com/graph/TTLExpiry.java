package com.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class TreeNode
{
	int val;
	List<TreeNode> children;

	TreeNode(int x)
	{
		val = x;
		children = new ArrayList<>();
	}
};

public class TTLExpiry
{
	public static List<Integer> getDevices(TreeNode root, TreeNode server, int ttl)
	{

		HashMap<Integer, List<Integer>> neighbors = new HashMap<>(); // Adjacency
																		// list
		dfs(null, root, neighbors);

		// BFS to find nodes
		List<Integer> bfs = new ArrayList<>();
		bfs.add(server.val);
		Set<Integer> lookup = new HashSet<Integer>(bfs);

		for (int i = 0; i < ttl; i++)
		{
			List<Integer> temp = new ArrayList<>();
			for (int node : bfs)
				for (int nei : neighbors.get(node))
					if (lookup.contains(nei) == false)
						temp.add(nei);

			bfs = temp;
			lookup.addAll(bfs);
		}

		return bfs;
	}

	private static void dfs(TreeNode parent, TreeNode child, HashMap<Integer, List<Integer>> neighbors)
	{
		// DFS to build adjacency list
		if (child == null)
		{
			return;
		}

		if (parent != null)
		{

			if (!neighbors.containsKey(parent.val))
				neighbors.put(parent.val, new ArrayList<>());
			if (!neighbors.containsKey(child.val))
				neighbors.put(child.val, new ArrayList<>());

			neighbors.get(parent.val).add(child.val);
			neighbors.get(child.val).add(parent.val);
		}

		for (int i = 0; i < (child.children).size(); i++)
			dfs(child, child.children.get(i), neighbors);
	}

	public static void main(String[] args)
	{
		// Driver Code

		TreeNode root = new TreeNode(1);
		root.children.add(new TreeNode(2));
		root.children.add(new TreeNode(3));
		root.children.add(new TreeNode(4));
		root.children.get(0).children.add(new TreeNode(5));
		root.children.get(0).children.get(0).children.add(new TreeNode(10));
		root.children.get(0).children.add(new TreeNode(6));
		root.children.get(0).children.get(1).children.add(new TreeNode(11));
		root.children.get(0).children.get(1).children.add(new TreeNode(12));
		root.children.get(0).children.get(1).children.add(new TreeNode(13));
		root.children.get(2).children.add(new TreeNode(7));
		root.children.get(2).children.add(new TreeNode(8));
		root.children.get(2).children.add(new TreeNode(9));

		TreeNode server = root.children.get(0).children.get(1);
		int ttl = 2;
		System.out.println("The TTL value will expire on node IDs: " + getDevices(root, server, ttl));

	}
}
