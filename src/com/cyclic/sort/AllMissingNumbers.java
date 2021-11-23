package com.cyclic.sort;

import java.util.*;

/*
 * We are given an unsorted array containing numbers taken from the range 1 to ‘n’. The array can have duplicates, which means some numbers will be missing. Find all those missing numbers.

Example 1:

Input: [2, 3, 1, 8, 2, 3, 5, 1]
Output: 4, 6, 7
Explanation: The array should have all numbers from 1 to 8, due to duplicates 4, 6, and 7 are missing.
Example 2:

Input: [2, 4, 1, 2]
Output: 3
Example 3:

Input: [2, 3, 2, 1]
Output: 4
 * 
 */
public class AllMissingNumbers {

	public static List<Integer> findNumbers(int[] nums) {
		List<Integer> missingNumbers = new ArrayList<>();

		int i = 0;
		while (i < nums.length) {
			int j = nums[i]-1;
			if (nums[i] != nums[j]) {
				swap(nums, i, j);
			} else{
				i++;
			}
		}
		
		for(i =0; i< nums.length ;i++)
		{
			if(nums[i] != (i+1))
			{
				missingNumbers.add(i+1);
			}
		}

		System.out.println(Arrays.toString(nums));
		return missingNumbers;
	}

	private static void swap(int[] nums, int i, int j) {

		int t = nums[i];
		nums[i] = nums[j];
		nums[j] = t;
	}

	public static void main(String[] args) {
		System.out.println(findNumbers(new int[] { 2, 3, 1, 8, 2, 3, 5, 1 }));
		System.out.println(findNumbers(new int[] { 2, 4, 1, 2 }));
		System.out.println(findNumbers(new int[] { 2, 3, 2, 1 }));
	}
}