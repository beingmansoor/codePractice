package com.binary.search;

public class SearchInsertPOsition {
	public int searchInsert(int[] nums, int target) {

		int l = 0, r = nums.length - 1;

		while (l <= r) {
			int m = r - (r - l) / 2;
			if (nums[m] == target) {
				return m;
			} else if (nums[m] > target) {
				r = m - 1;
			} else {
				l = m + 1;
			}
		}

		return l;
	}

	public static void main(String[] args) {
		SearchInsertPOsition s = new SearchInsertPOsition();

		System.out.println(s.searchInsert(new int[] { 1, 3, 5, 6 }, 5));
		System.out.println(s.searchInsert(new int[] { 1, 3, 5, 6 }, 2));
		System.out.println(s.searchInsert(new int[] { 1, 3, 5, 6 }, 7));
		System.out.println(s.searchInsert(new int[] { 1, 3, 5, 6 }, 0));
	}
}
