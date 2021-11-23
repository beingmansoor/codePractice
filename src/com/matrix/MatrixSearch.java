package com.matrix;

public class MatrixSearch
{
	int search(int mat[][], int n, int x)
	{
		if (n == 0)
			return -1;

		int smallest = mat[0][0], largest = mat[n - 1][n - 1];
		if (x < smallest || x > largest)
			return -1;

		// set indexes for top right element
		int i = 0, j = n - 1;
		while (i < n && j >= 0)
		{
			if (mat[i][j] == x)
			{
				System.out.println("Found");
				return 1;
			}
			if (mat[i][j] > x)
				j--;
			// Check if mat[i][j] < x
			else
				i++;
		}

		System.out.println(" Element not found");
		return 0;
	}
}
