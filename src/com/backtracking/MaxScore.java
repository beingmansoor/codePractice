package com.backtracking;

public class MaxScore {

	public static void main(String[] args) {
		int scores[] = new int[] { 1, 0, 9, 5, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int[] frequencies = new int[26];
		frequencies[0] = 1;
		frequencies[1] = 1;
		frequencies[2] = 1;
		frequencies[3] = 3;
		frequencies[6] = 1;
		frequencies['o' - 'a'] = 2;

		String[] words = new String[] { "dog", "cat", "dad", "good" };
		System.out.println(solution(words, frequencies, scores, 0));
	}

	public static int solution(String[] words, int[] farr, int[] score, int idx) {
		if (idx == words.length) {
			return 0;
		}

		int m2 = solution(words, farr, score, idx + 1);

		String word = words[idx];

		boolean canInclude = true;

		char[] chars = word.toCharArray();
		int scores = 0;
		for (char c : chars) {
			if (farr[c - 'a'] == 0)
				canInclude = false;
			farr[c - 'a']--;
			scores += score[c - 'a'];
		}
		int m1 = 0;
		if (canInclude) {
			m1 = scores + solution(words, farr, score, idx + 1);
		}
		for (char c : chars) {
			farr[c - 'a']++;
		}
		return Math.max(m1, m2);
	}

}
