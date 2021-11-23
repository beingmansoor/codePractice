package com.two.pointers;

import java.util.*;

/*
 * Problem Statement #
Given an array arr of unsorted numbers and a target sum, count all triplets in it such that arr[i] + arr[j] + arr[k] < target where i, j, and k are three different indices. Write a function to return the count of such triplets.

Example 1:

Input: [-1, 0, 2, 3], target=3 
Output: 2
Explanation: There are two triplets whose sum is less than the target: [-1, 0, 3], [-1, 0, 2]
Example 2:

Input: [-1, 4, 2, 1, 3], target=5 
Output: 4
Explanation: There are four triplets whose sum is less than the target: 
   [-1, 1, 4], [-1, 1, 3], [-1, 1, 2], [-1, 2, 3]
 * 
 */
public class TripletWithSmallerSum {
	
	 public static int searchTripletCount(int[] arr, int target) {
		    Arrays.sort(arr);
		    int count = 0;
		    for (int i = 0; i < arr.length - 2; i++) {
		      count += searchPair(arr, target - arr[i], i);
		    }
		    return count;
		  }

		  private static int searchPair(int[] arr, int targetSum, int first) {
		    int count = 0;
		    int left = first + 1, right = arr.length - 1;
		    while (left < right) {
		      if (arr[left] + arr[right] < targetSum) { // found the triplet
		        // since arr[right] >= arr[left], therefore, we can replace arr[right] by any number between 
		        // left and right to get a sum less than the target sum
		        count += right - left;
		        left++;
		      } else {
		        right--; // we need a pair with a smaller sum
		      }
		    }
		    return count;
		  }

	
	public static List<List<Integer>> searchTriplets(int[] arr, int target) {
	    Arrays.sort(arr);
	    List<List<Integer>> triplets = new ArrayList<>();
	    for (int i = 0; i < arr.length - 2; i++) {
	      searchPair(arr, target - arr[i], i, triplets);
	    }
	    return triplets;
	  }

	  private static void searchPair(int[] arr, int targetSum, int first, List<List<Integer>> triplets) {
	    int left = first + 1, right = arr.length - 1;
	    while (left < right) {
	      if (arr[left] + arr[right] < targetSum) { // found the triplet
	        // since arr[right] >= arr[left], therefore, we can replace arr[right] by any number between 
	        // left and right to get a sum less than the target sum
	        for (int i = right; i > left; i--)
	          triplets.add(Arrays.asList(arr[first], arr[left], arr[i]));
	        left++;
	      } else {
	        right--; // we need a pair with a smaller sum
	      }
	    }
	  }
	  
	public static int searchTriplets1(int[] arr, int target) {
		int count = 0;

		Arrays.sort(arr);
		
		 List<List<Integer>> triplets = new ArrayList<>();

		for (int i = 0; i < arr.length - 2; i++) {
			int left = i + 1, right = arr.length - 1;

			while (left < right) {
				int sum = arr[left] + arr[i] + arr[right];
				if (sum < target) {
					count += (right - left);
					for(int j=right; j>left;j--)
					{
						triplets.add(Arrays.asList(arr[i], arr[left], arr[j]));
					}
					left++;
				} else {
					right--;
				}
			}

		}
		System.out.println(triplets);
		return count;
	}

	public static void main(String[] args) {
		System.out.println(TripletWithSmallerSum.searchTriplets(new int[] { -1, 0, 2, 3 }, 3));
		System.out.println(TripletWithSmallerSum.searchTriplets(new int[] { -1, 4, 2, 1, 3 }, 5));
	}
}