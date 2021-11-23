package com.array;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

	public static void main(String[] args) {

		System.out.println(Arrays.toString(productExceptSelf(new int[] { 1, 2, 3, 4 })));

		System.out.println(workbook(5, 3, new int[] { 4, 2, 6, 1, 10 }));
	}

	public static int[] productExceptSelf(int[] nums) {

		int left = 1;

		final int result[] = new int[nums.length];

		result[0] = 1;

		for (int i = 1; i < nums.length; i++) {
			left *= nums[i - 1];
			result[i] = left;
		}

		int right = 1;

		for (int i = nums.length - 2; i >= 0; i--) {
			right *= nums[i + 1];
			result[i] *= right;
		}

		return result;

	}

	static int workbook(int n, int k, int[] arr) {

		if (null == arr || arr.length == 0) {
			return 0;
		}

		int page = 0, result = 0;
		for (final int problems : arr) {

			int PStart = 1;
			int pEnd = (problems < k ? problems : k);

			while (PStart <= problems) {
				page++;
				if (PStart <= page && page <= pEnd) {
					System.out.println("page:" + page);
					result++;
				}
				PStart += k;
				pEnd = (pEnd + k <= problems ? pEnd + k : problems);
			}

		}

		return result;

	}

}
