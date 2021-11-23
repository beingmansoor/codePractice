package com.union.find;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * There are n computers numbered from 0 to n-1 connected by ethernet cables connections forming a network where connections[i] = [a, b] represents a connection between computers a and b. Any computer can reach any other computer directly or indirectly through the network.

Given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected. Return the minimum number of times you need to do this in order to make all the computers connected. If it's not possible, return -1. 

Input: n = 4, connections = [[0,1],[0,2],[1,2]]
Output: 1
Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.

Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
Output: 2
Example 3:

Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
Output: -1
Explanation: There are not enough cables.
Example 4:

Input: n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
Output: 0

 */
public class NetworkConnected {
	public int makeConnected(int n, int[][] connections) {
		if (connections.length < n - 1) {
			return -1; // To connect all nodes need at least n-1 edges
		}
		int[] parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
		int components = n;
		for (int[] c : connections) {
			int p1 = findParent(parent, c[0]);
			int p2 = findParent(parent, c[1]);
			if (p1 != p2) {
				parent[p1] = p2; // Union 2 component
				components--;
			}
		}
		return components - 1; // Need (components-1) cables to connect
								// components together
	}

	private int findParent(int[] parent, int i) {
		if (i == parent[i]) {
			return i;
		}
		return parent[i] = findParent(parent, parent[i]); // Path compression
	}

	public int makeConnected_rank(int n, int[][] connections) {
		if (connections.length < n - 1) {
			return -1; // To connect all nodes need at least n-1 edges
		}
		int[] parent = new int[n];
		int[] size = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			size[i] = 1;
		}
		int components = n;
		for (int[] c : connections) {
			int p1 = findParent(parent, c[0]);
			int p2 = findParent(parent, c[1]);
			if (p1 != p2) {
				if (size[p1] < size[p2]) { // Merge small size to large size
					size[p2] += size[p1];
					parent[p1] = p2;
				} else {
					size[p1] += size[p2];
					parent[p2] = p1;
				}
				components--;
			}
		}
		return components - 1; // Need (components-1) cables to connect
								// components together
	}

	public int makeConnected_dfs(int n, int[][] connections) {
		if (connections.length < n - 1) {
			return -1; // To connect all nodes need at least n-1 edges
		}
		List<Integer>[] graph = new List[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int[] c : connections) {
			graph[c[0]].add(c[1]);
			graph[c[1]].add(c[0]);
		}
		int components = 0;
		boolean[] visited = new boolean[n];
		for (int v = 0; v < n; v++) {
			components += dfs(v, graph, visited);
		}
		return components - 1; // Need (components-1) cables to connect
								// components together
	}

	int dfs(int u, List<Integer>[] graph, boolean[] visited) {
		if (visited[u]) {
			return 0;
		}
		visited[u] = true;
		for (int v : graph[u]) {
			dfs(v, graph, visited);
		}
		return 1;
	}

	public int makeConnected_bfs(int n, int[][] connections) {
		if (connections.length < n - 1) {
			return -1; // To connect all nodes need at least n-1 edges
		}
		List<Integer>[] graph = new List[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int[] c : connections) {
			graph[c[0]].add(c[1]);
			graph[c[1]].add(c[0]);
		}
		int components = 0;
		boolean[] visited = new boolean[n];
		for (int v = 0; v < n; v++) {
			components += bfs(v, graph, visited);
		}
		return components - 1; // Need (components-1) cables to connect
								// components together
	}

	int bfs(int src, List<Integer>[] graph, boolean[] visited) {
		if (visited[src]) {
			return 0;
		}
		visited[src] = true;
		Queue<Integer> q = new LinkedList<>();
		q.offer(src);
		while (!q.isEmpty()) {
			int u = q.poll();
			for (int v : graph[u]) {
				if (!visited[v]) {
					visited[v] = true;
					q.offer(v);
				}
			}
		}
		return 1;
	}

}
