package com.cyclic.sort;

import java.util.Arrays;

public class MissingNumber {
	
	public static int findMissingNumber(int[] nums) {
	    int i = 0;
	    while (i < nums.length) {
	      if (nums[i] < nums.length && nums[i] != nums[nums[i]])
	        swap(nums, i, nums[i]);
	      else
	        i++;
	    }

	    // find the first number missing from its index, that will be our required number
	    for (i = 0; i < nums.length; i++)
	      if (nums[i] != i)
	        return i;

	    return nums.length;
	  }
	

	public static int findMissingNumber1(int[] nums) {

		int i = 0;

		while (i < nums.length) {
			if(nums[i] != i && nums[i] < nums.length)
				swap(nums, i, nums[i]);
			else
				i++;
		}
		
		i=0;
		while(i < nums.length)
		{
			if(nums[i]==i)
				i++;
			else
				return i;
		}
		
		return -1;
	}

	static void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static void main(String[] args) {
		System.out.println(findMissingNumber(new int[] { 4, 0, 3, 1 }));
		System.out.println(findMissingNumber(new int[] { 8, 3, 5, 2, 4, 6, 0, 1 }));
	}
}
