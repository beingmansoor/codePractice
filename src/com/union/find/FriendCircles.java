package com.union.find;

/*
 * There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

Example 1:
Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
The 2nd student himself is in a friend circle. So return 2.
Example 2:
Input: 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.

 */
public class FriendCircles {

	class UnionFind {
		private int count = 0;
		private final int[] parent, rank;

		public UnionFind(int n) {
			count = n;
			parent = new int[n];
			rank = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}

		public int find(int p) {
			while (p != parent[p]) {
				parent[p] = parent[parent[p]]; // path compression by halving
				p = parent[p];
			}
			return p;
		}

		public void union(int p, int q) {
			int rootP = find(p);
			int rootQ = find(q);
			if (rootP == rootQ) {
				return;
			}
			if (rank[rootQ] > rank[rootP]) {
				parent[rootP] = rootQ;
			} else {
				parent[rootQ] = rootP;
				if (rank[rootP] == rank[rootQ]) {
					rank[rootP]++;
				}
			}
			count--;
		}

		public int count() {
			return count;
		}
	}

	public int findCircleNum2(int[][] M) {
		int n = M.length;
		UnionFind uf = new UnionFind(n);
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (M[i][j] == 1) {
					uf.union(i, j);
				}
			}
		}
		return uf.count();
	}

	public int findCircleNum(int[][] M) {

		int c = M.length;
		int p[] = new int[M.length];
		for (int i = 0; i < M.length; i++) {
			p[i] = i;
		}

		for (int i = 0; i < M.length; i++) {
			for (int j = i + 1; j < M.length; j++) {
				if (M[i][j] == 1) {
					int x = find(p, i);
					int y = find(p, j);
					if (x != y) {
						p[y] = x;
						c--;
					}
				}
			}
		}

		return c;
	}

	public int find(int[] p, int i) {
		while (p[i] != i) {
			i = p[i];
		}
		return p[i];
	}

	public void dfs(int[][] M, int[] visited, int i) {
		for (int j = 0; j < M.length; j++) {
			if (M[i][j] == 1 && visited[j] == 0) {
				visited[j] = 1;
				dfs(M, visited, j);
			}
		}
	}

	public int findCircleNum_DFS(int[][] M) {
		int[] visited = new int[M.length];
		int count = 0;
		for (int i = 0; i < M.length; i++) {
			if (visited[i] == 0) {
				dfs(M, visited, i);
				count++;
			}
		}
		return count;
	}
	public static void main(String[] args) {
		FriendCircles f = new FriendCircles();

		System.out.println(f.findCircleNum(new int[][] { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } }));
		System.out.println(f.findCircleNum(new int[][] { { 1, 1, 0 }, { 1, 1, 1 }, { 0, 1, 1 } }));

	}
}
