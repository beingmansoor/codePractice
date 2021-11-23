package com.matrix;

public class NoOfSubMatricesWithOnes {

	static int countSquares(int[][] matrix) {

		int count = 0;
		int r = matrix.length;
		int c = matrix[0].length;

		int maxSubSquareSize = 0;

		int[][] dp = new int[r][c];

		// fill first row
		for (int i = 0; i < c; i++) {
			dp[0][i] = matrix[0][i];
			maxSubSquareSize = Math.max(dp[0][i], maxSubSquareSize);
			count += dp[0][i];
		}
		// fill first column
		for (int j = 1; j < r; j++) {
			dp[j][0] = matrix[j][0];
			count += dp[j][0];
			maxSubSquareSize = Math.max(dp[j][0], maxSubSquareSize);
		}

		if (maxSubSquareSize == 0) {
			return 0;
		}
		for (int i = 1; i < r; i++) {
			for (int j = 1; j < c; j++) {
				if (matrix[i][j] != 0) {
					dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
				}
				count += dp[i][j];
				maxSubSquareSize = Math.max(dp[i][j], maxSubSquareSize);
			}
		}

		System.out.println("max sub sq size:" + maxSubSquareSize);
		return count;
	}

	static int countSquares1(int[][] matrix) {

		int count = 0;
		int r = matrix.length;
		int c = matrix[0].length;
		for (int i = 0; i < r; i++) {
			count += matrix[i][c - 1];
		}
		for (int j = 0; j < c; j++) {
			count += matrix[r - 1][j];
		}

		count -= matrix[r - 1][c - 1];

		for (int i = r - 2; i >= 0; i--) {
			for (int j = c - 2; j >= 0; j--) {
				if (matrix[i][j] != 0) {
					matrix[i][j] = 1 + Math.min(matrix[i + 1][j + 1], Math.min(matrix[i + 1][j], matrix[i][j + 1]));
				}
				count += matrix[i][j];
			}
		}

		return count;
	}


	public static void main(String[] args) {
		System.out.println(countSquares(new int[][] { { 0, 0, 0 }, { 0, 0, 0 } }));
		System.out.println(countSquares(new int[][] { { 0, 1, 1, 1 }, { 1, 1, 1, 1 }, { 0, 1, 1, 1 } }));
	}
}
