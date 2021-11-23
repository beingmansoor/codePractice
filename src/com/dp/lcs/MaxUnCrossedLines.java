package com.dp.lcs;

import java.util.Arrays;

public class MaxUnCrossedLines {
	public int maxUncrossedLines(int[] A, int[] B) {

		int l1 = A.length, l2 = B.length;
		int[][] dp = new int[l1 + 1][l2 + 1];

		for (int j = 0; j < l2; j++) {
			dp[0][j] = 0;
		}
		for (int i = 0; i < l1; i++) {
			dp[i][0] = 0;
		}

		for (int i = 1; i <= l1; i++) {
			for (int j = 1; j <= l2; j++) {
				if (A[i - 1] == B[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
				}
			}
		}

		System.out.println(Arrays.deepToString(dp));

		return dp[l1][l2];
	}

	public static void main(String[] args) {
		MaxUnCrossedLines m = new MaxUnCrossedLines();

		System.out.println(m.maxUncrossedLines(new int[] { 1, 4, 2 }, new int[] { 1, 2, 4 }));
		System.out.println(m.maxUncrossedLines(new int[] { 2, 5, 1, 2, 5 }, new int[] { 10, 5, 2, 1, 5, 2 }));
		System.out.println(m.maxUncrossedLines(new int[] { 1, 3, 7, 1, 7, 5 }, new int[] { 1, 9, 2, 5, 1 }));
	}
}
