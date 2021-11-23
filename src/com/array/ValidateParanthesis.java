package com.array;

import java.util.Stack;

public class ValidateParanthesis {

	public static boolean checkValidString(String s) {
		int cmin = 0, cmax = 0; // open parentheses count in range [cmin, cmax]
		for (final char c : s.toCharArray()) {
			if (c == '(') {
				cmax++;
				cmin++;
			} else if (c == ')') {
				cmax--;
				cmin--;
			} else if (c == '*') {
				cmax++; // if `*` become `(` then openCount++
				cmin--; // if `*` become `)` then openCount--
				// if `*` become `` then nothing happens
				// So openCount will be in new range [cmin-1, cmax+1]
			}
			if (cmax < 0) {
				return false; // Currently, don't have enough open parentheses
								// to match close parentheses-> Invalid
			}
			// For example: (()))(
			cmin = Math.max(cmin, 0); // It's invalid if open parentheses count
										// < 0 that's why cmin can't be negative
		}
		return cmin == 0; // Return true if can found `openCount == 0` in range
							// [cmin, cmax]
	}


	// Complete the flatlandSpaceStations function below.
	static int flatlandSpaceStations(int n, int[] c) {

		final int dist[] = new int[n];

		final boolean seen[] = new boolean[n];

		int leftNearest = n + 1, rightNearest = -1;

		int result = 0;

		for (final int ci : c) {
			seen[ci] = true;
		}

		for (int i = 0; i < n; i++) {
			if (seen[i]) {
				leftNearest = i;
				dist[i] = 0;
			} else {
				if (i > leftNearest) {
					dist[i] = (i - leftNearest);
				} else {
					dist[i] = n + 1;//// Occurs when there is not leftNearest
									//// spaceStation yet
				}
			}
		}

		for (int i = n - 1; i > 0; i--) {
			if (seen[i]) {
				rightNearest = i;
			} else {
				if (rightNearest - i > 0) {
					dist[i] = Math.min(dist[i], rightNearest - i);
				}
			}
			result = Math.max(result, dist[i]);
		}

		return result;

	}

	public static void main(String[] args) {

		System.out.println(flatlandSpaceStations(5, new int[] { 0, 4 }));

		System.out.println(flatlandSpaceStations(5, new int[] { 4 }));


		System.out.println(flatlandSpaceStations(6, new int[] { 0, 1, 2, 4, 3, 5 }));
		// System.out.println(checkValidString("()"));
		// System.out.println(checkValidString("(*)"));
		// System.out.println(checkValidString("(*))"));
		 System.out.println(checkValidString("(*))()"));
		System.out.println(checkValidStringUsingStacks("(*))(()"));
		System.out.println(checkValidStringUsingStacks("(*))(()*"));
	}

	public static boolean checkValidStringUsingStacks(String s) {
		final Stack<Integer> leftID = new Stack<>();
		final Stack<Integer> starID = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			final char ch = s.charAt(i);
			if (ch == '(') {
				leftID.push(i);
			} else if (ch == '*') {
				starID.push(i);
			} else {
				if (leftID.isEmpty() && starID.isEmpty()) {
					return false;
				}
				if (!leftID.isEmpty()) {
					leftID.pop();
				} else {
					starID.pop();
				}
			}
		}
		while (!leftID.isEmpty() && !starID.isEmpty()) {
			if (leftID.pop() > starID.pop()) {
				return false;
			}
		}
		return leftID.isEmpty();
	}

}
