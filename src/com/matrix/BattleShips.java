package com.matrix;

public class BattleShips {

	public static int countBattleships(char[][] board) {
		int m = board.length;
		if (m == 0)
			return 0;
		int n = board[0].length;

		int count = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == '.')
					continue;
				if (i > 0 && board[i - 1][j] == 'X')
					continue;
				if (j > 0 && board[i][j - 1] == 'X')
					continue;
				count++;
				System.out.println(i + "\t" + j);
			}
		}

		return count;
	}

	public static void main(String[] args) {
		
		char[][] board = {{'.','.','.','X'},{'X','X','X','X'},{'.','.','.','X'}};
		System.out.println(countBattleships(board ));

	}
}
