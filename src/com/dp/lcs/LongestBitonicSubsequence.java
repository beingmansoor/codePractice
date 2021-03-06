package com.dp.lcs;

/*
 * Given a number sequence, find the length of its Longest Bitonic Subsequence (LBS). 
 * A subsequence is considered bitonic if it is monotonically increasing and then monotonically 
 * decreasing.

Example 1:

Input: {4,2,3,6,10,1,12}
Output: 5
Explanation: The LBS is {2,3,6,10,1}.
Example 2:

Input: {4,2,5,9,7,6,10,3,1}
Output: 7
Explanation: The LBS is {4,5,9,7,6,3,1}.

Basic Solution #
A basic brute-force solution could be to try finding the Longest Decreasing Subsequences (LDS),
starting from every number in both directions. 
So for every index ?i? in the given array, we will do two things:

Find LDS starting from ?i? to the end of the array.
Find LDS starting from ?i? to the beginning of the array.
LBS would be the maximum sum of the above two subsequences.
 */
public class LongestBitonicSubsequence {

	
	private int findLBSLength(int[] nums) {
	    int[] lds = new int[nums.length];
	    int[] ldsReverse = new int[nums.length];

	    // find LDS for every index up to the beginning of the array
	    for (int i = 0; i < nums.length; i++) {
	      lds[i] = 1; // every element is an LDS of length 1
	      for (int j = i - 1; j >= 0; j--)
	        if (nums[j] < nums[i]) {
	          lds[i] = Math.max(lds[i], lds[j] + 1);
	        }
	    }

	    // find LDS for every index up to the end of the array
	    for (int i = nums.length - 1; i >= 0; i--) {
	      ldsReverse[i] = 1; // every element is an LDS of length 1
	      for (int j = i + 1; j < nums.length; j++)
	        if (nums[j] < nums[i]) {
	          ldsReverse[i] = Math.max(ldsReverse[i], ldsReverse[j] + 1);
	        }
	    }

	    int maxLength = 0;
	    for (int i = 0; i < nums.length; i++) {
	      maxLength = Math.max(maxLength, lds[i] + ldsReverse[i] - 1);
	    }

	    return maxLength;
	  }

	
	private int findLBSLength1(int[] nums) {
		int maxLength = 0;
		for (int i = 0; i < nums.length; i++) {
			int c1 = findLDSLength(nums, i, -1);
			int c2 = findLDSLengthRev(nums, i, -1);
			maxLength = Math.max(maxLength, c1 + c2 - 1);
		}
		return maxLength;
	}

	// find the longest decreasing subsequence from currentIndex till the end of
	// the array
	private int findLDSLength(int[] nums, int currentIndex, int previousIndex) {
		if (currentIndex == nums.length)
			return 0;

		// include nums[currentIndex] if it is smaller than the previous number
		int c1 = 0;
		if (previousIndex == -1 || nums[currentIndex] < nums[previousIndex])
			c1 = 1 + findLDSLength(nums, currentIndex + 1, currentIndex);

		// excluding the number at currentIndex
		int c2 = findLDSLength(nums, currentIndex + 1, previousIndex);

		return Math.max(c1, c2);
	}

	// find the longest decreasing subsequence from currentIndex till the
	// beginning of the array
	private int findLDSLengthRev(int[] nums, int currentIndex, int previousIndex) {
		if (currentIndex < 0)
			return 0;

		// include nums[currentIndex] if it is smaller than the previous number
		int c1 = 0;
		if (previousIndex == -1 || nums[currentIndex] < nums[previousIndex])
			c1 = 1 + findLDSLengthRev(nums, currentIndex - 1, currentIndex);

		// excluding the number at currentIndex
		int c2 = findLDSLengthRev(nums, currentIndex - 1, previousIndex);

		return Math.max(c1, c2);
	}

	public static void main(String[] args) {
		LongestBitonicSubsequence lbs = new LongestBitonicSubsequence();
		int[] nums = { 4, 2, 3, 6, 10, 1, 12 };
		System.out.println(lbs.findLBSLength(nums));
		nums = new int[] { 4, 2, 5, 9, 7, 6, 10, 3, 1 };
		System.out.println(lbs.findLBSLength(nums));
	}
}
