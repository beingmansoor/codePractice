package com.bits;

import java.util.Arrays;

public class CountSetBits {
	public int[] countBits(int num) {

		if (num == 0) {
			return new int[] { 0 };
		}
		int[] r = new int[num + 1];
		r[0] = 0;
		r[1] = 1;
		for (int i = 2; i <= num; i++) {
			if (i % 2 == 0) {
				r[i] = r[i / 2];
			} else {
				r[i] = r[i / 2] + 1;
			}
		}
		return r;
	}

	public static void main(String[] args) {
		CountSetBits c = new CountSetBits();
		System.out.println(Arrays.toString(c.countBits(127)));
	}
}
