package com.stack;

import java.util.Arrays;
import java.util.Stack;

/*
 * Problem statement #
You are given a list logs, where logs[i] represents the ithith log message formatted as a string, {function_id}:{"start" | "end"}:{timestamp}. For example, 0:start:3 means a function call with a function of ID 0 starts at the beginning of timestamp 3, and 1:end:2 means a function call with a function of ID 1 ends at the end of timestamp 2.

Note: A function can be called multiple times, possibly recursively.

A function’s exclusive time is the sum of the execution times for all of the calls to that function anywhere in the program. For example, if a function is called twice, one call executes for 2 time units and the other executes for 1 time unit, the exclusive time is 2+1=32+1=3.

Input #
The input will be a variable with the total number of functions and a list of strings with each string containing information about a specific function. The following is an example input:

n = 2, logs = {"0:start:0", "1:start:3", "1:end:6", "0:end:10"}
Output #
The output will be a list of the exclusive time of each function. The following is an example output of the above input:

{7, 4}
In this example, the function with id 0 has an exclusive time of 7 stored at index 0, and the function with id 1 has an exclusive time of 4 stored at index 1.
 */
public class ExclusiveTimeofFunctions
{
	public static int[] serviceTime(int n, String[] logs)
	{
		Stack<Integer> stack = new Stack<Integer>();
		int[] servTimes = new int[n];
		String[] func = logs[0].split(":");
		stack.push(Integer.parseInt(func[0]));
		int time = Integer.parseInt(func[2]);
		for (int i = 0; i < logs.length; i++)
		{
			func = logs[i].split(":");
			if (func[1].indexOf("start") != -1)
			{
				if (stack != null)
					servTimes[stack.peek()] += Integer.parseInt(func[2]) - time;
				stack.push(Integer.parseInt(func[0]));
				time = Integer.parseInt(func[2]);
			}

			else
			{
				servTimes[stack.peek()] += Integer.parseInt(func[2]) - time + 1;
				stack.pop();
				time = Integer.parseInt(func[2]) + 1;
			}

		}

		return servTimes;
	}

	public static void main(String args[])
	{

		// Driver code
		String[] logs = { "0:start:0", "0:start:2", "0:end:5", "1:start:6", "1:end:6", "0:end:7" };
		System.out.println(Arrays.toString(serviceTime(2, logs)));
	}
}
