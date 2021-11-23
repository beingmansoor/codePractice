package com.array;

public class HappyLadyBugs {

	static long strangeCounter(long t) {

		int rem = 3;

		while (t > rem) {
			t = t - rem;
			rem *= 2;
		}

		return rem - t + 1;
	}

	static String happyLadybugs(String b) {
		int[] letters = new int[26];
		char[] in = b.toCharArray();

		int empty = 0;
		for (int i = 0; i < in.length; i++) {
			char c = in[i];

			if (c != '_') {
				letters[c - 'A']++;
			} else {
				empty++;
			}
		}
		if (empty == in.length) {
			return "YES";
		}

		boolean happy = true;
		if (empty == 0) {
			for (int i = 1; i < in.length - 1 && happy; i += 2) {
				if (in[i - 1] != in[i] && in[i + 1] != in[i]) {
					happy = false;
				}
			}
			if (!happy) {
				return "NO";
			}
		}

		for (int count : letters) {
			if (count == 1) {
				return "NO";
			}
		}

		return "YES";

	}

	public static void main(String[] args) {

		System.out.println(strangeCounter(22));
		System.out.println("=================");

		System.out.println(happyLadybugs("AABBC"));
		System.out.println(happyLadybugs("AABBC_C"));
		System.out.println(happyLadybugs("_"));
		System.out.println(happyLadybugs("DD__FQ_QQF"));
		System.out.println(happyLadybugs("AABCBC"));

	}

}
