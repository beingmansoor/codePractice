package com.xor;

public class SingleNumberII {
	public int singleNumber(int[] nums) {

		int ones = 0;
		int twos = 0;

		for (int n : nums) {
			ones = (ones ^ n) & ~twos;
			twos = (twos ^ n) & ~ones;
		}

		return ones;
	}

	public int singleNumber2(int[] nums) {

		int tn = Integer.MAX_VALUE, tnp1 = 0, tnp2 = 0;
		int cmtn = 0, cmtnp1 = 0, cmtnp2 = 0;

		for (int n : nums) {
			cmtn = n & tn;
			cmtnp1 = n & tnp1;
			cmtnp2 = n & tnp2;

			tn = tn & (~cmtn);
			tnp1 = tnp1 | cmtn;

			tnp1 = tnp1 & (~cmtnp1);
			tnp2 = tnp2 | cmtnp1;

			tnp2 = tnp2 & (~cmtnp2);
			tn = tn | cmtnp2;

		}

		return tnp1;
	}

	public static void main(String[] args) {
		SingleNumberII s = new SingleNumberII();
		System.out.println(s.singleNumber(new int[] { 2, 2, 3, 2 }) + "\t" + s.singleNumber2(new int[] { 2, 2, 3, 2 }));
		System.out.println(s.singleNumber(new int[] { 0, 1, 0, 1, 0, 1, 99 }) + "\t"
				+ s.singleNumber2(new int[] { 0, 1, 0, 1, 0, 1, 99 }));
	}
}
