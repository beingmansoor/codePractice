package com.two.pointers;

import java.util.Arrays;

public class TwoSumLessThanK {
	public int twoSumLessThanK(int[] A, int K) {
		Arrays.sort(A);
		int maxSum = -1;
		int i = 0;
		int j = A.length - 1;
		while (i < j) {
			int sum = A[i] + A[j];
			if (sum < K) {
				maxSum = Math.max(maxSum, sum);
				i++;
			} else {
				j--;
			}
		}
		return maxSum;
	}
}
