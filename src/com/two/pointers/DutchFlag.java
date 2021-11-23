package com.two.pointers;

import java.util.Arrays;

/*
 * 
 * Problem Statement #
Given an array containing 0s, 1s and 2s, sort the array in-place.
 You should treat numbers of the array as objects, hence, we can’t count 0s, 1s, and 2s 
 to recreate the array.

The flag of the Netherlands consists of three colors: red, white and blue; 
and since our input array also consists of three different numbers that
 is why it is called Dutch National Flag problem.

Example 1:

Input: [1, 0, 2, 1, 0]
Output: [0 0 1 1 2]
Example 2:

Input: [2, 2, 0, 1, 2, 0]
Output: [0 0 1 2 2 2 ]
 * 
 */
public class DutchFlag {

	public static void sort(int[] arr) {
		// all elements < low are 0 and all elements > high are 2
		// all elements from >= low < i are 1
		int low = 0, high = arr.length - 1;
		for (int i = 0; i <= high;) {
			if (arr[i] == 0) {
				swap(i, low, arr);
				// increment 'i' and 'low'
				i++;
				low++;
			} else if (arr[i] == 1) {
				i++;
			} else { // the case for arr[i] == 2
				swap(i, high, arr);
				// decrement 'high' only, after the swap the number at index 'i'
				// could be 0, 1 or 2
				high--;
			}
		}
		System.out.println(Arrays.toString(arr));
	}

	public static void sort1(int[] arr) {
		int length = arr.length-1;
		int zeros = 0, twos = length;

		System.out.println(Arrays.toString(arr));

		while (arr[zeros] == 0) {
			zeros++;
		}
		while (arr[twos] == 2) {
			twos--;
		}
		
		for(int i=zeros; i<=twos;)
		{
			if(arr[i] ==2)
			{
				swap(i, twos, arr);
				twos--;
			}
			else
			{
				if(arr[i] ==0)
				{
					swap(i, zeros, arr);
					zeros++;
				}
				i++;
			}
		}


		System.out.println(Arrays.toString(arr));
	}

	static void swap(int i, int j, int[] a) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 1, 0, 2, 1, 0 };
		DutchFlag.sort1(arr);

		int[] arr1 = new int[] { 2, 2, 0, 1, 2, 0 };
		DutchFlag.sort1(arr1);
	}
}