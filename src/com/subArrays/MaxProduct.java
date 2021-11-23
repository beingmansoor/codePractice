package com.subArrays;

public class MaxProduct {

	/*
	 * this is very similar to the " max cumulative sum subarray" problem. here
	 * you keep 2 values: the max cumulative product UP TO current element
	 * starting from SOMEWHERE in the past, and the minimum cumuliative product
	 * UP TO current element . it would be easier to see the DP structure if we
	 * store these 2 values for each index, like maxProduct[i],minProduct[i] .
	 * 
	 * at each new element, u could either add the new element to the existing
	 * product, or start fresh the product from current index (wipe out previous
	 * results), hence the 2 Math.max() lines.
	 * 
	 * if we see a negative number, the "candidate" for max should instead
	 * become the previous min product, because a bigger number multiplied by
	 * negative becomes smaller, hence the swap()
	 */
	static int maxProduct(int A[], int n) {

		int r = A[0];

		for (int i = 1, iMin = r, iMax = r; i < A.length; i++) {
			if (A[i] < 0) {
				int t = iMax;
				iMax = iMin;
				iMin = t;
			}

			iMax = Math.max(A[i], A[i] * iMax);
			iMin = Math.min(A[i], A[i] * iMin);

			r = Math.max(r, iMax);
		}

		return r;

	}

	/*
	 * Easy to understand O(n) solution : Its all about having odd or even
	 * numbers of negative integers. if the negative numbers are even, then the
	 * first pass will give the solution. If the negative numbers are odd, the
	 * second pass will give the solution.
	 */
	public int maxProduct(int[] nums) {

		int prod = 1;
		int result = Integer.MIN_VALUE;

		for (int i = 0; i < nums.length; i++) {
			prod = prod * nums[i];
			result = Math.max(prod, result);
			if (prod == 0) {
				prod = 1;
			}
		}
		prod = 1;

		for (int i = nums.length - 1; i >= 0; i--) {

			prod = prod * nums[i];
			result = Math.max(prod, result);
			if (prod == 0) {
				prod = 1;
			}
		}
		return result;
	}
}
