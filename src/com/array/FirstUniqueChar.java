package com.array;

import java.util.Arrays;

public class FirstUniqueChar {
	public static int firstUniqChar(String s) {

		int minIndex = Integer.MAX_VALUE;

		int[] letters = new int[26];
		Arrays.fill(letters, -1);
		int len = s.length();

		for (int i = 0; i < len; i++) {
			int aIndex = s.charAt(i) - 'a';
			if (letters[aIndex] == -1) {
				letters[aIndex] = i;
			} else {
				letters[aIndex] += len;
			}
		}

		System.out.println(Arrays.toString(letters));
		for (int i = 0; i < 26; i++) {
			if (letters[i] != -1 && letters[i] < len) {
				minIndex = Math.min(minIndex, letters[i]);
			}
		}

		return minIndex != Integer.MAX_VALUE ? minIndex : -1;
	}

	public static void main(String[] args) {
		System.out.println(firstUniqChar("leetcode"));
		System.out.println(firstUniqChar("aadadaad"));
	}
}
