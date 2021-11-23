package com.two.pointers;

/*
 * 
 * Problem Statement 
	Given a sorted array, create a new array containing squares of all the number
 	of the input array in the sorted order.

	Example 1:

	Input: [-2, -1, 0, 2, 3]
	Output: [0, 1, 4, 4, 9]

 */
public class SortedArraySquares {
	
	public static int[] makeSquares(int[] arr) {
	    int n = arr.length;
	    int[] squares = new int[n];
	    int highestSquareIdx = n - 1;
	    int left = 0, right = arr.length - 1;
	    while (left <= right) {
	      int leftSquare = arr[left] * arr[left];
	      int rightSquare = arr[right] * arr[right];
	      if (leftSquare > rightSquare) {
	        squares[highestSquareIdx--] = leftSquare;
	        left++;
	      } else {
	        squares[highestSquareIdx--] = rightSquare;
	        right--;
	      }
	    }
	    return squares;
	  }


	public static void main(String[] args) {

		int[] result = SortedArraySquares.makeSquares(new int[] { -2, -1, 0, 2, 3 });
		for (int num : result)
			System.out.print(num + " ");
		System.out.println();

		result = SortedArraySquares.makeSquares(new int[] { -3, -1, 0, 1, 2 });
		for (int num : result)
			System.out.print(num + " ");
		System.out.println();
	}
}