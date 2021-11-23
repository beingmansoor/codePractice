package com.dp.lis;

import java.util.Arrays;

/*
 * Problem Statement:

Given an integer array nums, return the length of the longest strictly increasing subsequence.
A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].

Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Example 2:
Input: nums = [0,1,0,3,2,3]
Output: 4

Example 3:
Input: nums = [7,7,7,7,7,7,7]
Output: 1


Solution
===============
Recurrence Relation:

Let arr[0..n-1] be the input array and L(i) be the length of the LIS ending at index i such that arr[i] is the last element of the LIS.
L(i) = 1 + max( L(j) ) where 0 < j < i and arr[j] < arr[i]; or
L(i) = 1, if no such j exists.

To find the LIS for a given array, we need to return max(L(i)) where 0 < i < n.
The length of the longest increasing subsequence ending at index i, will be 1 greater
 than the maximum of lengths of all longest increasing subsequences ending at indices j 
 such that j is before i, where arr[j] < arr[i].
 */
public class LIS
{
	 public int lengthOfLIS(int[] nums) {
	        if (nums == null || nums.length == 0) {
	            return 0;
	        }
	        int len = nums.length;
	        int[] dp = new int[len];
	        Arrays.fill(dp, 1);
	        int max = 1; 
	        for (int i = 1; i < len; i++) {
	            for (int j = 0; j < i; j++) {
	                if (nums[i] > nums[j]) {
	                    dp[i] = Math.max(dp[i], dp[j] + 1);
	                }
	            }
	            max = Math.max(max, dp[i]);
	        }
	        return max;
	    }
}
