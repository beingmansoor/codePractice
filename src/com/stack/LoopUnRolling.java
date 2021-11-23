package com.stack;

import java.util.Stack;

public class LoopUnRolling
{
	public static String loopUnrolling(String codeBlock)
	{
		Stack<Integer> countStack = new Stack<>();
		Stack<StringBuilder> statementStack = new Stack<>();
		StringBuilder currentStatement = new StringBuilder();

		int n = 0;
		for (int i = 0; i < codeBlock.length(); i++)
		{
			char ch = codeBlock.charAt(i);
			if (Character.isDigit(ch))
			{
				n = n * 10 + ch - '0';
			}
			else if (ch == '[')
			{
				// push the number n to countStack
				countStack.push(n);
				statementStack.push(currentStatement);
				// reset currentStatement and n
				currentStatement = new StringBuilder();
				n = 0;

			}
			else if (ch == ']')
			{
				StringBuilder unrollStatement = statementStack.pop();
				// unroll currentN[currentStatement] by appending
				// currentStatement n times
				for (int currentN = countStack.pop(); currentN > 0; currentN--)
				{
					unrollStatement.append(currentStatement);
				}
				currentStatement = unrollStatement;

			}
			else
			{
				currentStatement.append(ch);
			}
		}
		return currentStatement.toString();
	}

	public static void main(String args[])
	{
		String codeBlock = "2[sum = sum + i; 2[i++; ]]";
		System.out.println(loopUnrolling(codeBlock));
	}
}
