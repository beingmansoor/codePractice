package com.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class NQueen {

	
	public List<List<String>> solveNQueen(int N) {
        List<List<String>> res = new ArrayList<>();
        List<List<Integer>> allSolutions = solveNQueensHelper(N);
        
        for (List<Integer> arr : allSolutions) {
            List<String> list = new ArrayList<>();    
            for (int i : arr) {
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < N; k++) {
                    if (k == i) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                list.add(sb.toString());
            }
            res.add(list);
        }
        return res;
    }
    
    private List<List<Integer>> solveNQueensHelper(int N) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<Integer>(), 0, N);
        return res;
    }
    
    private void backtrack(List<List<Integer>> completeSolutions, List<Integer> partialSolution, int row, int N) {
        if (row == N) {
            completeSolutions.add(new ArrayList<Integer>(partialSolution));
            return;
        }
        for (int col = 0; col < N; col++) { // candidate for each row -> all columns with no conflict
            if (isASuitableCandidate(partialSolution, row, col, N)) {
                partialSolution.add(row, col); // makeMove
                backtrack(completeSolutions, partialSolution, row + 1, N); // move to the next row
                partialSolution.remove(row); // undoMove
            }
        }
    }
    
    private boolean isASuitableCandidate(List<Integer> partialSolution, int row, int col, int N) {
        for (int rowIndex = 0; rowIndex < row; rowIndex++) { // Optimization: rowIndex < row and not rowIndex < N 
                                    // because we only need to check for the rows already processed.
                                    // We are processing rows in increasing order.
            int column = partialSolution.get(rowIndex);
            if (column == col // same column ??
               || Math.abs(column - col) == Math.abs(rowIndex - row)) { // diagonal 
                return false;
            }
        }
        return true;
        /*
        Notice that we are not checking for row, since in each partial solution
        we are placing only one queen in a row. So there is no conflict.
        */
    }
    
	void nQueen(int n) {
		int[][] m = new int[n][n];
		dfs(n, m, n);
	}

	public List<List<String>> solveNQueens(int n) {
		char[][] board = new char[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				board[i][j] = '.';
		List<List<String>> res = new ArrayList<List<String>>();
		dfs(board, 0, res);
		return res;
	}

	private void dfs(char[][] board, int colIndex, List<List<String>> res) {
		if (colIndex == board.length) {
			res.add(construct(board));
			return;
		}

		for (int i = 0; i < board.length; i++) {
			if (validate(board, i, colIndex)) {
				board[i][colIndex] = 'Q';
				dfs(board, colIndex + 1, res);
				board[i][colIndex] = '.';
			}
		}
	}

	private boolean validate(char[][] board, int x, int y) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < y; j++) {
				if (board[i][j] == 'Q' && (x + j == y + i || x + y == i + j || x == i))
					return false;
			}
		}

		return true;
	}

	private List<String> construct(char[][] board) {
		List<String> res = new LinkedList<String>();
		for (int i = 0; i < board.length; i++) {
			String s = new String(board[i]);
			res.add(s);
		}
		return res;
	}

	void dfs(int n, int[][] m, int q) {
		if (q == 0) {
			for (int i = 0; i < n; i++) {
				System.out.println(Arrays.toString(m[i]));
			}
			System.out.println("==============================");
			return;
		}

		int i = q - 1;
		for (int j = 0; j < n; j++) {
			if (safe(m, i, j, n)) {
				m[i][j] = 1;
				dfs(n, m, q - 1);
				m[i][j] = 0;
			}
		}
	}

	boolean safe(int[][] m, int x, int y, int n) {

		for (int i = x; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (m[i][j] == 1 && (x - y == i - j || x + y == i + j || y == j))
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		NQueen q = new NQueen();
		q.nQueen(4);

		List<List<String>> queens = q.solveNQueens(4);
		System.out.println(queens);
	}

}
