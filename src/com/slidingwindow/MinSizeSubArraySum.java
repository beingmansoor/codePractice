package com.slidingwindow;

public class MinSizeSubArraySum {
	
	public static int findMinSubArray1(int S, int[] arr) {
	    int windowSum = 0, minLength = Integer.MAX_VALUE;
	    int windowStart = 0;
	    for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
	      windowSum += arr[windowEnd]; // add the next element
	      // shrink the window as small as possible until the 'windowSum' is smaller than 'S'
	      while (windowSum >= S) {
	        minLength = Math.min(minLength, windowEnd - windowStart + 1);
	        windowSum -= arr[windowStart]; // subtract the element going out
	        windowStart++; // slide the window ahead
	      }
	    }

	    return minLength == Integer.MAX_VALUE ? 0 : minLength;
	  }
	public static int findMinSubArray(int S, int[] arr) {
		int start = 0, end = 0;

		int sum = 0, minLength = Integer.MAX_VALUE - 1, sIndex = 0, eIndex = 0;
		while (end < arr.length) {
			sum += arr[end];
			while (sum >= S) {
				minLength = Math.min(minLength, (end - start +1));
				sIndex = start;
				eIndex = end;
				sum -= arr[start];
				start ++;
			}
			end++;
		}
		System.out.println(sIndex + "\t" + eIndex);
		return (minLength != Integer.MAX_VALUE - 1) ? minLength : 0;
	}

	public static void main(String[] args) {
		System.out.println(findMinSubArray1(7, new int[] { 2, 1, 5, 2, 3, 2 }));
		System.out.println(findMinSubArray1(7, new int[]{2, 1, 5, 2, 8}));
		System.out.println(findMinSubArray1(8, new int[]{3, 4, 1, 1, 6}));
	}
}