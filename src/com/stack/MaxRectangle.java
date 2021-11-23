package com.stack;

import java.util.Stack;

public class MaxRectangle
{
	public static int maximumRowArea(int[] heights)
	{
		Stack<Integer> stack = new Stack<>();

		// Initialize stack with the default L value
		stack.push(-1);

		int maxArea = 0;
		for (int i = 0; i < heights.length; ++i)
		{

			while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
			{
				int height = heights[stack.pop()];
				int R = i;
				int L = stack.peek();
				int width = (R - L) - 1;
				int area = height * width;

				maxArea = Math.max(maxArea, area);
			}

			stack.push(i);

		}

		// Case when no valid R value exists anymore
		while (stack.peek() != -1)
		{
			int height = heights[stack.pop()];
			int R = heights.length;
			int L = stack.peek();
			int width = (R - L) - 1;
			int area = height * width;

			maxArea = Math.max(maxArea, area);
		}

		return maxArea;
	}

	public static int lowCoverage(String[][] matrix)
	{

		if (matrix.length == 0)
			return 0;
		int maxArea = 0;
		int[] dp = new int[matrix[0].length];

		for (int i = 0; i < matrix.length; i++)
		{
			for (int j = 0; j < matrix[0].length; j++)
			{

				// update the state of this row's dp array using the last row's
				// dp array
				// by keeping track of the number of consecutive ones
				if (matrix[i][j] == "1")
				{
					dp[j] = dp[j] + 1;
				}
				else
				{
					dp[j] = 0;
				}
			}
			// update maxArea with the maximum area from this row's dp array
			maxArea = Math.max(maxArea, maximumRowArea(dp));
		}
		return maxArea;
	}

	public static void main(String args[])
	{

		int[] heights = { 2, 1, 5, 6, 2, 3 };

		System.out.println("Maximum Row Area: " + maximumRowArea(heights));

		String[][] mall = { { "1", "0", "0", "1", "1", "1" }, { "1", "0", "1", "1", "0", "1" },
				{ "0", "1", "1", "1", "1", "1" }, { "0", "0", "1", "1", "1", "1" } };

		System.out.println("Maximum Low coverage area is " + lowCoverage(mall) + " units");
	}
}
