package com.dp.lcs;

public class LongestIncreasingSubSequence {

	/*
	 * The above algorithm tells us two things:
	 * 
	 * If the number at the current index is bigger than the number at the
	 * previous index, we increment the count for LIS up to the current index.
	 * But if there is a bigger LIS without including the number at the current
	 * index, we take that. So we need to find all the increasing subsequences
	 * for the number at index ‘i’, from all the previous numbers (i.e. number
	 * till index ‘i-1’), to eventually find the longest increasing subsequence.
	 * 
	 * If ‘i’ represents the ‘currentIndex’ and ‘j’ represents the
	 * ‘previousIndex’, our recursive formula would look like:
	 * 
	 * if num[i] > num[j] => dp[i] = dp[j] + 1 if there is no bigger LIS for 'i'
	 */
	public int findLISLength(int[] nums) {
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
	
	public static int findPositionToReplace(int[] a, int low, int high, int x) {
		int mid;
		while (low <= high) {
			mid = low + (high - low) / 2;
			if (a[mid] == x)
				return mid;
			else if (a[mid] > x)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return low;
	}

	public static int lengthOfLIS(int[] nums) {
		if (nums == null | nums.length == 0)
			return 0;
		int n = nums.length, len = 0;
		int[] increasingSequence = new int[n];
		increasingSequence[len++] = nums[0];
		for (int i = 1; i < n; i++) {
			if (nums[i] > increasingSequence[len - 1])
				increasingSequence[len++] = nums[i];
			else {
				int position = findPositionToReplace(increasingSequence, 0, len - 1, nums[i]);
				increasingSequence[position] = nums[i];
			}
		}
		return len;
	}
	/*
	 * The time complexity of the above algorithm is O(n^2) and the
	 * space complexity is O(n)
	 */

	public int findLISLengthRec(int[] nums) {
		return findLISLengthRecursive(nums, 0, -1);
	}

	private int findLISLengthRecursive(int[] nums, int currentIndex, int previousIndex) {
		if (currentIndex == nums.length)
			return 0;

		// include nums[currentIndex] if it is larger than the last included
		// number
		int c1 = 0;
		if (previousIndex == -1 || nums[currentIndex] > nums[previousIndex])
			c1 = 1 + findLISLengthRecursive(nums, currentIndex + 1, currentIndex);

		// excluding the number at currentIndex
		int c2 = findLISLengthRecursive(nums, currentIndex + 1, previousIndex);

		return Math.max(c1, c2);
	}

	public static void main(String[] args) {
		LongestIncreasingSubSequence lis = new LongestIncreasingSubSequence();
		int[] nums = { 4, 2, 3, 6, 10, 1, 12 };
		System.out.println(lis.findLISLength(nums));
		nums = new int[] { -4, 10, 3, 7, 15 };
		System.out.println(lis.findLISLength(nums));
		
		System.out.println(lis.lengthOfLIS(nums));
	}
}