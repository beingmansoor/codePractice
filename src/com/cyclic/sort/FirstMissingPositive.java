package com.cyclic.sort;

import java.util.Arrays;

/*
 * Given an unsorted array containing numbers, find the smallest missing positive number in it.

Example 1:

Input: [-3, 1, 5, 4, 2]
Output: 3
Explanation: The smallest missing positive number is '3'
Example 2:

Input: [3, -2, 0, 1, 2]
Output: 4
Example 3:

Input: [3, 2, 5, 1]
Output: 4
 */
public class FirstMissingPositive {

	public static int findNumber(int[] nums) {
		int i = 0;
		while (i < nums.length) {
			if (nums[i] > 0 && nums[i] < nums.length && (nums[i] != nums[nums[i] - 1])) {
				swap(nums, i, nums[i] - 1);
			}
			else
				i++;
		}
		System.out.println(Arrays.toString(nums));
		for(i =0; i<nums.length;i++)
		{
			if(nums[i] != i+1)
				return i+1;
		}
		return -1;
	}

	private static void swap(int[] nums, int i, int j) {
		int t = nums[i];
		nums[i] = nums[j];
		nums[j] =t;
	}
	
	public static void main(String[] args) {
		System.out.println(findNumber(new int[]{-3, 1, 5, 4, 2}));
		System.out.println(findNumber(new int[]{3, -2, 0, 1, 2}));
		System.out.println(findNumber(new int[]{3, 2, 5, 1}));
	}
}