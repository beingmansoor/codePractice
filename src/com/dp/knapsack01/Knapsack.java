package com.dp.knapsack01;

public class Knapsack {
	
	static int solveKnapsackSpaceOptimized(int[] profits, int[] weights, int capacity) {
	    // basic checks
	    if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
	      return 0;

	    int n = profits.length;
	    int[] dp = new int[capacity + 1];

	    // if we have only one weight, we will take it if it is not more than the
	    // capacity
	    for (int c = 0; c <= capacity; c++) {
	      if (weights[0] <= c)
	        dp[c] = profits[0];
	    }

	    // process all sub-arrays for all the capacities
	    for (int i = 1; i < n; i++) {
	      for (int c = capacity; c >= 0; c--) {
	        int profit1 = 0, profit2 = 0;
	        // include the item, if it is not more than the capacity
	        if (weights[i] <= c)
	          profit1 = profits[i] + dp[c - weights[i]];
	        // exclude the item
	        profit2 = dp[c];
	        // take maximum
	        dp[c] = Math.max(profit1, profit2);
	      }
	    }

	    return dp[capacity];
	  }
	

	public int solveKnapsack(int[] profits, int[] weights, int capacity) {
		// basic checks
		if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
			return 0;

		int n = weights.length;
		int[][] dp = new int[n][capacity + 1];

		// populate the capacity=0 columns, with '0' capacity we have '0' profit
		for (int i = 0; i < n; i++)
			dp[i][0] = 0;

		// if we have only one weight, we will take it if it is not more than
		// the capacity
		for (int c = 0; c <= capacity; c++) {
			if (weights[0] <= c)
				dp[0][c] = profits[0];
		}

		// process all sub-arrays for all the capacities
		for (int i = 1; i < n; i++) {
			for (int c = 1; c <= capacity; c++) {
				int profit1 = 0, profit2 = 0;
				// include the item, if it is not more than the capacity
				if (weights[i] <= c)
					profit1 = profits[i] + dp[i - 1][c - weights[i]];
				// exclude the item
				profit2 = dp[i - 1][c];
				// take maximum
				dp[i][c] = Math.max(profit1, profit2);
			}
		}

		printSelectedElements(dp, weights, profits, capacity);
		// maximum profit will be at the bottom-right corner.
		return dp[n - 1][capacity];
	}
	/*
	 * Time and Space complexity # The above solution has the time and space
	 * complexity of O(N*C), where ‘N’ represents total items and ‘C’ is
	 * the maximum capacity.
	 */

	private void printSelectedElements(int dp[][], int[] weights, int[] profits, int capacity){
		   System.out.print("Selected weights:");
		   int totalProfit = dp[weights.length-1][capacity];
		   for(int i=weights.length-1; i > 0; i--) {
		     if(totalProfit != dp[i-1][capacity]) {
		       System.out.print(" " + weights[i]);
		       capacity -= weights[i];
		       totalProfit -= profits[i];
		     }
		   }

		   if(totalProfit != 0)
		     System.out.print(" " + weights[0]);
		   System.out.println("");
		 }
	
	public int solveKnapsack1(int[] profits, int[] weights, int capacity) {
		return this.knapsackRecursive(profits, weights, capacity, 0);
	}

	public int solveKnapsack2(int[] profits, int[] weights, int capacity) {
		Integer[][] dp = new Integer[profits.length][capacity + 1];
		return this.knapsackMemoization(dp, profits, weights, capacity, 0);
	}

	private int knapsackMemoization(Integer[][] dp, int[] profits, int[] weights, int capacity, int currentIndex) {
		// base checks
		if (capacity <= 0 || currentIndex >= profits.length)
			return 0;

		// if we have already solved a similar problem, return the result from
		// memory
		if (dp[currentIndex][capacity] != null)
			return dp[currentIndex][capacity];

		// recursive call after choosing the element at the currentIndex
		// if the weight of the element at currentIndex exceeds the capacity, we
		// shouldn't process this
		int profit1 = 0;
		if (weights[currentIndex] <= capacity)
			profit1 = profits[currentIndex]
					+ knapsackMemoization(dp, profits, weights, capacity - weights[currentIndex], currentIndex + 1);

		// recursive call after excluding the element at the currentIndex
		int profit2 = knapsackMemoization(dp, profits, weights, capacity, currentIndex + 1);

		dp[currentIndex][capacity] = Math.max(profit1, profit2);
		return dp[currentIndex][capacity];
	}
	/*
	 * Time and Space complexity # Since our memoization array
	 * dp[profits.length][capacity+1] stores the results for all subproblems, we
	 * can conclude that we will not have more than N*CN∗C subproblems (where
	 * ‘N’ is the number of items and ‘C’ is the knapsack capacity). This means
	 * that our time complexity will be O(N*C).
	 * 
	 * The above algorithm will use O(N*C) space for the memoization array.
	 * Other than that we will use O(N) space for the recursion call-stack. So
	 * the total space complexity will be O(N*C + N), which is asymptotically
	 * equivalent to O(N*C)).
	 */

	private int knapsackRecursive(int[] profits, int[] weights, int capacity, int currentIndex) {
		// base checks
		if (capacity <= 0 || currentIndex >= profits.length)
			return 0;

		// recursive call after choosing the element at the currentIndex
		// if the weight of the element at currentIndex exceeds the capacity, we
		// shouldn't process this
		int profit1 = 0;
		if (weights[currentIndex] <= capacity)
			profit1 = profits[currentIndex]
					+ knapsackRecursive(profits, weights, capacity - weights[currentIndex], currentIndex + 1);

		// recursive call after excluding the element at the currentIndex
		int profit2 = knapsackRecursive(profits, weights, capacity, currentIndex + 1);

		return Math.max(profit1, profit2);
	}
	/*
	 * Time and Space complexity # The time complexity of the above algorithm is
	 * exponential O(2^n), where ‘n’ represents the total number of items. This
	 * can also be confirmed from the above recursion tree. As we can see, we
	 * will have a total of ‘31’ recursive calls – calculated through (2^n) +
	 * (2^n) - 1, which is asymptotically equivalent to O(2^n).
	 * 
	 * The space complexity is O(n). This space will be used to store the
	 * recursion stack. Since the recursive algorithm works in a depth-first
	 * fashion, which means that we can’t have more than ‘n’ recursive calls on
	 * the call stack at any time.
	 */

	public static void main(String[] args) {
		Knapsack ks = new Knapsack();
		int[] profits = { 1, 6, 10, 16 };
		int[] weights = { 1, 2, 3, 5 };
		int maxProfit = ks.solveKnapsack(profits, weights, 7);
		System.out.println("Total knapsack profit ---> " + maxProfit);
		maxProfit = ks.solveKnapsack(profits, weights, 6);
		System.out.println("Total knapsack profit ---> " + maxProfit);
	}
}
