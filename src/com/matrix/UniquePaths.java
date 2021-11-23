package com.matrix;

public class UniquePaths {

	public int uniquePaths(int m, int n) {

		int[][] dp = new int[n][m];

		dp[0][0] = 1;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
				if (i == 0) {
					dp[i][j] = dp[i][j - 1];
				} else if (j == 0) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
				}
			}
		}

		return dp[n - 1][m - 1];

	}

	public static void main(String[] args) {
		UniquePaths u = new UniquePaths();

		System.out.println(u.uniquePaths(3, 2));
		System.out.println(u.uniquePaths(7, 3));
	}
}
