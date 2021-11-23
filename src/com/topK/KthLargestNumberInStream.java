package com.topK;

import java.util.*;

public class KthLargestNumberInStream {

	/*
	 * Design a class to efficiently find the Kth largest element in a stream of numbers.

	The class should have the following two things:
	
	The constructor of the class should accept an integer array containing initial numbers from the stream and 
	an integer ‘K’.
	The class should expose a function add(int num) which will store the given number and return the Kth largest 
	number.
	Example 1:
	
	Input: [3, 1, 5, 12, 2, 11], K = 4
	1. Calling add(6) should return '5'.
	2. Calling add(13) should return '6'.
	2. Calling add(4) should still return '6'.
	 */
	PriorityQueue<Integer> minHeap = new PriorityQueue<>();
	
	final int k;

	  public KthLargestNumberInStream(int[] nums, int k) {
	    this.k = k;
	    // add the numbers in the min heap
	    for (int i = 0; i < nums.length; i++)
	      add(nums[i]);
	  }

	  public int add(int num) {
	    // add the new number in the min heap
	    minHeap.add(num);

	    // if heap has more than 'k' numbers, remove one number
	    if (minHeap.size() > this.k)
	      minHeap.poll();

	    // return the 'Kth largest number
	    return minHeap.peek();
	  }
	  
//	public KthLargestNumberInStream(int[] nums, int k) {
//		for(int i=0;i<k && i <nums.length;i++)
//			minHeap.add(nums[i]);
//		for(int i =k; i< nums.length;i++)
//		{
//			if(minHeap.peek() < nums[i])
//			{
//				minHeap.poll();
//				minHeap.add(nums[i]);
//			}
//		}
//	}

	public int add1(int num) {
		if(minHeap.peek() < num)
		{
			minHeap.poll();
			minHeap.add(num);
		}
		return minHeap.peek();
	}

	public static void main(String[] args) {
		int[] input = new int[] { 3, 1, 5, 12, 2, 11 };
		KthLargestNumberInStream kthLargestNumber = new KthLargestNumberInStream(input, 4);
		System.out.println("4th largest number is: " + kthLargestNumber.add(6));
		System.out.println("4th largest number is: " + kthLargestNumber.add(13));
		System.out.println("4th largest number is: " + kthLargestNumber.add(4));
	}
}