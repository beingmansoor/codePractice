package com.array;

import java.util.Arrays;

public class CheckRotateArray {

	/*
	 * In this problem, you have to implement the void rotateArray(int[] arr)
	 * function which will pick the last index from the right and rotate it to
	 * the left. This means that the right-most element will appear at the
	 * left-most position in the array, and so on. However, in this problem, you
	 * only have to rotate by one element from the right.
	 * 
	 * Function Prototype # void rotateArray(int[] arr) Output # Array rotated
	 * by one element from right to left
	 * 
	 * Sample Input # arr = {1, 2, 3, 4, 5} Sample Output # arr = {5, 1, 2, 3,
	 * 4}
	 */
	public static void rotateArray1(int[] arr) {

		int l = 0, r = arr.length - 2;
		while (l < r) {
			swap(arr, l, r);
			l++;
			r--;
		}

		l = 0;
		r = arr.length - 1;

		while (l < r) {
			swap(arr, l, r);
			l++;
			r--;
		}

	}
	
	public static void rotateArray(int[] arr) {

	    //Store last element of Array.
	    //Start from the Second last and Right Shift the Array by one.
	    //Store the last element saved on the first index of the Array.
	    int lastElement = arr[arr.length - 1];

	    for (int i = arr.length - 1; i > 0; i--) {

	      arr[i] = arr[i - 1];
	    }

	    arr[0] = lastElement;
	  } 

	private static void swap(int[] arr, int l, int r) {
		int t = arr[l];
		arr[l] = arr[r];
		arr[r] = t;
	}

	public static void main(String[] args) {
		int a[] = { 1, 2, 3, 4, 5 };
		rotateArray(a);
		System.out.println(Arrays.toString(a));
	}
}