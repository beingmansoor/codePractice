package com.topK;

import java.util.*;

public class KLargestNumbers {

	/*
	 * Given an unsorted array of numbers, find the ‘K’ largest numbers in it.
	 * 
	 * Note: For a detailed discussion about different approaches to solve this
	 * problem, take a look at Kth Smallest Number.
	 * 
	 * Example 1:
	 * 
	 * Input: [3, 1, 5, 12, 2, 11], K = 3 Output: [5, 12, 11] Example 2:
	 * 
	 * Input: [5, 12, 11, -1, 12], K = 3 Output: [12, 11, 12]
	 */
	public static List<Integer> findKLargestNumbers(int[] nums, int k) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
		// put first 'K' numbers in the min heap
		for (int i = 0; i < k; i++)
			minHeap.add(nums[i]);

		// go through the remaining numbers of the array, if the number from the
		// array is bigger than the
		// top (smallest) number of the min-heap, remove the top number from
		// heap and add the number from array
		for (int i = k; i < nums.length; i++) {
			if (nums[i] > minHeap.peek()) {
				minHeap.poll();
				minHeap.add(nums[i]);
			}
		}

		// the heap has the top 'K' numbers, return them in a list
		return new ArrayList<>(minHeap);
	}
	
	public static List<Integer> findKSmallestNumbers(int[] nums, int k) {
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((n1, n2) -> n2 - n1);
		// put first 'K' numbers in the min heap
		for (int i = 0; i < k; i++)
			maxHeap.add(nums[i]);

		// go through the remaining numbers of the array, if the number from the
		// array is bigger than the
		// top (smallest) number of the min-heap, remove the top number from
		// heap and add the number from array
		for (int i = k; i < nums.length; i++) {
			if (nums[i] < maxHeap.peek()) {
				maxHeap.poll();
				maxHeap.add(nums[i]);
			}
		}

		// the heap has the top 'K' numbers, return them in a list
		return new ArrayList<>(maxHeap);
	}

	public static void main(String[] args) {
		List<Integer> result = KLargestNumbers.findKLargestNumbers(new int[] { 3, 1, 5, 12, 2, 11 }, 3);
		System.out.println("Here are the top K numbers: " + result);
		
		System.out.println("Here are the top K small number" + KLargestNumbers.findKSmallestNumbers(new int[] { 3, 1, 5, 12, 2, 11 }, 3));

		result = KLargestNumbers.findKLargestNumbers(new int[] { 5, 12, 11, -1, 12 }, 3);
		System.out.println("Here are the top K numbers: " + result);
	}
}
