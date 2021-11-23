package com.union.find;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.

The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), with one additional directed edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents a directed edge connecting nodes u and v, where u is a parent of child v.

Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given directed graph will be like this:
  1
 / \
v   v
2-->3

 */
public class RedudantConnectionII {
	public int[] findRedundantDirectedConnection(int[][] edges) {

		HashMap<Integer, Integer> incomingCount = new HashMap<>();
		int twoParentsVertex = -1;
		for (int[] e : edges) {
			incomingCount.put(e[1], incomingCount.getOrDefault(e[1], 0) + 1);
			if (incomingCount.get(e[1]) == 2) {
				twoParentsVertex = e[1];
			}
		}

		if (twoParentsVertex == -1) {
			return findRedundantConnection(edges, new int[] { -1, -1 });
		} else {
			for (int[] e : edges) {
				if (e[1] == twoParentsVertex) {
					int[] r = findRedundantConnection(edges, e);
					if (r == null) {
						return e;
					}
				}
			}
		}
		return null;
	}

	public int[] findRedundantDirectedConnection2(int[][] a) {
		Map<Integer, Integer> incoming = new HashMap<>();

		// count incoming edges for all nodes
		int nodeWithTwoIncomingEdges = -1;
		for (int[] v : a) {
			incoming.put(v[1], incoming.getOrDefault(v[1], 0) + 1);
			if (incoming.get(v[1]) == 2) {
				nodeWithTwoIncomingEdges = v[1];
			}
		}

		if (nodeWithTwoIncomingEdges == -1) {
			// if there are no nodes with 2 incoming edges -> just find a cycle
			return findRedundantConnection(a, -1);
		} else {
			// if there is a node with 2 incoming edges -> skip them one by one
			// and try to build a graph
			// if we manage to build a graph without a cycle - the skipped node
			// is what we're looking for
			for (int i = a.length - 1; i >= 0; i--) {
				if (a[i][1] == nodeWithTwoIncomingEdges) {
					int[] res = findRedundantConnection(a, i);
					if (res == null) {
						return a[i];
					}
				}
			}
		}

		return null;
	}

	// 'Redundant Connection' solution is extended to skip a node.
	int[] findRedundantConnection(int[][] a, int skip) {
		int[] p = new int[a.length + 1];
		for (int i = 0; i <= a.length; i++) {
			p[i] = i;
		}

		for (int i = 0; i < a.length; i++) {
			if (i == skip) {
				continue;
			}
			int x = find(p, a[i][0]);
			int y = find(p, a[i][1]);
			if (x == y) {
				return a[i];
			} else {
				p[y] = x;
			}
		}

		return null;
	}

	public int[] findRedundantConnection(int[][] edges, int[] skip) {

		int[] p = new int[edges.length + 1];
		for (int i = 0; i <= edges.length; i++) {
			p[i] = i;
		}

		int[] r = null;
		for (int[] e : edges) {
			if (skip[0] == e[0] && skip[1] == e[1]) {
				continue;
			}
			int x = find(p, e[0]);
			int y = find(p, e[1]);
			if (x == y) {
				r = e;
			} else {
				p[y] = x;
			}
		}

		return null;
	}

	int find(int[] p, int i) {
		while (p[i] != i) {
			i = p[i];
		}
		return i;
	}

	public static void main(String[] args) {
		RedudantConnectionII r = new RedudantConnectionII();
		System.out.println(
				Arrays.toString(r.findRedundantDirectedConnection(new int[][] { { 1, 2 }, { 1, 3 }, { 2, 3 } })));
	}
}
