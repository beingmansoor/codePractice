package com.subsets;

import java.util.ArrayList;

public class PermutationSequence {
	public String getPermutation(int n, int k) {

		StringBuilder sb = new StringBuilder();
		int[] fact = new int[n + 1];

		fact[0] = 1;

		for (int i = 1; i <= n; i++) {
			fact[i] = i * fact[i - 1];
		}

		ArrayList<Integer> digits = new ArrayList<>();

		for (int i = 1; i <= n; i++) {
			digits.add(i);
		}

		k--;
		for (int i = n - 1; i >= 0; i--) {
			int index = (k / fact[i]);

			sb.append(digits.get(index));

			digits.remove(index);

			k = k % fact[i];
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		PermutationSequence p = new PermutationSequence();

		System.out.println(p.getPermutation(3, 3));
		System.out.println(p.getPermutation(4, 9));
	}
}
