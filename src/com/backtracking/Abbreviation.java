package com.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Abbreviation {

	public static void abbr(String s, String asf, int count, int pos, List<String> result) {
		if (pos == s.length()) {
			if (count != 0) {
				asf += count;
			}
			result.add(asf);

			return;
		}

		abbr(s, asf, count + 1, pos + 1, result);

		if (count != 0)
			asf += count;
		abbr(s, asf + s.charAt(pos), 0, pos + 1, result);

	}

	public static void main(String[] args) {
		List<String> abbr = new ArrayList<String>();
		abbr("BAT", "", 0, 0, abbr);
		System.out.println(abbr);
	}

}
