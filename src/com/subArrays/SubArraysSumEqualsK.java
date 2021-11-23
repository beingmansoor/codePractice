package com.subArrays;

import java.util.HashMap;

public class SubArraysSumEqualsK {
	public static int subarraySum(int[] nums, int k) {
		int count = 0;

		HashMap<Integer, Integer> preFixSum = new HashMap<Integer, Integer>();
		preFixSum.put(0, 1);
		int sum = 0;
		for (int i : nums) {
			sum += i;
			if (preFixSum.containsKey(sum-k))
				count += preFixSum.get(sum-k);
			preFixSum.put(sum, preFixSum.getOrDefault(sum, 0) + 1);
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(subarraySum(new int[] { 1, 1, 1, }, 2));
		System.out.println(subarraySum(new int[] { 1, 2, 3, }, 3));
	}
}
