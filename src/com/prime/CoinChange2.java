package com.prime;

public class CoinChange2 {
	public int change(int amount, int[] coins) {
		int ways = 0;

		int[][] dp = new int[coins.length + 1][amount + 1];
		for (int j = 1; j <= amount; j++) {
			dp[0][j] = 0;
		}
		for (int i = 0; i <= coins.length; i++) {
			dp[i][0] = 1;
		}

		for (int i = 1; i <= coins.length; i++) {
			for (int j = 1; j <= amount; j++) {
				dp[i][j] = dp[i - 1][j];
				if (j >= coins[i - 1]) {
					dp[i][j] += dp[i][j - coins[i - 1]];
				}
			}
		}
		return dp[coins.length][amount];
	}

	public static void main(String[] args) {
		CoinChange2 c = new CoinChange2();
		System.out.println(c.change(5, new int[] { 1, 2, 5 }));
		System.out.println(c.change(3, new int[] { 2 }));
		System.out.println(c.change(10, new int[] { 10 }));
	}
}
