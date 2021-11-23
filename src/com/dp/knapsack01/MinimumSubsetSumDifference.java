package com.dp.knapsack01;

public class MinimumSubsetSumDifference {
	
	public int canPartition(int[] num) {
	    int sum = 0;
	    for (int i = 0; i < num.length; i++)
	      sum += num[i];

	    int n = num.length;
	    boolean[][] dp = new boolean[n][sum/2 + 1];

	    // populate the sum=0 columns, as we can always form '0' sum with an empty set
	    for(int i=0; i < n; i++)
	      dp[i][0] = true;

	    // with only one number, we can form a subset only when the required sum is equal to that number
	    for(int s=1; s <= sum/2 ; s++) {
	      dp[0][s] = (num[0] == s ? true : false);
	    }

	    // process all subsets for all sums
	    for(int i=1; i < num.length; i++) {
	      for(int s=1; s <= sum/2; s++) {
	        // if we can get the sum 's' without the number at index 'i'
	        if(dp[i-1][s]) {
	          dp[i][s] = dp[i-1][s];
	        } else if (s >= num[i]) {
	          // else include the number and see if we can find a subset to get the remaining sum
	          dp[i][s] = dp[i-1][s-num[i]];
	        }
	      }
	    }

	    int sum1 = 0;
	    // Find the largest index in the last row which is true
	    for (int i = sum / 2; i >= 0; i--) {
	      if (dp[n-1][i] == true) {
	          sum1 = i;
	          break;
	      }
	    }

	    int sum2 = sum - sum1;
	    return Math.abs(sum2-sum1);
	  }

	public int canPartition1(int[] num) {
		int sum = 0;
		for (int i = 0; i < num.length; i++)
			sum += num[i];
		Integer[][] dp = new Integer[num.length][sum + 1];
		return this.canPartitionMemoization(dp, num, 0, 0, 0);
	}

	public int canPartitionMemoization(Integer[][] dp, int[] num, int currentIndex, int sum1, int sum2) {
		// base check
		if (currentIndex == num.length)
			return Math.abs(sum1 - sum2);

		// check if we have not already processed similar problem
		if (dp[currentIndex][sum1] == null) {
			// recursive call after including the number at the currentIndex in
			// the first set
			int diff1 = canPartitionMemoization(dp, num, currentIndex + 1, sum1 + num[currentIndex], sum2);

			// recursive call after including the number at the currentIndex in
			// the second set
			int diff2 = canPartitionMemoization(dp, num, currentIndex + 1, sum1, sum2 + num[currentIndex]);

			dp[currentIndex][sum1] = Math.min(diff1, diff2);
		}

		return dp[currentIndex][sum1];
	}

	public int canPartitionRecursive(int[] num, int currentIndex, int sum1, int sum2) {
		// base check
		if (currentIndex == num.length)
			return Math.abs(sum1 - sum2);

		// recursive call after including the number at the currentIndex in the
		// first set
		int diff1 = canPartitionRecursive(num, currentIndex + 1, sum1 + num[currentIndex], sum2);

		// recursive call after including the number at the currentIndex in the
		// second set
		int diff2 = canPartitionRecursive(num, currentIndex + 1, sum1, sum2 + num[currentIndex]);

		return Math.min(diff1, diff2);
	}
	
	 public int solve(int[] nums) {
	        int n = nums.length;
	        int sum = 0;
	        for (int i = 0; i < n; i++) {
	            sum += nums[i];
	        }
	        boolean[] subsetSum = new boolean[sum + 1]; // subsetSum[i] = true if there exists a subset in nums[] array that sum to i
	        subsetSum[0] = true; // base condition
	        for (int i = 0; i < n; i++) {
	            for (int w = sum; w >= nums[i]; w--) {
	                subsetSum[w] = subsetSum[w] || subsetSum[w - nums[i]];
	            }
	        }
	        int minDiff = Integer.MAX_VALUE;
	        for (int i = 0; i <= sum / 2; i++) {
	            if (subsetSum[i]) {
	                int a = i;
	                int b = sum - i;
	                minDiff = Math.min(minDiff, Math.abs(a - b));
	            }
	        }
	        return minDiff;
	    }

	public static void main(String[] args) {
		MinimumSubsetSumDifference ps = new MinimumSubsetSumDifference();
		int[] num = { 1, 2, 3, 9 };
		System.out.println(ps.canPartition(num));
		num = new int[] { 1, 2, 7, 1, 5 };
		System.out.println(ps.canPartition(num));
		num = new int[] { 1, 3, 100, 4 };
		System.out.println(ps.canPartition(num));
	}
}