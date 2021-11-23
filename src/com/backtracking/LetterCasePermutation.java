package com.backtracking;

import java.util.ArrayList;
import java.util.List;

public class LetterCasePermutation {
	public List<String> letterCasePermutation(String S) {
		List<String> res = new ArrayList<>();
		if (S == null || S.isEmpty()) {
			return res;
		}
		char[] word = S.toCharArray(); // random access in array is O(1)
		StringBuilder sb = new StringBuilder();
		backtrack(0, word, sb, res);
		return res;
	}

	private void backtrack(int characterIndex, char[] word, StringBuilder partialSolution,
			List<String> completeSolution) {
		if (characterIndex == word.length) {
			completeSolution.add(partialSolution.toString());
			return;
		}
		char[] candidates = constructCandidates(characterIndex, word);
		for (char candidate : candidates) {
			partialSolution.append(Character.toString(candidate)); // makeMove
			backtrack(characterIndex + 1, word, partialSolution, completeSolution);
			partialSolution.setLength(partialSolution.length() - 1); // undoMove
		}
	}

	private char[] constructCandidates(int index, char[] word) {
		// for alphabetic characters return 2 characters: the lower case and
		// upper case of the character.
		// for numbers just return the number because there is no lowercase
		// /upper case for numbers.
		return Character.isDigit(word[index]) ? new char[] { word[index] }
				: new char[] { Character.toLowerCase(word[index]), Character.toUpperCase(word[index]) };
	}
}
