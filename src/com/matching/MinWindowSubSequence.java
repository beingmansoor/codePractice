package com.matching;

public class MinWindowSubSequence
{
	public static String match(String cheater, String student)
	{
		String window = "";
		int j = 0, min = cheater.length() + 1;
		for (int i = 0; i < cheater.length(); i++)
		{
			if (cheater.charAt(i) == student.charAt(j))
			{
				j++;
				if (j == student.length())
				{
					int end = i + 1;
					j--;
					while (j >= 0)
					{
						if (cheater.charAt(i) == student.charAt(j))
							j--;
						i--;
					}
					j++;
					i++;
					if (end - i < min)
					{
						min = end - i;
						window = cheater.substring(i, end);
					}
				}
			}
		}
		return window;
	}

	public static void main(String[] args)
	{
		// Driver code

		String cheater = "quiqutit";
		String student = "quit";

		System.out.print("Copied Content: " + match(cheater, student));

	}
}
