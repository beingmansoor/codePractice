package com.dp.misc;

public class DungeonGame {
	public int calculateMinimumHP(int[][] dungeon) {

		int r = dungeon.length;
		int c = dungeon[0].length;

		int dp[][] = new int[r][c];

		for (int i = r - 1; i >= 0; i--) {
			for (int j = c - 1; j >= 0; j--) {
				if (i == r - 1 && j == c - 1) {
					dp[i][j] = Math.min(0, dungeon[r - 1][c - 1]);
				} else if (i == r - 1) {
					dp[i][j] = Math.min(0, dungeon[i][j] + dp[i][j + 1]);
				} else if (j == c - 1) {
					dp[i][j] = Math.min(0, dungeon[i][j] + dp[i + 1][j]);
				} else {
					dp[i][j] = Math.min(0, dungeon[i][j] + Math.max(dp[i + 1][j], dp[i][j + 1]));
				}
			}
		}
		return Math.abs(dp[0][0]) + 1;
	}

	public static void main(String[] args) {
		DungeonGame d = new DungeonGame();
		System.out.println(d.calculateMinimumHP(new int[][] { { 101 } }));
		System.out.println(d.calculateMinimumHP(new int[][] { { -2, -3, 3 }, { -5, -10, 1 }, { 10, 30, -5 } }));
	}
}
