package com.dp.fibonacci;

import java.util.Arrays;

/*
 * Different ways to represent N as sum of S non-negative integers
Given N and S. The task is to find out how many different ways are there to represent N as the 
sum of S non-negative integers.

N=2, S=4
(0, 4)
(1, 3)
(2, 2)
(3, 1)
(4, 0)
=> 5

N=3, S=5
(0, 0, 5)
(0, 1, 4)
...
=> 21

It is a dynamic programming problem, */
public class NoOfWays {

	static int solve(int n, int s, int[][] dp) {
		if (s < 0 || n <= 0)
			return 0;
		if (n == 1)
			return 1;
		if (dp[n][s] > 0)
			return dp[n][s];
		int cnt = 0;

		for (int i = 0; i <= s; i++) {
			cnt += solve(n - 1, s - i, dp);
		}

		return dp[n][s] = cnt;
	}

	static int solve(int n, int s) {
		int[][] dp = new int[n][s+1];
		for(int i=0;i<n;i++) {
			for(int j=0;j<=s;j++) {
				if(i == 0) {
					dp[i][j] = 1;
				}else {
					for(int k=0;k<=j;k++) {
						dp[i][j] += dp[i-1][k];
					}
				}
			}
		}
		return dp[n-1][s];
	}

	static int solve2(int n, int s) {
		int[] dp = new int[s + 1];
		Arrays.fill(dp, 1);
		for(int j=0;j<n-1;j++) {
			for(int i=s;i>=0;i--) {
				for(int k=i-1;k>=0;k--)
					dp[i] += dp[k];
			}
		}
		return dp[s];
	}
	public static void main(String[] args) {
		
		int[][] dp = null;
		int n1 = 2, s1 = 4;
		int n2 = 3, s2 = 5;
		dp = new int[n1+1][s1+1];
		System.out.println(solve(n1, s1,dp));
		dp = new int[n2+1][s2+1];
		System.out.println(solve(n2, s2,dp));
	}
}
