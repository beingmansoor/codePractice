package com.dp.lis;

import java.util.Arrays;

/*
Problem Statement:
Given an array arr[0 … n-1] containing n positive integers, a subsequence of arr[] is called Bitonic 
if it is first increasing, then decreasing. 

Write a function that takes an array as argument and returns the length of the longest bitonic 
subsequence. A sequence, sorted in increasing order is considered Bitonic with the decreasing part as 
empty. Similarly, decreasing order sequence is considered Bitonic with the increasing part as empty.

 */
public class LongestBitonicSubsequence
{
	  // Space Complexity: O(n)
    // Time Complexity: O(n)
    // n = length of given integer array
    public int getLongestBitonicSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] longestIncreasingSubsequence = new int[len]; // longestIncreasingSubsequence[i] = length of
            // longest increasing subsequence that ENDS at nums[i] and includes nums[i] in nums[0..i]
        int[] longestDecreasingSubsequence = new int[len]; // longestIncreasingSubsequence[i] = length of
            // longest decreasing subsequence that BEGINS at nums[i] and includes nums[i] in nums[i...(len - 1)]
        Arrays.fill(longestIncreasingSubsequence, 1); // initialize with 1 since
            // longestIncreasingSubsequence[i] is at least 1.
            // Since longestIncreasingSubsequence[i] = length of
            // longest increasing subsequence that ENDS at nums[i] and includes nums[i] .
            // This means the longest increasing subsequence ending at and including nums[i]
            // will at least include nums[i]. So min length = 1
        Arrays.fill(longestDecreasingSubsequence, 1); // initialize with 1 since
            // longestDecreasingSubsequence[i] is at least 1.
            // Since longestDecreasingSubsequence[i] = length of
            // longest decreasing subsequence that BEGINS at nums[i] and includes nums[i] .
            // This means the longest decreasing subsequence ending at and including nums[i]
            // will at least include nums[i]. So min length = 1


       /* Compute LIS (Longest Increasing Subsequence) values from LEFT TO RIGHT */
       for (int i = 1; i < len; i++) {// longestIncreasingSubsequence[0] = 1 since Longest Increasing Subsequence ending at nums[0] also starts at
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    longestIncreasingSubsequence[i] = Math.max(longestIncreasingSubsequence[i], longestIncreasingSubsequence[j] + 1);
                }
            }
        }
        /* Compute LDS (Longest Decreasing Subsequence) values from RIGHT TO LEFT */
        for (int i = len - 2; i >= 0; i--) { // longestDecreasingSubsequence[len - 1] = 1 since Longest Decreasing Subsequence starting at nums[len - 1] also ends at nums[len - 1]
            for (int j = i + 1; j <= len - 1; j++) {
                if (nums[j] < nums[i]) {
                    longestDecreasingSubsequence[i] = Math.max(longestDecreasingSubsequence[i], longestDecreasingSubsequence[j] + 1);
                }
            }
        }
        int max = 1; // max is 1 we would have at least one element in Bitonic Subsequence for a given non-empty non-null array
        /* Return the maximum value of longestIncreasingSubsequence[i] + longestDecreasingSubsequence[i] - 1*/
        for (int i = 0; i < len; i++) {
            max = Math.max(max, longestIncreasingSubsequence[i] + longestDecreasingSubsequence[i] - 1); // We have MINUS 1 because
                // both longestIncreasingSubsequence[i] and longestDecreasingSubsequence[i] includes nums[i]
                // but we want to include nums[i] once and not twice
        }
        return max;
    }
}
