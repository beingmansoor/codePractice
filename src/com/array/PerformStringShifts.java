package com.array;

public class PerformStringShifts {

	public static void main(String[] args) {
		final PerformStringShifts p = new PerformStringShifts();
		
		System.out.println(p.stringShift("abc", new int[][]{{0,1},{1,2}}));
		System.out.println(p.stringShift("abcdefg", new int[][]{{1,1},{1,1},{0,2},{1,3}}));
	}

	private void reverse(char[] input, int start, int end) {

		while (start < end) {
			final char t = input[start];
			input[start] = input[end];
			input[end] = t;
			start++;
			end--;
		}

	}
	public String stringShift(String s, int[][] shift) {
		final int length = s.length();
		
		int val =0;
		
		for(int i=0; i < shift.length; i++)
		{
			val += (shift[i][0] ==0 ? -shift[i][1] : shift[i][1]);
		}
		
		val %= length;
		
		String result ="";
		if(val <0)
		{
			result += s.substring(-val, length + val) + s.substring(0, -val);
		}
		else
		{
			result += s.substring(length - val, length) + s.substring(0, length-val); 
		}
		return result;
	}

	public String stringShift_mine(String s, int[][] shift) {

		int leftShifts = 0;
		
		final char[] input = s.toCharArray();
		
		final int length = input.length;
		for(final int[] sh : shift)
		{
			if(sh[0] == 0) {
				leftShifts += (sh[1] % length);
			} else {
				leftShifts -= (sh[1] % length);
			}
		}
		
		if(leftShifts >0)
		{
			reverse(input, 0, leftShifts);
			reverse(input, leftShifts+1, input.length-1);
			reverse(input, 0, input.length-1);
		}
		else
		{
			reverse(input, 0, input.length + leftShifts - 1);
			reverse(input, input.length + leftShifts, input.length - 1);
			reverse(input, 0, input.length-1);
		}
		
		return new String(input);
	}
}
