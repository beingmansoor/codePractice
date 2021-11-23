package com.array;

import java.util.HashMap;
import java.util.Map;

/*
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

 */
public class ContiguosArray {

	static int jumpingOnClouds(int[] c, int k) {

		int e = 100;

		final int n = c.length;
		int hop = 0;
		while (true) {
			hop = (hop + k) % n;
			e--;
			if (c[hop] == 1) {
				e -= 2;
			}
			if (hop == 0) {
				break;
			}

		}

		return e;

	}

	public static void main(String[] args) {
		final ContiguosArray c = new ContiguosArray();

		System.out.println(c.findMaxLength(new int[] { 0, 1 }));
		System.out.println(c.findMaxLength(new int[] { 0, 1, 0 }));
		System.out.println(c.findMaxLength(new int[] { 0, 1, 1, 1, 1, 0, 0 }));
		System.out.println(c.findMaxLength(new int[] { 1, 1, 1, 1, 1 }));

		System.out.println(jumpingOnClouds(new int[] { 0, 0, 1, 0, 0, 1, 1, 0 }, 2));
	}

	public int findMaxLength(int[] nums) {
		final Map<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);
		int maxlen = 0, count = 0;
		for (int i = 0; i < nums.length; i++) {
			count = count + (nums[i] == 1 ? 1 : -1);
			if (map.containsKey(count)) {
				maxlen = Math.max(maxlen, i - map.get(count));
			} else {
				map.put(count, i);
			}
		}
		return maxlen;
	}

	public int findMaxLength_Mine(int[] nums) {

		int maxLength = 0;
		final int sum[] = new int[nums.length];
		sum[0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			sum[i] = sum[i - 1] + nums[i];
		}

		int l = 0, r = nums.length - 1;

		while (l < r) {
			final int ones = 2 * (sum[r] - sum[l] + nums[l]);

			final int window = (r - l + 1);

			if (window == ones) {
				maxLength = Math.max(maxLength, r - l + 1);
				l++;
				r--;
			} else if (window > ones) {
				r--;
			} else {
				l++;
			}

		}

		return maxLength;
	}
}
