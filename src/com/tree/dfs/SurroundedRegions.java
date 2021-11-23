package com.tree.dfs;

import java.util.Arrays;

public class SurroundedRegions {
	public void solve(char[][] board) {

		int r = board.length, c = board[0].length;
		boolean[][] visited = new boolean[r][c];
		for (int i = 0; i < r; i++) {
			dfs(board, visited, i, 0);
			dfs(board, visited, i, c - 1);
		}

		for (int j = 0; j < c; j++) {
			dfs(board, visited, 0, j);
			dfs(board, visited, r - 1, j);
		}

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (board[i][j] == '1') {
					board[i][j] = 'O';
				} else if (board[i][j] == 'O') {
					board[i][j] = 'X';
				}
			}
		}

	}

	private void dfs(char[][] board, boolean[][] visited, int i, int j) {

		if (!isValid(board, visited, i, j)) {
			return;
		}
		visited[i][j] = true;
		board[i][j] = '1';

		dfs(board, visited, i - 1, j);
		dfs(board, visited, i + 1, j);
		dfs(board, visited, i, j - 1);
		dfs(board, visited, i, j + 1);
	}

	private boolean isValid(char[][] board, boolean[][] visited, int i, int j) {

		if (i < 0 || i >= visited.length || j < 0 || j >= visited[0].length || visited[i][j] || board[i][j] == 'X') {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		SurroundedRegions s = new SurroundedRegions();
		char[][] board = new char[][] { { 'X', 'X', 'X', 'X' }, { 'X', 'X', 'O', '0' }, { 'X', 'O', 'O', 'X' },
				{ 'X', 'X', 'X', 'X' } };
		s.solve(board);

		System.out.println(Arrays.deepToString(board));
	}
}
