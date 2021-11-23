package com.backtracking;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
	public List<List<String>> partition(String s) {
		List<List<String>> res = new ArrayList<>();
		backtrack(new ArrayList<>(), res, s, 0);
		return res;
	}
	
	public List<List<String>> partition1(String s) {
		List<List<String>> res = new ArrayList<>();
		backtrack1(res,new ArrayList<>(), s, 0);
		return res;
	}

	public void backtrack(List<String> partialSolution, List<List<String>> allSolutions, String s, int start) {
		if (start == s.length()) {
			allSolutions.add(new ArrayList<>(partialSolution));
			return;
		}

		// candidates would be all indices such that s[start...candidate] is a
		// palindrome
		for (int candidate = start; candidate < s.length(); candidate++) { // candidates
			if (isPalindrome(s, start, candidate)) { // proceed only if
														// s[start...firstPalindromeSubstringEndIndex]
														// is a palindrome
				partialSolution.add(s.substring(start, candidate + 1)); // makeMove
				backtrack(partialSolution, allSolutions, s, candidate + 1); // recursively
																			// call
																			// backtrack(
																			// ...
																			// )
				partialSolution.remove(partialSolution.size() - 1); // undoMove
			}
		}
	}

	public boolean isPalindrome(String s, int low, int high) {
		while (low < high) {
			if (s.charAt(low++) != s.charAt(high--)) {
				return false;
			}
		}
		return true;
	}
	
	public void backtrack1(List<List<String>> list, List<String> tempList, String s, int start) {
		if (start == s.length())
			list.add(new ArrayList<>(tempList));
		else {
			for (int i = start; i < s.length(); i++) {
				if (isPalindrome(s, start, i)) {
					tempList.add(s.substring(start, i + 1));
					backtrack1(list, tempList, s, i + 1);
					tempList.remove(tempList.size() - 1);
				}
			}
		}
	}

	
	public static void main(String[] args) {
		PalindromePartitioning p = new PalindromePartitioning();
		
		System.out.println(p.partition("aab"));
		System.out.println(p.partition1("aab"));
	}
}
