package com.dp.lcs;

public class MinDeletionsSortedSequence {

	/*
	 * Problem Statement # Given a number sequence, find the minimum number of
	 * elements that should be deleted to make the remaining sequence sorted.
	 * 
	 * Example 1:
	 * 
	 * Input: {4,2,3,6,10,1,12} Output: 2 Explanation: We need to delete {4,1}
	 * to make the remaing sequence sorted {2,3,6,10,12}. Example 2:
	 * 
	 * Input: {-4,10,3,7,15} Output: 1 Explanation: We need to delete {10} to
	 * make the remaing sequence sorted {-4,3,7,15}. Example 3:
	 * 
	 * Input: {3,2,1,0} Output: 3 Explanation: Since the elements are in reverse
	 * order, we have to delete all except one to get a sorted sequence. Sorted
	 * sequences are {3}, {2}, {1}, and {0}
	 */
	public int findMinimumDeletions(int[] nums) {
		// subtracting the length of LIS from the length of the input array to
		// get minimum number of deletions
		return nums.length - findLISLength(nums);
	}

	private int findLISLength(int[] nums) {
		int[] dp = new int[nums.length];
		dp[0] = 1;

		int maxLength = 1;
		for (int i = 1; i < nums.length; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++)
				if (nums[i] > nums[j] && dp[i] <= dp[j]) {
					dp[i] = dp[j] + 1;
					maxLength = Math.max(maxLength, dp[i]);
				}
		}
		return maxLength;
	}

	public static void main(String[] args) {
		MinDeletionsSortedSequence mdss = new MinDeletionsSortedSequence();
		int[] nums = { 4, 2, 3, 6, 10, 1, 12 };
		System.out.println(mdss.findMinimumDeletions(nums));
		nums = new int[] { -4, 10, 3, 7, 15 };
		System.out.println(mdss.findMinimumDeletions(nums));
		nums = new int[] { 3, 2, 1, 0 };
		System.out.println(mdss.findMinimumDeletions(nums));
	}
}
