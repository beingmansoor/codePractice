package com.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class TwoCityScheduling {
	public int twoCitySchedCost(int[][] costs) {

		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
				(d1, d2) -> (Math.abs(d2[0] - d2[1]) - Math.abs(d1[0] - d1[1])));

		for (int[] c : costs) {
			pq.offer(c);
		}

		int c1 = 0, c2 = 0, i = 0, n = costs.length / 2, ans = 0;

		while (i < 2 * n) {
			int[] cost = pq.poll();
			if ((cost[0] <= cost[1] && c1 < n) || c2 == n) {
				ans += cost[0];
				c1++;
			} else {
				ans += cost[1];
				c2++;
			}
			i++;
		}
		return ans;
	}

	public int twoCitySchedCost_Sort(int[][] costs) {
		Arrays.sort(costs, (a, b) -> a[0] - a[1] - (b[0] - b[1]));
		int res = 0;
		for (int i = 0; i < costs.length / 2; i++) {
			res += costs[i][0];
		}
		for (int i = costs.length / 2; i < costs.length; i++) {
			res += costs[i][1];
		}
		/*
		 * instead of two loops
		 * 
		 * for (int i = 0; i < costs.length / 2; i++) { cost += costs[i][1] +
		 * costs[costs.length-i-1][0]; }
		 * 
		 */
		return res;
	}

	/*
	 * dp[i][j] represents the cost when considering first (i + j) people in
	 * which i people assigned to city A and j people assigned to city B. my
	 * explanation here, just in case other people need this:
	 * 
	 * for (i+j)th people, he/she can be assigned either to A city or B city,
	 * the min cost if he is assigned to A city: dp[i-1][j]+costs[i+j-1][0];
	 * //because it is to A, so we should use i-1 the min cost if he is assigned
	 * to B city: dp[i][j-1]+costs[i+j-1][1]; //because it is to B, so we should
	 * use j-1 so dp[i][j] = Math.min(dp[i-1][j]+costs[i+j-1][0] ,
	 * dp[i][j-1]+costs[i+j-1][1]); another way to represent the dp equation is:
	 * dp[totalPerson][personToA], toatalPerson is the number of people have
	 * been assigned, and personToA of them are assigned to city A, so the the
	 * equation: dp[totalPerson][personToA]=
	 * Math.min(dp[totalPerson-1][personToA]+costs[totalPerson-1][1], //the last
	 * one to B dp[totalPerson-1][personToA-1]+costs[totalPerson-1][0]);//the
	 * last one to A
	 * 
	 */

	public int twoCitySchedCost_DP(int[][] costs) {
		int N = costs.length / 2;
		int[][] dp = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			dp[i][0] = dp[i - 1][0] + costs[i - 1][0];
		}
		for (int j = 1; j <= N; j++) {
			dp[0][j] = dp[0][j - 1] + costs[j - 1][1];
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				dp[i][j] = Math.min(dp[i - 1][j] + costs[i + j - 1][0], dp[i][j - 1] + costs[i + j - 1][1]);
			}
		}
		return dp[N][N];
	}

	public int twoCitySchedCost_memo(int[][] costs) {
		int len = costs.length / 2;
		int[][] memo = new int[len + 1][len + 1];
		dfs(memo, costs, len, len, 0);
		return memo[len][len];
	}

	private int dfs(int[][] memo, int[][] costs, int A, int B, int index) {
		if (index == costs.length) {
			return 0;
		}

		if (memo[A][B] != 0) {
			return memo[A][B];
		}

		int costA = costs[index][0];
		int costB = costs[index][1];

		if (A == 0 && B > 0) {
			memo[A][B] = costB + dfs(memo, costs, A, B - 1, index + 1);
		} else if (B == 0 && A > 0) {
			memo[A][B] = costA + dfs(memo, costs, A - 1, B, index + 1);
		} else {
			int sumA = costA + dfs(memo, costs, A - 1, B, index + 1);
			int sumB = costB + dfs(memo, costs, A, B - 1, index + 1);
			memo[A][B] = Math.min(sumA, sumB);
		}

		return memo[A][B];
	}

	public static void main(String[] args) {
		TwoCityScheduling t = new TwoCityScheduling();

		System.out.println(t.twoCitySchedCost(new int[][] { { 10, 20 }, { 30, 200 }, { 400, 50 }, { 30, 20 } }));
	}
}
