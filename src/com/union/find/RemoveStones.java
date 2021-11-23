package com.union.find;

/*
 * 
 * On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.

Now, a move consists of removing a stone that shares a column or row with another stone on the grid.

What is the largest possible number of moves we can make?

 

Example 1:

Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Example 2:

Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
Example 3:

Input: stones = [[0,0]]
Output: 0

 */
public class RemoveStones {
	public int removeStones(int[][] stones) {
		if (stones.length <= 1) {
			return 0;
		}
		int s = stones.length;

		int[] p = new int[s];
		for (int i = 0; i < s; i++) {
			p[i] = i;
		}

		for (int i = 0; i < stones.length; i++) {
			for (int j = i + 1; j < stones.length; j++) {
				if (isConnected(stones[i], stones[j])) {
					int x = find(p, i);
					int y = find(p, j);
					if (x != y) {
						p[y] = x;
						s--;
					}
				}
			}
		}

		return stones.length - s;
	}

	private boolean isConnected(int[] s1, int[] s2) {
		if (s1[0] == s2[0] || s1[1] == s2[1]) {
			return true;
		}
		return false;
	}

	private int find(int[] p, int i) {

		while (p[i] != i) {
			i = p[i];
		}
		return i;
	}

	public static void main(String[] args) {
		RemoveStones r = new RemoveStones();
		System.out.println(r.removeStones(new int[][] { { 0, 0 }, { 0, 2 }, { 1, 1 }, { 2, 0 }, { 2, 2 } }));
		System.out.println(r.removeStones(new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 2 }, { 2, 1 }, { 2, 2 } }));
	}
}
