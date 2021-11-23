package com.subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//variant LIS where each element is divisible by previous number
public class LargestDivisibleSet {
	public List<Integer> largestDivisibleSubset(int[] nums) {

		int divCount[] = new int[nums.length];

		Arrays.sort(nums);

		// we will always have atleast one
		// element divisible by itself
		Arrays.fill(divCount, 1);

		// maintain the index of the last increment
		int prev[] = new int[nums.length];
		Arrays.fill(prev, -1);

		// index at which last increment happened
		int max_ind = 0;

		for (int i = 1; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {

				// only increment the maximum index if
				// this iteration will increase it
				if (nums[i] % nums[j] == 0 && divCount[i] < divCount[j] + 1) {
					prev[i] = j;
					divCount[i] = divCount[j] + 1;

				}

			}
			// Update last index of largest subset if size
			// of current subset is more.
			if (divCount[i] > divCount[max_ind]) {
				max_ind = i;
			}
		}

		// Print result
		int k = max_ind;
		ArrayList<Integer> r = new ArrayList<>();
		while (k >= 0) {
			r.add(nums[k]);
			k = prev[k];
		}

		return r;
	}

	public static void main(String[] args) {
		LargestDivisibleSet l = new LargestDivisibleSet();

		System.out.println(l.largestDivisibleSubset(new int[] { 3, 4, 16, 8 }));

		System.out.println(l.largestDivisibleSubset(new int[] { 1, 2, 4, 8 }));

		System.out.println(l.largestDivisibleSubset(new int[] { 1, 13, 17, 26, 0 }));

		System.out.println(l.largestDivisibleSubset(new int[] { 3, 5, 9, 12, 10 }));
	}
}
