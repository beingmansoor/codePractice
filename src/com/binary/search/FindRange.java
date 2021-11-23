package com.binary.search;

public class FindRange
{

	/*
	 * Given an array of numbers sorted in ascending order, find the range of a
	 * given number ‘key’. The range of the ‘key’ will be the first and last
	 * position of the ‘key’ in the array.
	 * 
	 * Write a function to return the range of the ‘key’. If the ‘key’ is not
	 * present return [-1, -1].
	 * 
	 * Example 1:
	 * 
	 * Input: [4, 6, 6, 6, 9], key = 6 Output: [1, 3] Example 2:
	 * 
	 * Input: [1, 3, 8, 10, 15], key = 10 Output: [3, 3] Example 3:
	 * 
	 * Input: [1, 3, 8, 10, 15], key = 12 Output: [-1, -1]
	 */
	public static int[] findRange(int[] arr, int key)
	{
		int[] result = new int[] { -1, -1 };
		result[0] = search(arr, key, false);
		if (result[0] != -1) // no need to search, if 'key' is not present in
								// the input array
			result[1] = search(arr, key, true);
		return result;
	}

	private static int firstGreaterEqual(int[] A, int target)
	{
		int low = 0, high = A.length;
		while (low < high)
		{
			int mid = low + ((high - low) >> 1);
			// low <= mid < high
			if (A[mid] < target)
			{
				low = mid + 1;
			}
			else
			{
				// should not be mid-1 when A[mid]==target.
				// could be mid even if A[mid]>target because mid<high.
				high = mid;
			}
		}
		return low;
	}

	// modified Binary Search
	private static int search(int[] arr, int key, boolean findMaxIndex)
	{
		int keyIndex = -1;
		int start = 0, end = arr.length - 1;
		while (start <= end)
		{
			int mid = start + (end - start) / 2;
			if (key < arr[mid])
			{
				end = mid - 1;
			}
			else if (key > arr[mid])
			{
				start = mid + 1;
			}
			else
			{ // key == arr[mid]
				keyIndex = mid;
				if (findMaxIndex)
					start = mid + 1; // search ahead to find the last index of
										// 'key'
				else
					end = mid - 1; // search behind to find the first index of
									// 'key'
			}
		}
		return keyIndex;
	}

	public static int find(int[] a, int k)
	{
		int s = 0, e = a.length;
		while (s < e)
		{
			int m = s + (e - s) / 2;
			if (a[m] < k)
				s = m + 1;
			else
				e = m;
		}
		return s;
	}

	public static int[] findRange1(int[] arr, int key)
	{
		int[] result = new int[] { -1, -1 };

		int start = 0, end = arr.length - 1;
		boolean isKeyFound = false;
		while (start <= end)
		{
			int m = end - (end - start) / 2;
			if (arr[m] < key)
			{
				start = m + 1;
			}
			else
			{
				if (arr[m] == key)
				{
					isKeyFound = true;
				}
				end = m - 1;
			}
		}

		if (!isKeyFound)
			return result;
		result[0] = start;

		start = 0;
		end = arr.length - 1;
		while (start <= end)
		{
			int m = end - (end - start) / 2;
			if (arr[m] > key)
			{
				end = m - 1;
			}
			else
			{
				start = m + 1;
			}
		}
		result[1] = end;
		return result;
	}

	public int[] searchRange(int[] nums, int target)
	{
		double left = target - 0.5, right = target + 0.5;
		int l = bs(nums, left), r = bs(nums, right);
		if (l == r)
			return new int[] { -1, -1 };
		return new int[] { l, r - 1 };
	}

	public int bs(int[] nums, double target)
	{
		int l = 0, h = nums.length - 1;
		while (l <= h)
		{
			int m = l + (h - l) / 2;
			if (target > nums[m])
				l = m + 1;
			else
				h = m - 1;
		}
		return l;
	}

	public static void main(String[] args)
	{
		int[] result = FindRange.findRange(new int[] { 4, 6, 6, 6, 9 }, 6);
		System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
		result = FindRange.findRange(new int[] { 1, 3, 8, 10, 15 }, 10);
		System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
		result = FindRange.findRange(new int[] { 1, 3, 8, 10, 15 }, 12);
		System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
	}
}