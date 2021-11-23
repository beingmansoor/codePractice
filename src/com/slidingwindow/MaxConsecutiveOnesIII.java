package com.slidingwindow;

public class MaxConsecutiveOnesIII {

	static int max(int[] a, int k) {
		int i = 0, j;

		for (j = 0; j < a.length; j++) {
			if (a[j] == 0)
				k--;

			if (k < 0 && a[i++] == 0)
				k++;
		}
		return j - i;

	}

	public static void main(String[] args) {
		System.out.println(max(new int[] { 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 }, 2));
	}
}
