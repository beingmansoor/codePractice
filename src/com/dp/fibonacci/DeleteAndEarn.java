package com.dp.fibonacci;

/*
 * Question
Given an array nums of integers, you can perform operations on the array.

In each operation, you pick any nums[i] and delete it to earn nums[i] points. After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.

You start with 0 points. Return the maximum number of points you can earn by applying such operations.

Example 1:

Input: nums = [3, 4, 2]
Output: 6
Explanation:
Delete 4 to earn 4 points, consequently 3 is also deleted.
Then, delete 2 to earn 2 points. 6 total points are earned.



Example 2:

Input: nums = [2, 2, 3, 3, 3, 4]
Output: 9
Explanation:
Delete 3 to earn 3 points, deleting both 2's and the 4.
Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
9 total points are earned.
Note:

The length of nums is at most 20000.
Each element nums[i] is an integer in the range [1, 10000].
 */
public class DeleteAndEarn {
	public static int deleteAndEarn(int[] nums) {
		int len = nums.length;
		if (len == 0)
			return 0;
		int max = 0;
		for (int num : nums) {
			max = Math.max(max, num);
		}
		int[] val = new int[max + 1];
		for (int num : nums)
			val[num]++;
		int[] dp = new int[max + 1];
		for (int i = 1; i < dp.length; i++) {
			dp[i] = Math.max(dp[i - 1], (i > 1 ? dp[i - 2] : 0) + i * val[i]);
		}
		return dp[max];
	}
	
	public static void main(String[] args) {
		System.out.println(deleteAndEarn(new int[]{3,2,4}));
	}
}
