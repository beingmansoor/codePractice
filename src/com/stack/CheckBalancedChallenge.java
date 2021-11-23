package com.stack;

import com.queue.Stack;

/*
 * In this problem, you have to implement the isBalanced() function which will take a string containing only curly {}, square [], and round () parentheses. The function will tell us whether all the parentheses in the string are balanced or not.

For all the parentheses to be balanced, every opening parenthesis must have a closing one. The order in which they appear also matters. For example, {[]} is balanced, but {[}] is not.

Function Prototype: #
boolean isBalanced(String exp);
where exp is an expression containing a combination of parentheses.

Input #
A string consisting solely of (, ), {, }, [ and ]

Output #
It returns False if the expression doesn’t have balanced parentheses. If it does, the function returns True.

Sample Input #
exp = "{[({})]}"
Sample Output #
True
 */
public class CheckBalancedChallenge {
    public static boolean isBalanced(String exp) {

        //Iterate through the string exp.
        //For each opening parentheses, push it into stack
        //For every closing parentheses check for its opening parentheses in stack
        //If you can't find the opening parentheses for any closing one then returns false.
        //and after complete traversal of string exp, if there's any opening parentheses left
        //in stack then also return false.
        //At the end return true if you haven't encountered any of the above false conditions.
        Stack<Character> stack = new Stack<>(exp.length());

        for (int i = 0; i < exp.length(); i++) {

            char character = exp.charAt(i);

            if (character == '}' || character == ')' || character == ']') {

				if (stack.isEmpty())
					return false;

                if ((character == '}' && stack.pop() != '{') || 
                		(character == ')' && stack.pop() != '(') ||
                		(character == ']' && stack.pop() != '[')) 
                	return false;

            }
            else stack.push(character);

        } //end of for
        if (!stack.isEmpty()) return false;

        return true;
    }

    public static void main(String args[]) {

        System.out.println(isBalanced("{[()]}"));
        System.out.println(isBalanced("[{(}]"));

    }
}