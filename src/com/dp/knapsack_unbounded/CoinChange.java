package com.dp.knapsack_unbounded;

public class CoinChange {

	public int countChange(int[] denominations, int total) {
		int n = denominations.length;
		int[][] dp = new int[n][total + 1];

		// populate the total=0 columns, as we will always have an empty set for
		// zero toal
		for (int i = 0; i < n; i++) {
			dp[i][0] = 1;
		}

		// process all sub-arrays for all capacities
		for (int i = 0; i < n; i++) {
			for (int t = 1; t <= total; t++) {
				if (i > 0) {
					dp[i][t] = dp[i - 1][t];
				}
				if (t >= denominations[i]) {
					dp[i][t] += dp[i][t - denominations[i]];
				}
			}
		}

		// total combinations will be at the bottom-right corner.
		return dp[n - 1][total];
	}

	public int countChangeRecursive(int[] denominations, int total, int currentIndex) {
		// basic checks
		if (total == 0) {
			return 1;
		}

		if (denominations.length == 0 || currentIndex >= denominations.length) {
			return 0;
		}

		// recursive call after selecting the coin at the currentIndex
		// if the coin at currentIndex exceeds the total, we shouldn't process
		// this
		int sum1 = 0;
		if (denominations[currentIndex] <= total) {
			sum1 = countChangeRecursive(denominations, total - denominations[currentIndex], currentIndex);
		}

		// recursive call after excluding the coin at the currentIndex
		int sum2 = countChangeRecursive(denominations, total, currentIndex + 1);

		return sum1 + sum2;
	}

	public int change(int amount, int[] coins) {
		int[][] dp = new int[coins.length + 1][amount + 1];
		dp[0][0] = 1;

		for (int i = 1; i <= coins.length; i++) {
			dp[i][0] = 1;
			for (int j = 1; j <= amount; j++) {
				dp[i][j] = dp[i - 1][j] + (j >= coins[i - 1] ? dp[i][j - coins[i - 1]] : 0);
			}
		}
		return dp[coins.length][amount];
	}
	// Now we can see that dp[i][j] only rely on dp[i-1][j] and
	// dp[i][j-coins[i]], then we can optimize the space by only using
	// one-dimension array.

	public int change_spaceOpt(int amount, int[] coins) {
		int[] dp = new int[amount + 1];
		dp[0] = 1;
		for (int coin : coins) {
			for (int i = coin; i <= amount; i++) {
				dp[i] += dp[i - coin];
			}
		}
		return dp[amount];
	}

	public static void main(String[] args) {
		CoinChange cc = new CoinChange();
		int[] denominations = { 1, 2, 3 };
		System.out.println(cc.countChange(denominations, 5));
	}
}