package com.slidingwindow.template;

public class CheckPermutationSubStr {

	static int MAX = 26;
	public static boolean checkInclusion(String s1, String s2) {

		int[] count1 = new int[MAX];
		int[] count2 = new int[MAX];

		int l1 = s1.length();
		int l2 = s2.length();
		for (int i = 0; i < l1; i++) {
			count1[s1.charAt(i) - 'a']++;
			count2[s2.charAt(i) - 'a']++;
		}

		for (int i = l1; i < l2; i++) {
			if (compare(count1, count2)) {
				return true;
			}

			count2[s2.charAt(i) - 'a']++;
			count2[s2.charAt(i - l1) - 'a']--;
		}

		if (compare(count1, count2)) {
			return true;
		}

		return false;
	}

	static boolean compare(int[] a, int[] b) {

		for (int i = 0; i < MAX; i++) {
			if (a[i] != b[i]) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		System.out.println(checkInclusion("hello", "ooolleoooleh"));
		System.out.println(checkInclusion("ab", "eidbaooo"));
		System.out.println(checkInclusion("ab", "eidboaoo"));
		System.out.println(checkInclusion("adc", "dcda"));
	}
}
