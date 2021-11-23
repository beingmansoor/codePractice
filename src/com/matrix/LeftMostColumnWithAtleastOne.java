package com.matrix;

import java.util.Arrays;
import java.util.List;

public class LeftMostColumnWithAtleastOne {
	public static int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {

		List<Integer> dimensions = binaryMatrix.dimensions();

		int rows = dimensions.get(0);
		int cols = dimensions.get(1);

		int i = 0, j = cols - 1;

		int result = -1;

		while (j >= 0 && i < rows) {
			int val = binaryMatrix.get(i, j);

			if (val == 0) {
				i++;
			} else {
				result = j;
				j--;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(leftMostColumnWithOne(new MyBinaryMatrix(new int[][] { { 0, 0 }, { 1, 1 } })));
		System.out.println(leftMostColumnWithOne(new MyBinaryMatrix(new int[][] { { 0, 0 }, { 0, 1 } })));

		System.out.println(leftMostColumnWithOne(new MyBinaryMatrix(new int[][] { { 0, 0 }, { 0, 0 } })));
		System.out.println(leftMostColumnWithOne(
				new MyBinaryMatrix(new int[][] { { 0, 0, 0, 1 }, { 0, 0, 1, 1 }, { 0, 1, 1, 1 } })));
	}
}

interface BinaryMatrix {
	public int get(int x, int y);

	public List<Integer> dimensions();
}

class MyBinaryMatrix implements BinaryMatrix {

	private final int[][] matrix;

	public MyBinaryMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	@Override
	public int get(int x, int y) {
		return matrix[x][y];
	}

	@Override
	public List<Integer> dimensions() {
		return Arrays.asList(matrix.length, matrix[0].length);
	}

}