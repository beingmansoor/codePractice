package com.tree.bfs;

import java.util.LinkedList;

public class NumberOfIslands {

	public static void main(String[] args) {
		final NumberOfIslands i = new NumberOfIslands();

		char[][] grid = { { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' }, { '0', '0', '1', '0', '0' },
				{ '0', '0', '0', '1', '1' } };
		System.out.println(i.numIslands(grid));

		grid = new char[][] { { '1', '1', '1', '1', '0' }, { '1', '1', '0', '1', '0' }, { '1', '1', '0', '0', '0' },
				{ '0', '0', '0', '0', '0' } };
		System.out.println(i.numIslands(grid));
	}

	private void bfsFill(char[][] grid,int x, int y){
	    grid[x][y]='0';
	    final int n = grid.length;
	    final int m = grid[0].length;
	    final LinkedList<Integer> queue = new LinkedList<Integer>();  
	    int code = x*m+y;  
	    queue.offer(code);  
	    while(!queue.isEmpty())  
	    {  
	        code = queue.poll();  
	        final int i = code/m;  
	        final int j = code%m;  
	        if(i>0 && grid[i-1][j]=='1')    //search upward and mark adjacent '1's as '0'.
	        {  
	            queue.offer((i-1)*m+j);  
	            grid[i-1][j]='0';  
	        }  
	        if(i<n-1 && grid[i+1][j]=='1')  //down
	        {  
	            queue.offer((i+1)*m+j);  
	            grid[i+1][j]='0';  
	        }  
	        if(j>0 && grid[i][j-1]=='1')  //left
	        {  
	            queue.offer(i*m+j-1);  
	            grid[i][j-1]='0';  
	        }  
	        if(j<m-1 && grid[i][j+1]=='1')  //right
	        {  
	            queue.offer(i*m+j+1);  
	            grid[i][j+1]='0';  
	        }
	    } 
	}

	private void dfs(char[][] grid, int i, int j) {

		if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') {
			return;
		}

		grid[i][j] = '0';

		dfs(grid, i, j + 1);
		dfs(grid, i + 1, j);

		dfs(grid, i - 1, j);
		dfs(grid, i, j - 1);

	}

	public int numIslands(char[][] grid) {

		int result = 0;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if ('1' == grid[i][j]) {
					dfs(grid, i, j);
					result++;
				}
			}
		}

		return result;
	}

	public int numIslandsUsingBFS(char[][] grid) {
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1') {
					bfsFill(grid, i, j);
					count++;
				}
			}
		}
		return count;
	}

}
