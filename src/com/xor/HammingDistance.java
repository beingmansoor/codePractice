package com.xor;

public class HammingDistance {
	/*
	 * Given two integers, the task is to find the hamming distance between two
	 * integers. Hamming Distance between two integers is the number of bits
	 * which are different at same position in both numbers.
	 * 
	 * Examples:
	 * 
	 * Input: n1 = 9, n2 = 14 Output: 3
	 *  9 = 1001, 14 = 1110 No. of Different bits = 3
	 * 
	 * Input: n1 = 4, n2 = 8 Output: 2
	 * 
	 * Approach:
		Calculate the XOR of two numbers.
		Count the number of set bits.
	 */
	static int hammingDistance(int n1, int n2) {
		int x = n1 ^ n2;
		int setBits = 0;

		while (x > 0) {
			setBits += x & 1;
			x >>= 1;
		}

		return setBits;
	}

	// Driver code
	public static void main(String[] args) {
		int n1 = 9, n2 = 14;
		System.out.println(hammingDistance(n1, n2));
	}
}