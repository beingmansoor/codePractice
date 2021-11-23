package com.array;

import java.util.Arrays;

public class MaxSquare {

	public static int maximalSquare(char[][] matrix) {

		int maxLen = 0;

		int cols = matrix[0].length;
		int rows = matrix.length;
		int[][] dp = new int[rows][cols];

		for (int i = 0; i < rows; i++) {
			dp[i][0] = (matrix[i][0] == '1' ? 1 : 0);
		}
		for (int j = 0; j < cols; j++) {
			dp[0][j] = (matrix[0][j] == '1' ? 1 : 0);
		}

		for (int i = 1; i < rows; i++) {
			for (int j = 1; j < cols; j++) {
				if (matrix[i][j] == '1') {
					dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
				}
				maxLen = Math.max(maxLen, dp[i][j]);
			}
		}

		System.out.println(Arrays.deepToString(dp));

		return maxLen * maxLen;

	}

	public static void main(String[] args) {
		System.out.println(maximalSquare(new char[][] { { '1', '0', '1', '0', '0' }, { '1', '0', '1', '1', '1' },
				{ '1', '1', '1', '1', '1' }, { '1', '0', '0', '1', '0' } }));
	}
}
