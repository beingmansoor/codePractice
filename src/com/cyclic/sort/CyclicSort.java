package com.cyclic.sort;

/*
 * 
 * We are given an array containing n objects. Each object, when created, was assigned 
 * a unique number from 1 to n based on their creation sequence. 
 * This means that the object with sequence number 3 was created just before the object 
 * with sequence number 4.

Write a function to sort the objects in-place on their creation sequence number in O(n)O(n) 
and without any extra space. For simplicity, lets assume we are passed an integer array 
containing only the sequence numbers, though each number is actually an object.

Example 1:

Input: [3, 1, 5, 4, 2]
Output: [1, 2, 3, 4, 5]
Example 2:

Input: [2, 6, 4, 3, 1, 5]
Output: [1, 2, 3, 4, 5, 6]
Example 3:

Input: [1, 5, 6, 4, 3, 2]
Output: [1, 2, 3, 4, 5, 6]
 */
public class CyclicSort {

	public static void sort(int[] nums) {
		int i = 0;
		while (i < nums.length) {
			int j = nums[i] - 1;
			if (nums[i] != nums[j])
				swap(nums, i, j);
			else
				i++;
		}
	}

	public static void sort1(int[] nums) {

		int i = 0;
		while (i < nums.length) {
			if (nums[i] != (i + 1))
				swap(nums, i, nums[i] - 1);
			else
				i++;
		}
	}

	static void swap(int[] nums, int a, int b) {
		int t = nums[a];
		nums[a] = nums[b];
		nums[b] = t;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 3, 1, 5, 4, 2 };
		CyclicSort.sort(arr);
		for (int num : arr)
			System.out.print(num + " ");
		System.out.println();

		arr = new int[] { 2, 6, 4, 3, 1, 5 };
		CyclicSort.sort(arr);
		for (int num : arr)
			System.out.print(num + " ");
		System.out.println();

		arr = new int[] { 1, 5, 6, 4, 3, 2 };
		CyclicSort.sort(arr);
		for (int num : arr)
			System.out.print(num + " ");
		System.out.println();
	}
}