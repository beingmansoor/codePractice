package com.backtracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
	public List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<>();
		if (n < 1) {
			return res;
		}
		backtrack(new StringBuilder(), res, 0, 0, n);
		return res;
	}

	private void backtrack(StringBuilder partialSolution, List<String> solutions, int openingBraces, int closingBraces,
			int n) {
		if (partialSolution.length() == 2 * n) {
			solutions.add(partialSolution.toString());
			return;
		}
		List<String> candidates = generateCandidates(openingBraces, closingBraces, n);
		for (String brace : candidates) {
			partialSolution.append(brace); // makeMove
			backtrack(partialSolution, solutions, brace.equals("(") ? openingBraces + 1 : openingBraces,
					brace.equals(")") ? closingBraces + 1 : closingBraces, n);
			partialSolution.setLength(partialSolution.length() - 1); // undoMove
		}
	}

	private List<String> generateCandidates(int openingBraces, int closingBraces, int n) {
		List<String> candidates = new ArrayList<>();
		if (openingBraces < n) { // we always have room for appending one
									// more"("
			// at the to the partialSolution
			// anytime as long as partialSolution contains less than n opening
			// braces.
			// Keep in mind we cannit have more than n opening braces in our
			// solution.
			candidates.add("(");
		}
		if (closingBraces < openingBraces) { // we can append ")" to our partial
												// solution
			// only when we have less number of closing braces than opening
			// braces in our current partial solution
			candidates.add(")");
		}
		return candidates;
	}
}
