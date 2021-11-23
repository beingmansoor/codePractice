package com.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearch {
	public boolean exist(char[][] board, String word) {
		if (board == null || word == null || board.length == 0 || word.isEmpty()) {
			return false;
		}
		char firstCharacter = word.charAt(0);
		Set<String> visited = new HashSet<>(); // we cannot visit same cell
												// twice. example: in "BAB" the
												// two B's need to be two
												// different cells.
		// we are taking set and not list because searching in set is O(1)
		// amortized. In list searching is linear.
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == firstCharacter) {
					if (backtrack(0, word.toCharArray(), i, j, board, visited)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean backtrack(int characterIndex, char[] word, int row, int col, char[][] board, Set<String> visited) {
		if (characterIndex == word.length - 1 && word[characterIndex] == board[row][col]) {
			return true;
		}
		if (board[row][col] != word[characterIndex]) {
			return false;
		}
		List<List<Integer>> suitableCandidates = computeAdjacentCells(row, col, board, visited);
		for (List<Integer> candidate : suitableCandidates) {
			visited.add(row + ", " + col);
			if (backtrack(characterIndex + 1, word, candidate.get(0), candidate.get(1), board, visited)) {
				return true;
			}
			visited.remove(row + ", " + col);
		}
		return false;
	}

	private List<List<Integer>> computeAdjacentCells(int row, int col, char[][] board, Set<String> visited) {
		// This is a great technique which you can reuse in any grid (or board)
		// problem wherever you would have vertical, horizontal or even diagonal
		// movement.
		// Make sure you undertand this technique and add this to your
		// algorithms, data structures and coding toolbox.
		int[][] directions = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

		List<List<Integer>> candidates = new ArrayList<>();
		for (int[] direction : directions) {
			int nextRow = row + direction[0];
			int nextCol = col + direction[1];
			if (nextRow < 0 || nextRow == board.length || nextCol < 0 || nextCol == board[0].length
					|| visited.contains(nextRow + ", " + nextCol)) {
				continue;
			}
			List<Integer> candidate = new ArrayList<>();
			candidate.add(nextRow);
			candidate.add(nextCol);
			candidates.add(candidate);
		}
		return candidates;
	}

}
