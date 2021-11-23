package com.subArrays;

import java.util.TreeSet;

public class MaxSumCloseToK {
	public int getLargestSumCloseToK(int[] arr, int k) {
		int sum = 0;
		TreeSet<Integer> set = new TreeSet<Integer>();
		int result = Integer.MIN_VALUE;
		set.add(0);

		for (int i = 0; i < arr.length; i++) {
			sum = sum + arr[i];

			Integer ceiling = set.ceiling(sum - k);
			if (ceiling != null) {
				result = Math.max(result, sum - ceiling);
			}

			set.add(sum);
		}

		return result;
	}
}
