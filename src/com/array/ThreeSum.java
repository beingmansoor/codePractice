package com.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreeSum {
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length < 3) {
			return res;
		}

		Arrays.sort(nums);

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], map.containsKey(nums[i]) ? map.get(nums[i]) + 1 : 1);
		}

		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				int rest = 0 - nums[i] - nums[j];
				int count = 0;
				if (nums[i] == rest) {
					count++;
				}
				if (nums[j] == rest) {
					count++;
				}
				if (map.containsKey(rest) && map.get(rest) > count && rest >= nums[j]) {
					res.add(Arrays.asList(nums[i], nums[j], rest));
				}
				while (j < nums.length - 1 && nums[j] == nums[j + 1]) {
					j++;
				}
			}
			while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
				i++;
			}
		}
		return res;
	}

}
