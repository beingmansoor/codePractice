package com.bits;

public class SumAndSubtract {

	public int getSum(int a, int b) {
		if (a == 0) return b;
		if (b == 0) return a;

		while (b != 0) {
			int carry = a & b;
			a = a ^ b;
			b = carry << 1;
		}
		
		return a;
	}

	// Iterative
	public int getSubtract(int a, int b) {
		while (b != 0) {
			int borrow = (~a) & b;
			a = a ^ b;
			b = borrow << 1;
		}
		
		return a;
	}

	// Recursive
	public int getSumRec(int a, int b) {
		return (b == 0) ? a : getSumRec(a ^ b, (a & b) << 1);
	}

	// Recursive
	public int getSubtractRec(int a, int b) {
		return (b == 0) ? a : getSubtractRec(a ^ b, (~a & b) << 1);
	}

	// Get negative number
	public int negate(int x) {
		return ~x + 1;
	}
	
	public static void main(String[] args) {
		int a = 1<<30 ;
		System.out.println( a  + "\t" + Integer.MIN_VALUE);
	}
}
