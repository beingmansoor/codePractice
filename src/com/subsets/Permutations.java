package com.subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Permutations {
	
	public static List<List<Character>> findPerms(String str)
	{
		List<List<Character>> result = new ArrayList<List<Character>>();
		
		char[] input = str.toCharArray();
		
		Arrays.sort(input);
		
		Queue<List<Character>> queue = new LinkedList<List<Character>>();

		queue.add(new ArrayList<>());
		
		boolean[] used = new boolean[input.length];
		
		for(int i=0; i<input.length;i++)
		{
			if(used[i] || (i>0 && input[i-1]==input[i] && !used[i-1]))
			{
				continue;
			}
			else
			{
				used[i] = true;
			}
			int queuesize = queue.size();
			for(int j=0;j<queuesize;j++)
			{
				
				List<Character> temp = queue.poll();
				int positions = temp.size();
				for(int k=0; k<=positions;k++)
				{
					ArrayList<Character> next = new ArrayList<Character>(temp);
					next.add(k,input[i]);
					if(next.size() == input.length)
					{
						result.add(next);
					}
					else
					{
						queue.add(next);
					}
				}
			}
		}
		
		return result;
	}

	public static List<List<Integer>> findPermutations(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		Queue<List<Integer>> permutations = new LinkedList<>();
		permutations.add(new ArrayList<>());
		for (int currentNumber : nums) {
			// we will take all existing permutations and add the current number
			// to create new permutations
			int n = permutations.size();
			for (int i = 0; i < n; i++) {
				List<Integer> oldPermutation = permutations.poll();
				// create a new permutation by adding the current number at
				// every position
				for (int j = 0; j <= oldPermutation.size(); j++) {
					List<Integer> newPermutation = new ArrayList<Integer>(oldPermutation);
					newPermutation.add(j, currentNumber);
					if (newPermutation.size() == nums.length)
						result.add(newPermutation);
					else
						permutations.add(newPermutation);
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		
		List<List<Character>> findPerms = findPerms("aaa");
		findPerms.stream().forEach( var -> System.out.println(var));
		List<List<Integer>> result = Permutations.findPermutations(new int[] { 1, 3, 5 });
		System.out.print("Here are all the permutations: " + result);
	}

	/*
	 * Time complexity # We know that there are a total of N! permutations of a
	 * set with ‘N’ numbers. In the algorithm above, we are iterating through
	 * all of these permutations with the help of the two ‘for’ loops. In each
	 * iteration, we go through all the current permutations to insert a new
	 * number in them on line 17 (line 23 for C++ solution). To insert a number
	 * into a permutation of size ‘N’ will take O(N), which makes the overall
	 * time complexity of our algorithm O(N*N!).
	 * 
	 * Space complexity # All the additional space used by our algorithm is for
	 * the result list and the queue to store the intermediate permutations. If
	 * you see closely, at any time, we don’t have more than N! permutations
	 * between the result list and the queue. Therefore the overall space
	 * complexity to store N! permutations each containing N elements will be
	 * O(N*N!).
	 */

	public static List<List<Integer>> generatePermutations(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		generatePermutationsRecursive(nums, 0, new ArrayList<Integer>(), result);
		return result;
	}

	private static void generatePermutationsRecursive(int[] nums, int index, List<Integer> currentPermutation,
			List<List<Integer>> result) {
		if (index == nums.length) {
			result.add(currentPermutation);
		} else {
			// create a new permutation by adding the current number at every
			// position
			for (int i = 0; i <= currentPermutation.size(); i++) {
				List<Integer> newPermutation = new ArrayList<Integer>(currentPermutation);
				newPermutation.add(i, nums[index]);
				generatePermutationsRecursive(nums, index + 1, newPermutation, result);
			}
		}
	}
}
