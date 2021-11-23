package com.matrix;

public class MaximumRouters
{
	public static int maximumRouters(int[][] grid)
	{
		if (grid.length == 0)
			return 0;

		int res = 0;
		int[][] cache = new int[grid.length][grid[0].length];

		for (int i = 0; i < grid.length; i++)
		{
			for (int j = 0; j < grid[0].length; j++)
			{

				if (cache[i][j] == 0)
				{
					int prev_val = grid[i][j];
					dfs(grid, i, j, prev_val, cache);
					res = Math.max(cache[i][j], res);
				}
			}
		}
		return res;
	}

	public static int dfs(int[][] grid, int i, int j, int prev_val, int[][] cache)
	{

		if (i < 0 || j < 0 || i > grid.length - 1 || j > grid[0].length - 1)
			return 0;

		else if (prev_val > grid[i][j])
			return 0;

		else if (cache[i][j] != 0)
			return cache[i][j];

		// Up
		int path_up = dfs(grid, i - 1, j, grid[i][j], cache);
		// Down
		int path_down = dfs(grid, i + 1, j, grid[i][j], cache);
		// Left
		int path_left = dfs(grid, i, j - 1, grid[i][j], cache);
		// Right
		int path_right = dfs(grid, i, j + 1, grid[i][j], cache);

		int max1 = Math.max(path_up, path_down);
		int max2 = Math.max(path_left, path_right);

		cache[i][j] = 1 + Math.max(max1, max2);

		return cache[i][j];
	}

	public static void main(String args[])
	{
		// Driver code
		int[][] grid = { { 2, 9, 6 }, { 8, 4, 7 }, { 5, 3, 1 } };
		System.out.println("Maximum Routers are " + maximumRouters(grid));
	}
}
