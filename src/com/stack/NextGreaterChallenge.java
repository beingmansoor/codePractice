package com.stack;

import java.util.Arrays;

import com.queue.Stack;

/*
 * Problem Statement #
In this problem, you have to implement int[] nextGreaterElement(int[] arr). 
For each element in an array, it finds the next greater element in that list.

Note: The next greater element is the first element towards right which is greater than the element.
For example, in the array [1, 3, 8, 4, 10, 5], the next greater element to 3 is 8 and 
the next greater element for 8 is 10.

To keep it simple, the next greater element for the last or maximum value in the array is -1.

In each iteration, we only check the array elements appearing after the current node.

Function Prototype #
int[] nextGreaterElement(int[] arr);
where arr is an Integer array

Input #
The input is an integer array

Output #
An array containing the next greater element of each element from the input array. 
For the maximum value in the array, the next greater value is -1.

Sample Input #
arr = {4,6,3,2,8,1}
Sample Output #
result = {6,8,8,8,-1,-1}
 */
public class NextGreaterChallenge {
	public static int[] nextGreaterElement(int[] arr) {
		int[] result = new int[arr.length];
		Stack<Integer> stack = new Stack<>(arr.length);
		// iterate for rest of the elements
		for (int i = arr.length - 1; i >= 0; i--) {
			if (!stack.isEmpty()) {
				while (!stack.isEmpty() && stack.top() <= arr[i]) {
					stack.pop();
				}
			}
			if (stack.isEmpty()) {
				result[i] = -1;
			} else
				result[i] = stack.top();
			stack.push(arr[i]);
		}
		return result;
	}

	public static void main(String[] args) {
		int arr[] = { 4, 6, 3, 2, 8, 1, 11 };
		System.out.println(Arrays.toString(arr));
		int result[] = nextGreaterElement(arr);
		System.out.println(Arrays.toString(result));
	}
}