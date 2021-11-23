package com.subArrays;

public class SubArrayProductLessThanK {

	public int numSubarrayProductLessThanK(int[] nums, int k) {

		int count = 0;

		int prod = 1;
		for (int i = 0, j = 0; j < nums.length; j++) {
			prod *= nums[j];

			while (i <= j && prod >= k) {
				prod /= nums[i++];
			}

			count += (j - i + 1);
		}

		return count;

	}

	public static void main(String[] args) {

		SubArrayProductLessThanK s = new SubArrayProductLessThanK();

		System.out.println(s.numSubarrayProductLessThanK(new int[] { 10, 5, 2, 6 }, 100));

	}

}
