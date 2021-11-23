package com.union.find;

import java.util.Arrays;

/*
 * In this problem, a tree is an undirected graph that is connected and has no cycles.

The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.

Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given undirected graph will be like this:
  1
 / \
2 - 3
Example 2:
Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
Output: [1,4]
Explanation: The given undirected graph will be like this:
5 - 1 - 2
    |   |
    4 - 3

 */
public class RedudantConnection {
	public int[] findRedundantConnection(int[][] edges) {

		int[] p = new int[edges.length + 1];
		for (int i = 0; i <= edges.length; i++) {
			p[i] = i;
		}

		for (int[] e : edges) {
			int x = find(p, e[0]);
			int y = find(p, e[1]);
			if (x == y) {
				return e;
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
		RedudantConnection r = new RedudantConnection();

		System.out.println(Arrays.toString(r.findRedundantConnection(new int[][] { { 1, 2 }, { 1, 3 }, { 2, 3 } })));
	}
}
