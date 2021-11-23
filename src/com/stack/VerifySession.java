package com.stack;

import java.util.Stack;

/*
 * You are provided with two stack sequences, pushed and popped, with distinct values. 
 * Return true if and only if this could have been the result of a sequence of push and pop operations on an initially empty stack.

Input #
The input will be two arrays. The following is an example input:

pushed = {1,2,3,4,5}
popped = {1,2,3,4,5}
Output #
The output will be a Boolean representing whether these sequences of push and pop operations could have been interleaved and performed on a valid stack that was initially empty. The following is an example output:

true
The following operation would result in an empty stack:

push(1), pop(1), push(2), pop(2), push(3), pop(3), push(4), pop(4), push(5), pop(5)
 */
public class VerifySession
{
	 public static boolean verifySession(int[] pushOp, int[] popOp) {

	        int M = pushOp.length;
	        int N = popOp.length;
	        if (M != N)
	            return false;

	        Stack<Integer> stack = new Stack<Integer>();

	        int i = 0;
	        for (int pid: pushOp) {
	            stack.push(pid);
	            while (!stack.isEmpty() && stack.peek() == popOp[i]) {
	                stack.pop();
	                i++;
	            }
	        }

	        if (stack.isEmpty())
	            return true;
	        return false;
	    }

	    public static void main( String args[] ) {
	        // Driver code
	        int[] pushOp = {1,2,3,4,5};
	        int[] popOp = {5,4,3,2,1};

	        if (verifySession(pushOp, popOp) == true)
	            System.out.println( "Session Successfull!" );
	        else
	            System.out.println( "Session Faulty!" );
	    }
}
