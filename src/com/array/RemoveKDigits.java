package com.array;

import java.util.Stack;

public class RemoveKDigits {


	public String removeKdigits(String num, int k) {

		int n = num.length();

		if (n == k) {
			return "0";
		}

		char[] digits = num.toCharArray();
		Stack<Integer> s = new Stack<Integer>();

		for (int i = 0; i < n; i++) {
			int d = digits[i] - '0';
			while (!s.isEmpty() && k > 0 && s.peek() > d) {
				s.pop();
				k--;
			}
			if (!s.isEmpty() || d != 0) {
				s.push(d);
			}
		}

		System.out.println(s);

		while (!s.empty() && k-- > 0) {
			s.pop();
		}

		if (s.isEmpty()) {
			return "0";
		}
		StringBuilder sb = new StringBuilder();
		while (!s.isEmpty()) {
			sb.append(s.pop());
		}

		return sb.reverse().toString();

	}

	public static void main(String[] args) {
		RemoveKDigits k = new RemoveKDigits();

		System.out.println(k.removeKdigits("1234567890", 9));

		System.out.println("======================");

		System.out.println(k.removeKdigits("1432219", 3));

		System.out.println(k.removeKdigits("12345", 3));
		System.out.println(k.removeKdigits("10200", 1));

		System.out.println(k.removeKdigits("100", 1));

		System.out.println(k.removeKdigits("12", 1));
	}
}
