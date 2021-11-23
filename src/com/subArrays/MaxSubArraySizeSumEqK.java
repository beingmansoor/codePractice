package com.subArrays;

import java.util.HashMap;

public class MaxSubArraySizeSumEqK {
	public static int maxSubArrayLen(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);
		int result = 0;
		int sum = 0;

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (map.containsKey(sum - k)) {
				result = Math.max(result, i - map.get(sum - k));
			}
			map.putIfAbsent(sum, i);
		}

		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(maxSubArrayLen(new int[]{1, -1, 5, -2, 3}, 3));
	}
}
