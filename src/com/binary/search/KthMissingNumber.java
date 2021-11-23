package com.binary.search;

public class KthMissingNumber
{

	public int missingElement(int[] nums, int k)
	{
		int n = nums.length;
		if (k > missing(n - 1, nums))
		{
			return nums[n - 1] + k - missing(n - 1, nums);
		}
		int start = 0;
		int end = n - 1;
		while (start < end)
		{
			int mid = start + (end - start) / 2;
			if (missing(mid, nums) < k)
			{
				start = mid + 1;
			}
			else
			{
				end = mid;
			}
		}
		return nums[start - 1] + k - missing(start - 1, nums);
	}

	private int missing(int idx, int[] nums)
	{
		return nums[idx] - nums[0] - idx;
	}

	static int getMissingID(int[] arr, int left, int right, int new_n)
	{
		if ((left + 1) == right)
			return arr[left] + new_n;

		int middle = (left + right) / 2;

		int MissingNums = (arr[middle] - arr[left]) - (middle - left);
		if (new_n > MissingNums)
			return getMissingID(arr, middle, right, new_n - MissingNums);
		else
			return getMissingID(arr, left, middle, new_n);
	}

	public static void main(String args[])
	{
		// Driver code

		int[] processes = { 5, 7, 9, 10, 13 };
		System.out.println(getMissingID(processes, 3));
	}

	private static int getMissingID(int[] processes, int k)
	{
		return getMissingID(processes, 0, processes.length, k);
	}
}
