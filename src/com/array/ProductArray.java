package com.array;

public class ProductArray {
	/*
	 * In this problem, you have to implement the int[] findProduct(int[] arr)
	 * function which will modify the whole array in such a way that each index
	 * will have a product of all numbers present in the array (except the
	 * number stored on the current index).
	 * 
	 * Function Prototype # int[] findProduct(int[] arr) Input # An array of
	 * integers. This array can be of any (valid) size and elements can be
	 * repeated.
	 * 
	 * Output # An array with products stored at each position.
	 * 
	 * Sample Input # arr = {1,2,3,4} Sample Output # arr = {24,12,8,6}
	 */
	public static int[] findProduct(int arr[]) {
		int n = arr.length;
		int i, temp = 1;

		// Allocation of result array
		int result[] = new int[n];

		// Initializing the result array by 1
		for (int j = 0; j < n; j++)
			result[j] = 1;

		// Product of elements on left side excluding arr[i]
		for (i = 0; i < n; i++) {
			result[i] = temp;
			temp *= arr[i];
		}

		// Initializing temp to 1 for product on right side
		temp = 1;

		// Product of elements on right side excluding arr[i]
		for (i = n - 1; i >= 0; i--) {
			result[i] *= temp;
			temp *= arr[i];
		}

		return result;
	}

	public static String arrayToString(int arr[]) {
		if (arr.length > 0) {
			String result = "";
			for (int i = 0; i < arr.length; i++) {
				result += arr[i] + " ";
			}
			return result;
		} else {
			return "Empty Array!";
		}
	}

	public static void main(String args[]) {

		int[] arr = { -1, 2, -3, 4, -5 };

		System.out.println("Array before product: " + arrayToString(arr));

		int[] prodArray = findProduct(arr);

		System.out.println("Array after product: " + arrayToString(prodArray));
	}
}