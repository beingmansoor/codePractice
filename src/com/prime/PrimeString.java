package com.prime;

import java.util.Arrays;
import java.util.TreeSet;

public class PrimeString {
	public static void main(String[] args) {
		String s1 = "ABc", s2 = "CABa";
		System.out.println(getPrimeString(s1));
		System.out.println(getPrimeString(s2));
	}

	private static String getPrimeString(String s) {
		int n = 128;
		boolean[] isPrime = new boolean[n];
		Arrays.fill(isPrime, true);
		for (int i = 2; i * i < n; i++) {
			if (isPrime[i]) {
				for (int j = i; i * j < n; j++)
					isPrime[i * j] = false;
			}
		}
		TreeSet<Integer> set = new TreeSet<>();
		for (int i = 65; i < 123; i++) {
			if (i > 90 && i < 97)
				continue;
			if (isPrime[i])
				set.add(i);
		}
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			int asciiVal = c + 0;
			Integer ceiling = set.ceiling(asciiVal), floor = set.floor(asciiVal);
			if (ceiling != null && floor != null) {
				if (ceiling - asciiVal < asciiVal - floor) {
					sb.append((char) (ceiling + 0));
				} else {
					sb.append((char) (floor + 0));
				}
			} else if (ceiling == null) {
				sb.append((char) (floor + 0));
			} else if (floor == null) {
				sb.append((char) (ceiling + 0));
			}
		}
		return sb.toString();
	}
}
