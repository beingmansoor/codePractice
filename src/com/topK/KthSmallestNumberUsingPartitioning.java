package com.topK;

import java.util.Random;

public class KthSmallestNumberUsingPartitioning {

	public static int findKthSmallestNumber(int[] nums, int k) {
		return findKthSmallestNumberRec(nums, k, 0, nums.length - 1);
	}

	public static int findKthSmallestNumberRec(int[] nums, int k, int start, int end) {
		int p = partition(nums, start, end);

		if (p == k - 1)
			return nums[p];

		if (p > k - 1) // search lower part
			return findKthSmallestNumberRec(nums, k, start, p - 1);

		// search higher part
		return findKthSmallestNumberRec(nums, k, p + 1, end);
	}

	private static int partition(int[] nums, int low, int high) {
		if (low == high)
			return low;

		int pivot = nums[high];
		for (int i = low; i < high; i++) {
			// all elements less than 'pivot' will be before the index 'low'
			if (nums[i] < pivot)
				swap(nums, low++, i);
		}
		// put the pivot in its correct place
		swap(nums, low, high);
		return low;
	}
	
	private static int partitionUsingRandomizedQuickSort(int[] nums, int low, int high) {
	    if (low == high)
	      return low;

	    Random randomNum = new Random();
	    int pivotIndex = low + randomNum.nextInt(high - low);
	    swap(nums, pivotIndex, high);

	    int pivot = nums[high];
	    for (int i = low; i < high; i++) {
	      // all elements less than 'pivot' will be before the index 'low'
	      if (nums[i] < pivot)
	        swap(nums, low++, i);
	    }
	    // put the pivot in its correct place
	    swap(nums, low, high);
	    return low;
	  }

	

	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	/*
	 * Time & Space Complexity # The above algorithm is known as QuickSelect and
	 * has a Worst case time complexity of O(N^2). The best and
	 * average case is O(N), which is better than the best and average case
	 * of QuickSort. Overall, QuickSelect uses the same approach as QuickSort
	 * i.e., partitioning the data into two parts based on a pivot. However,
	 * contrary to QuickSort, instead of recursing into both sides QuickSelect
	 * only recurses into one side – the side with the element it is searching
	 * for. This reduces the average and best case time complexity from
	 * O(N*logN) to O(N).
	 * 
	 * The worst-case occurs when, at every step, the partition procedure splits
	 * the N-length array into arrays of size ‘1’ and ‘N−1’. This can only
	 * happen when the input array is sorted or if all of its elements are the
	 * same. This “unlucky” selection of pivot elements requires O(N)
	 * recursive calls, leading to an O(N^2) worst-case.
	 * 
	 * Worst-case space complexity will be O(N)O(N) used for the recursion stack
	 */

	public static void main(String[] args) {
		int result = KthSmallestNumberUsingPartitioning.findKthSmallestNumber(new int[] { 1, 5, 12, 2, 11, 5 }, 3);
		System.out.println("Kth smallest number is: " + result);

		// since there are two 5s in the input array, our 3rd and 4th smallest
		// numbers should be a '5'
		result = KthSmallestNumberUsingPartitioning.findKthSmallestNumber(new int[] { 1, 5, 12, 2, 11, 5 }, 4);
		System.out.println("Kth smallest number is: " + result);

		result = KthSmallestNumberUsingPartitioning.findKthSmallestNumber(new int[] { 5, 12, 11, -1, 12 }, 3);
		System.out.println("Kth smallest number is: " + result);
	}
}