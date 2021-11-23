package com.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * You are given an integer array nums and you have to return a new counts array. 
 * The counts array has the property where counts[i] is the number of smaller elements to the 
 * right of nums[i].

Example:

Input: [5,2,6,1]
Output: [2,1,1,0] 
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.

 */
class TreeNode1 {
	int val;
	int num;
	TreeNode1 left;
	TreeNode1 right;

	TreeNode1(int x) {
		val = x;
		num = 0;
	}
}

public class CountSmallerElements {
	public List<Integer> countSmaller(int[] nums) {
		if (nums.length == 0)
			return new ArrayList<Integer>();
		List<Integer> list = new ArrayList<Integer>();
		List<Integer> list1 = new ArrayList<Integer>();
		TreeNode1 root = new TreeNode1(nums[nums.length - 1]);
		root.num = 1;
		list.add(0);
		for (int i = nums.length - 2; i >= 0; i--) {
			list.add(get(root, nums[i], 0));
		}
		for (int i = nums.length - 1; i >= 0; i--)
			list1.add(list.get(i));
		return list1;
	}

	public int get(TreeNode1 root, int val, int num) {
		if (root.val >= val) {
			root.num = root.num + 1;
			if (root.left == null) {
				TreeNode1 node = new TreeNode1(val);
				node.num = 1;
				root.left = node;
				return num;
			} else {
				return get(root.left, val, num);
			}
		} else {
			num += root.num;
			if (root.right == null) {
				TreeNode1 node = new TreeNode1(val);
				node.num = 1;
				root.right = node;
				return num;
			} else {
				return get(root.right, val, num);
			}
		}
	}

	public List<Integer> countSmaller1(int[] nums) {
		Integer[] ans = new Integer[nums.length];
		List<Integer> sorted = new ArrayList<Integer>();
		for (int i = nums.length - 1; i >= 0; i--) {
			int index = findIndex(sorted, nums[i]);
			ans[i] = index;
			sorted.add(index, nums[i]);
		}
		return Arrays.asList(ans);
	}

	private int findIndex(List<Integer> sorted, int target) {
		if (sorted.size() == 0)
			return 0;
		int start = 0;
		int end = sorted.size() - 1;
		if (sorted.get(end) < target)
			return end + 1;
		if (sorted.get(start) >= target)
			return 0;
		while (start <= end) {
			int mid = start + (end - start) / 2;
			if (sorted.get(mid) < target) {
				start = mid + 1;
			} else {
				end = mid-1;
			}
		}
		// if (sorted.get(start) >= target)
		// return start;
		// return end;

		 return start;
	}

	public static void main(String[] args) {
		CountSmallerElements c = new CountSmallerElements();
		System.out.println(c.countSmaller(new int[] { 5, 2, 6, 1 }));
	}
}
