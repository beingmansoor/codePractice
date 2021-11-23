package com.array;

import java.util.Arrays;

public class PlusOne {
	public int[] plusOne(int[] digits) {

		int n = digits.length;
		for (int i = n - 1; i >= 0; i--) {
			if (digits[i] < 9) {
				digits[i]++;
				return digits;
			}

			digits[i] = 0;
		}

		int[] newNumber = new int[n + 1];
		newNumber[0] = 1;

		return newNumber;

	}

	public static void main(String[] args) {
		PlusOne p = new PlusOne();
		System.out.println(Arrays.toString(p.plusOne(new int[] { 8, 9, 9, 9 })));
		System.out.println(Arrays.toString(p.plusOne(new int[] { 8, 9, 4, 9 })));
		System.out.println(Arrays.toString(p.plusOne(new int[] { 1, 9, 9 })));
		System.out.println(Arrays.toString(p.plusOne(new int[] { 1, 8, 9 })));
		System.out.println(Arrays.toString(p.plusOne(new int[] { 9, 9 })));
	}
}
