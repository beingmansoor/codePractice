package com.two.pointers;

import java.util.Arrays;

public class RemoveDuplicates {

	public static int remove(int[] arr) {
		int nextNonDuplicate = 1; // index of the next non-duplicate element
		for (int i = 0; i < arr.length; i++) {
			if (arr[nextNonDuplicate] != arr[i]) {
				arr[nextNonDuplicate] = arr[i];
				nextNonDuplicate++;
			}
		}
		System.out.println(Arrays.toString(arr));
		return nextNonDuplicate;
	}

	public static int remove2(int[] arr, int key) {
		int nextElement = 0; // index of the next element which is not 'key'
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != key) {
				arr[nextElement] = arr[i];
				nextElement++;
			}
		}

		return nextElement;
	}

	public static int remove1(int[] arr) {

		int start = 0, end = start + 1, len = arr.length;

		while (start < end && end < arr.length) {
			if (arr[start] == arr[end]) {
				len--;
				end++;
			} else {
//				arr[end] = arr[start];
				start = end;
				end++;
			}
		}
		System.out.println(Arrays.toString(arr));
		return len;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 2,2, 3, 3, 3, 6, 9, 9 };
		System.out.println(RemoveDuplicates.remove(arr));

		arr = new int[] { 2, 2, 2, 11 };
		System.out.println(RemoveDuplicates.remove(arr));
	}
}