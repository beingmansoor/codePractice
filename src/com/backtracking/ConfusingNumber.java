package com.backtracking;

import java.util.Arrays;
import java.util.List;

public class ConfusingNumber {
	/*
	 * Given a number N, return true if and only if it is a confusing number,
	 * which satisfies the following condition:
	 * 
	 * We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8,
	 * 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively. When
	 * 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid. A
	 * confusing number is a number that when rotated 180 degrees becomes a
	 * different number with each digit valid.
	 * 
	 * 
	 */

	/*
	 * Hint : Reverse each digit with their corresponding new digit if an invalid digit
	 * is found the return -1. After reversing the digits just compare the
	 * reversed number with the original number.
	 */
	static boolean isConfusingNumber(int n) {

		int rotatedNumber = rotate(n);

		if (rotatedNumber != -1 && rotatedNumber != n)
			return true;
		return false;
	}

	private static int rotate(int n) {

		List<Integer> validDigits = Arrays.asList(0, 1, 6, 8, 9);
		int r = 0;

		for (; n != 0; n /= 10) {
			int k = n % 10;

			if (false == validDigits.contains(k))
				return -1;

			switch (k) {
			case 6:
				k = 9;
				break;
			case 9:
				k = 6;
				break;
			}

			r = r * 10 + k;

		}
		return 0;
	}

	public static void main(String[] args) {
		System.out.println(isConfusingNumber(12));
	}
}
