package com.matrix;

import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadders
{
	private int n;

	/*
	 * Two important points to note -
	 * 
	 * 1. We are doing level order traversal in the BFS by finding out the size of
	 * the Q and traversing only that much. Had we stored the step in the queue
	 * as well then we usual BFS would have been sufficient.
	 * 
	 * 2. How do we calculate the position from the number:
	 * 
	 * private int[] numToPos(int target) {
	 * 
	 * // Visualise it as if converting 2D matrix to 1D. int row = (target - 1)
	 * / n, int col = (target - 1) % n;
	 * 
	 * // Since rows starting from the bottom. Therefore, Rth row will be n-1-R
	 * row from the bottom. int x = n - 1 - row;
	 * 
	 * // Now, we are looking at rows from bottom. Notice if the row number is
	 * even then it starts from left to right otherwise from right to left. int
	 * y = row % 2 == 0 ? col : n - 1 - col;
	 * 
	 * return new int[]{x, y}; }
	 */
	public int snakesAndLadders(int[][] board)
	{
		n = board.length;
		boolean[] visited = new boolean[n * n + 1];
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(1);
		visited[1] = true;
		int step = 1;
		while (!queue.isEmpty())
		{
			int size = queue.size();
			for (int k = 0; k < size; k++)
			{
				int cur = queue.poll();
				for (int i = 1; i <= 6; i++)
				{
					int next = cur + i;
					int[] pos = numToPos(next);
					if (board[pos[0]][pos[1]] > 0)
					{
						next = board[pos[0]][pos[1]];
					}
					if (next == n * n)
					{
						return step;
					}
					if (!visited[next])
					{
						queue.offer(next);
						visited[next] = true;
					}
				}
			}
			step++;
		}
		return -1;
	}

	private int[] numToPos(int target)
	{
		int row = (target - 1) / n, col = (target - 1) % n;
		int x = n - 1 - row, y = row % 2 == 0 ? col : n - 1 - col;
		return new int[] { x, y };
	}

	// private int posToNum(int[] position)
	// {
	// int row = (n - 1 - position[0]);
	// int y = row % 2 == 0 ? position[1] + 1 : n - position[1];
	// return row * n + y;
	// }
}
