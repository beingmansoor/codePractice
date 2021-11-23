package com.array;

public class MinPathSum {

	public static void main(String[] args) {

		final int[][] grid = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
		System.out.println(minPathSum(grid));
	}

	public static int minPathSum(int[][] grid) {

		if (grid == null || grid.length == 0) {
			return Integer.MAX_VALUE;
		}
		final int[][] minCost = new int[grid.length][grid[0].length];

		minCost[0][0] = grid[0][0];
		for (int i = 1; i < grid.length; i++) {
			minCost[i][0] = grid[i][0] + minCost[i - 1][0];
		}

		for (int j = 1; j < grid[0].length; j++) {
			minCost[0][j] = grid[0][j] + minCost[0][j - 1];
		}

		for (int i = 1; i < grid.length; i++) {
			for (int j = 1; j < grid[0].length; j++) {
				minCost[i][j] = Math.min(minCost[i][j - 1], minCost[i - 1][j]) + grid[i][j];
			}
		}

		return minCost[grid.length - 1][grid[0].length - 1];
	}

}
