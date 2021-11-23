package com.stack;

import com.queue.Stack;

/*
 * Infix and Postfix Expressions #
In the infix expression, the usual convention followed in mathematics, operators 
like + and * appear between the two numbers involved in the calculation:

6 + 3 * 8 - 4
Another convention is the postfix expression, where the operators appear after the two numbers 
involved in the expression. In postfix, the expression written above will be presented as:

6 3 8 * + 4 -
The two digits preceding an operator will be used with that operator.

From the first block of digits 6 3 8, we pick the last two which are 3 and 8.
Reading the operators from left to right, the first one is *. The expression now becomes 3 * 8
The next number is 6 while the next operator is +, so we have 6 + 8 * 3.
The value of this expression is followed by 4, which is right before -. Hence we have 6 + 8 * 3 - 4.
Problem Statement #
In this problem, you have to implement the evaluatePostFix() function that will compute a postfix expression given to it in a string.

Input #
The input is a string containing a postfix mathematic expression. Each digit is considered a separate number, i.e. there are no double-digit numbers.

Output #
An integer result of the given postfix expression.

Sample Input #
expression = "921*-8-4+" # 9 - 2 * 1 - 8 + 4
Sample Output #
3
Explanation #
1st operation => 2 * 1 = 2,
2nd operation => 9 - 2 = 7,
3rd operation => 7 - 8 = -1,
4th operation => -1 + 4 = 3
 */
public class EvaluatePostfixChallenge {
	public static int evaluatePostFix(String expression) {
		Stack<Integer> stack = new Stack<>(expression.length());
		// 1.Scan expression character by character,
		// 2.If character is a number push it in stack
		// 3.If character is operator then pop two elements from stack
		// perform the operation and put the result back in stack
		// At the end, Stack will contain result of whole expression.
		for (int i = 0; i < expression.length(); i++) {
			char character = expression.charAt(i);

			if (!Character.isDigit(character)) {
				Integer x = stack.pop();
				Integer y = stack.pop();

				switch (character) {
				case '+':
					stack.push(y + x);
					break;
				case '-':
					stack.push(y - x);
					break;
				case '*':
					stack.push(y * x);
					break;
				case '/':
					stack.push(y / x);
					break;
				}

			} else
				stack.push(Character.getNumericValue(character));
		}
		return stack.pop();
	}
}