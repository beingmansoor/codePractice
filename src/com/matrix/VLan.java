package com.matrix;

import java.util.Arrays;

public class VLan
{
	public static int[][] updateVLAN(int[][] matrix, int r, int c, int newID)
	{

		int currID = matrix[r][c];
		if (currID == newID)
			return matrix;

		dfs(matrix, r, c, currID, newID);
		return matrix;
	}

	public static void dfs(int[][] matrix, int r, int c, int currID, int newID)
	{
		// Check bounds of the matrix
		if (r < 0 || c < 0 || r >= matrix.length || c >= matrix[0].length || matrix[r][c] != currID)
		// check if ID has already been changed
		{
			return;
		}
		// Assign new ID
		matrix[r][c] = newID;

		// Up
		dfs(matrix, r - 1, c, currID, newID);
		// Down
		dfs(matrix, r + 1, c, currID, newID);
		// Left
		dfs(matrix, r, c - 1, currID, newID);
		// Right
		dfs(matrix, r, c + 1, currID, newID);
	}

	public static void main(String args[])
	{
		// Drive code

		int[][] matrix = { { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 0 }, { 1, 1, 1, 0, 0 }, { 1, 1, 0, 1, 0 },
				{ 1, 1, 0, 0, 1 } };
		int r = 1, c = 1, newID = 2;

		System.out.println("Swtches with Updated VLAN IDs:\n " + Arrays.deepToString(updateVLAN(matrix, r, c, newID)));
	}
}
