package com.merge.intervals;

import java.util.ArrayList;

public class IntersectIntervals {
	public int[][] intervalIntersection(int[][] A, int[][] B) {
		int l1 = A.length;
		int l2 = B.length;
		if (l1 == 0 || l2 == 0) {
			return new int[0][0];
		}

		int i = 0, j = 0;

		ArrayList<int[]> result = new ArrayList<int[]>();
		while (i < l1 && j < l2) {
			int[] a = A[i];
			int[] b = B[j];

			if (a[0] <= b[1] && a[1] >= b[0]) {
				result.add(new int[] { Math.max(a[0], b[0]), Math.min(a[1], b[1]) });
			}

			if (a[1] <= b[1]) {
				i++;
			} else {
				j++;
			}
		}

		int[][] rArray = new int[result.size()][2];

		return result.toArray(rArray);

	}
}
