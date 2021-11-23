package com.math;

public class IntergerToEnglish
{
	
	final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	final String[] THOUSANDS = {"Billion", "Million", "Thousand", ""};
	final int[] radix = {1000000000, 1000000, 1000, 1};

	public String numberToWords(int num) {
	    if (num == 0) return "Zero";
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < radix.length; i++) {
	        if (num / radix[i] == 0) continue;
	        sb.append(trans(num / radix[i])).append(THOUSANDS[i]).append(' ');
	        num %= radix[i];
	    }
	    return sb.toString().trim();
	}

	private String trans(int num) {
	    if (num == 0) return "";
	    if (num < 20) return LESS_THAN_20[num] + " ";
	    if (num < 100) return TENS[num / 10] + " " + trans(num % 10);
	    return LESS_THAN_20[num / 100] + " Hundred " + trans(num % 100);
	}
	
	public static String ones(int fare)
	{
		switch (fare)
		{
		case 1:
			return "One";
		case 2:
			return "Two";
		case 3:
			return "Three";
		case 4:
			return "Four";
		case 5:
			return "Five";
		case 6:
			return "Six";
		case 7:
			return "Seven";
		case 8:
			return "Eight";
		case 9:
			return "Nine";
		}
		return "";
	}

	public static String teens(int fare)
	{
		switch (fare)
		{
		case 10:
			return "Ten";
		case 11:
			return "Eleven";
		case 12:
			return "Twelve";
		case 13:
			return "Thirteen";
		case 14:
			return "Fourteen";
		case 15:
			return "Fifteen";
		case 16:
			return "Sixteen";
		case 17:
			return "Seventeen";
		case 18:
			return "Eighteen";
		case 19:
			return "Nineteen";
		}
		return "";
	}

	public static String tens(int fare)
	{
		switch (fare)
		{
		case 2:
			return "Twenty";
		case 3:
			return "Thirty";
		case 4:
			return "Forty";
		case 5:
			return "Fifty";
		case 6:
			return "Sixty";
		case 7:
			return "Seventy";
		case 8:
			return "Eighty";
		case 9:
			return "Ninety";
		}
		return "";
	}

	public static String two(int fare)
	{
		if (fare == 0)
			return "";
		else if (fare < 10)
			return ones(fare);
		else if (fare < 20)
			return teens(fare);
		else
		{
			int tenner = fare / 10;
			int rest = fare - tenner * 10;
			if (rest != 0)
				return tens(tenner) + " " + ones(rest);
			else
				return tens(tenner);
		}
	}

	public static String three(int fare)
	{
		int hundred = fare / 100;
		int rest = fare - hundred * 100;
		String res = "";

		if (hundred * rest != 0)
			res = ones(hundred) + " Hundred " + two(rest);
		else if ((hundred == 0) && (rest != 0))
			res = two(rest);
		else if ((hundred != 0) && (rest == 0))
			res = ones(hundred) + " Hundred";

		return res;
	}

	public static String FareinWords(int fare)
	{
		if (fare == 0)
			return "Zero";

		int billion = fare / 1000000000;
		int million = (fare - billion * 1000000000) / 1000000;
		int thousand = (fare - billion * 1000000000 - million * 1000000) / 1000;
		int rest = fare - billion * 1000000000 - million * 1000000 - thousand * 1000;

		String result = "";

		if (billion != 0)
			result = three(billion) + " Billion";
		if (million != 0)
		{
			if (!result.isEmpty())
				result += " ";
			result += three(million) + " Million";
		}
		if (thousand != 0)
		{
			if (!result.isEmpty())
				result += " ";
			result += three(thousand) + " Thousand";
		}
		if (rest != 0)
		{
			if (!result.isEmpty())
				result += " ";
			result += three(rest);
		}

		return result;
	}

	public static void main(String args[])
	{
		// Driver code

		int fare = 5648;
		System.out.println("The fare is: " + FareinWords(fare) + " dollars");
	}

}
