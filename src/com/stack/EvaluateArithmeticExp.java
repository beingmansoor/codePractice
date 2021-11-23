package com.stack;

import java.util.Stack;

public class EvaluateArithmeticExp
{
	public static int evaluateExpression(String expression)
	{
		int number = 0;
		int sign = 1;
		int output = 0;
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < expression.length(); i++)
		{
			char c = expression.charAt(i);
			if (Character.isDigit(c))
			{
				number = number * 10 + Character.getNumericValue(c); // for
																		// consecutive
																		// digits
																		// 98 =>
																		// 9x10
																		// + 8 =
																		// 98
			}
			else if (c == '-' || c == '+')
			{
				output += number * sign;
				if (c == '-')
					sign = -1;
				else
					sign = 1;
				number = 0;
			}
			else if (c == '(')
			{
				stack.push(output);
				stack.push(sign);
				output = 0;
				sign = 1;
			}
			else if (c == ')')
			{
				output += sign * number;
				output *= stack.pop();
				output += stack.pop();
				number = 0;
			}
		}
		return output + number * sign;
	}

	public static void main(String args[])
	{
		String expression = "{4 - (10 + 52) + 99}";
		System.out.println(evaluateExpression(expression));
	}
}
