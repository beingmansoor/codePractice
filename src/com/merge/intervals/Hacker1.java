package com.merge.intervals;

public class Hacker1 {

	static long arrayManipulation(int n, int[][] queries) {

		long[] a = new long[n + 1];
		for (int[] q : queries) {
			a[q[0]] += q[2];
			if (q[1] + 1 <= n) {
				a[q[1] + 1] -= q[2];
			}
		}

		long max = Integer.MIN_VALUE;
		long sum = 0;
		for (int i = 1; i <= n; i++) {
			sum = sum + a[i];
			if (max < sum) {
				max = sum;
			}
		}

		return max;

	}

	public static void main(String[] args) {
		System.out.println(arrayManipulation(10, new int[][] { { 2, 6, 10 }, { 1, 8, 1 }, { 5, 9, 15 } }));
	}
}
