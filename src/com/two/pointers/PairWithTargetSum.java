package com.two.pointers;

/*
 * Given an array of sorted numbers and a target sum, find a pair in the array whose sum
 *  is equal to the given target.

Write a function to return the indices of the two numbers (i.e. the pair) such that 
they add up to the given target.

Example 1:

Input: [1, 2, 3, 4, 6], target=6
Output: [1, 3]
Explanation: The numbers at index 1 and 3 add up to 6: 2+4=6
 * 
 */
public class PairWithTargetSum {

	public static int[] search(int[] arr, int targetSum) {

		int start = 0, end = arr.length - 1;
		int sum = 0;
		while (start < end) {
			sum = (arr[start] + arr[end]);
			if (sum > targetSum)
				end--;
			else if (sum < targetSum)
				start++;
			else
				return new int[] { start, end };
		}

		return new int[] { -1, -1 };
	}

	public static void main(String[] args) {
		int[] result = PairWithTargetSum.search(new int[] { 1, 2, 3, 4, 6 }, 6);
		System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
		result = PairWithTargetSum.search(new int[] { 2, 5, 9, 11 }, 11);
		System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
	}
}