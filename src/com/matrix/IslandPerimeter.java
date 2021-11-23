package com.matrix;

public class IslandPerimeter {
	public int islandPerimeter(int[][] grid) {
		int land = 0;
		int edge = 0;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1) {
					land++;
					if (i > 0 && grid[i - 1][j] == 1) {
						edge++;
					}
					if (j > 0 && grid[i][j - 1] == 1) {
						edge++;
					}
				}
			}
		}

		return land * 4 - edge * 2;
	}

	int numofneighbour(int mat[][], int i, int j)
	{ 
		int R = mat.length;
		int C = mat[0].length;
	    int count = 0; 
	  
	    // UP 
		if (i > 0 && mat[i - 1][j] == 1) {
			count++;
		} 
	  
	    // LEFT 
		if (j > 0 && mat[i][j - 1] == 1) {
			count++;
		} 
	  
	    // DOWN 
		if (i < R - 1 && mat[i + 1][j] == 1) {
			count++;
		} 
	  
	    // RIGHT 
		if (j < C - 1 && mat[i][j + 1] == 1) {
			count++;
		} 
	  
	    return count; 
	}

	// Returns sum of perimeter of shapes formed with 1s
	int findperimeter(int mat[][])
	{ 
		int R = mat.length;
		int C = mat[0].length;
	    int perimeter = 0; 
	  
	    // Traversing the matrix and finding ones to 
	    // calculate their contribution. 
	    for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (mat[i][j]==1) {
					perimeter += (4 - numofneighbour(mat, i ,j));
				}
			}
		} 
	  
	    return perimeter; 
	} 

}
