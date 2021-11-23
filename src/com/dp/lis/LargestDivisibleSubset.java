package com.dp.lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Problem Statement:

Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of 
elements in this subset satisfies:
Si % Sj = 0 or Sj % Si = 0.
If there are multiple solutions, return any subset is fine.

Example 1:
Input: [1,2,3]
Output: [1,2] (of course, [1,3] will also be ok)

Example 2:
Input: [1,2,4,8]
Output: [1,2,4,8]
 */
public class LargestDivisibleSubset
{
	public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int len = nums.length;
        if (nums == null || len == 0) {
            return res;
        }
        Arrays.sort(nums); // common step for most LIS (Longest Increasing Subsequence) based solution
        //
        // once we sort nums[] array in ascending order this problems reduces to 
        // finding longest increasing subsequence maintaining the condition given in the problem
        int[] parent = new int[len];
        for (int i = 0; i < len; i++) {
            parent[i] = i; // initially there is no predecessor to any item
        }
        int[] lis = new int[len]; // longest increasing subsequence
        int maxLISLength = 1;
        int lisEndIndex = 0;
        Arrays.fill(lis, 1); // common step for most LIS (Longest Increasing Subsequence) based solution
        // perform an LIS operation
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                    parent[i] = j;
                }
            }
            if (maxLISLength < lis[i]) {
                maxLISLength = lis[i];
                lisEndIndex = i;
            }
        }
        while (parent[lisEndIndex] != lisEndIndex){
            res.add(nums[lisEndIndex]);
            lisEndIndex = parent[lisEndIndex];
        }
        res.add(nums[lisEndIndex]);
        Collections.reverse(res);
        return res;
    }
}
