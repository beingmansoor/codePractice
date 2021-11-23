package com.prime;

import java.util.Arrays;
import java.util.Scanner;

public class PrimeNumber {
	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
		Scanner sc = new Scanner(System.in);
		String in = sc.nextLine();

		int n = Integer.parseInt(in);

		boolean[] primes = new boolean[n];
		Arrays.fill(primes, true);

		for (int i = 0; i < n; i++) {
			int j = Integer.parseInt(sc.nextLine());
			if (j <= 1) {
				primes[i] = false;
				continue;
			}

			for (int k = 2; k * k <= j; k++) {
				if (j % k == 0) {
					primes[i] = false;
					break;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			if (primes[i]) {
				System.out.println("Prime");
			} else {
				System.out.println("Not prime");
			}
		}
	}
}
