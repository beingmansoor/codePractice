package com.array;

import java.util.Arrays;

public class SortColors {
	public void sortColors1(int[] a) {
		int n = a.length;

		int z = 0, t = n - 1;

		for (int i = 0; i <= t; i++) {
			while (a[i] == 2 && i < t) {
				swap(a, i, t);
				t--;
			}
			while (a[i] == 0 && i > z) {
				swap(a, i, z);
				z++;
			}
		}

	}

	public void sortColors(int[] a) {
		int n = a.length;

		int z = 0, t = n - 1;

		for (int i = 0; i <= t; i++) {
			if (a[i] == 0) {
				swap(a, i, z);
				z++;
			}
			if (a[i] == 2) {
				swap(a, i, t);
				t--;
				i--;
			}
		}

	}

	void swap(int[] a, int i, int j) {
		if (i != j) {
			int t = a[i];
			a[i] = a[j];
			a[j] = t;
		}
	}

	public static void main(String[] args) {
		SortColors s = new SortColors();
		int a[] = new int[] { 2, 2, 0, 0, 2, 0, 2, 1, 0 };
		s.sortColors(a);
		System.out.println(Arrays.toString(a));
		a = new int[] { 1, 2, 0 };
		s.sortColors(a);
		System.out.println(Arrays.toString(a));
		a = new int[] { 2, 0 };
		s.sortColors(a);
		System.out.println(Arrays.toString(a));
	}
}
