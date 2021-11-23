package com.array;

public class StraightLine {
	public static boolean checkStraightLine(int[][] coordinates) {

		int[] p0 = coordinates[0];
		int[] p1 = coordinates[1];

		double dr = (p1[1] - p0[1]);
		double nr = (p1[0] - p0[0]);

		double slope = dr / nr;
		System.out.println(slope);

		for (int i = 2; i < coordinates.length; i++) {
			int[] p = coordinates[i];
			double nri = (p[1] - p0[1]);
			double dri = (p[0] - p0[0]);
			double s = nri / dri;

			System.out.println(s);
			if (slope != s) {
				return false;
			}
		}

		return true;

	}

	public static void main(String[] args) {
		System.out
				.println(checkStraightLine(new int[][] { { 1, 1 }, { 2, 2 }, { 3, 3 }, { 4, 4 }, { 5, 5 }, { 7, 7 } }));
	}
}
