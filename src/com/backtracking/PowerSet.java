package com.backtracking;

import java.util.ArrayList;
import java.util.List;

public class PowerSet {

	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		makePowerSet(result, new ArrayList<Integer>(), new int[] { 1, 1,2, 3 }, 0);
		System.out.println(result);
		System.out.println(new PowerSet().subsets(new int[] { 1, 2, 3 }));
	}

	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		backtrack(nums, 0, new ArrayList<Integer>(), res);
		return res;
	}

	private void backtrack(int[] input, int index, List<Integer> partialSolution,
			List<List<Integer>> completeSolution) {
		if (index > input.length - 1) {
			processSolution(partialSolution, completeSolution);
			return;
		}
		if (isASolution(index)) {
			processSolution(partialSolution, completeSolution);
		}
		for (int i = index; i < input.length; i++) { // suitableCandidates when
														// index = i
			// are all the elements in input[] at index = i, (i + 1) ...
			// (input.length - 1)
			makeMove(input[i], partialSolution);
			backtrack(input, i + 1, partialSolution, completeSolution); // compute
																		// power
																		// set
																		// for
																		// input[(i
																		// +
																		// 1)...(n
																		// - 1)]
			undoMove(input[i], partialSolution); // once we have processed a
													// candidate, we remove it
		}
	}

	private boolean isASolution(int index) {
		return true; // always return true since
		// we are printing powerset
		// we print all the partial solutions.
		// In short, every partial solution is a solution for power set.
		//
		// For this particular problem we do not
		// need to do this check
		// but I am keeping this method to conform to our template.
	}

	private void processSolution(List<Integer> partialSolution, List<List<Integer>> completeSolution) {
		completeSolution.add(new ArrayList<Integer>(partialSolution));
	}

	private void makeMove(int candidate, List<Integer> partialSolution) {
		partialSolution.add(candidate);
	}

	private void undoMove(int candidate, List<Integer> partialSolution) {
		partialSolution.remove(Integer.valueOf(candidate));
		// partialSolution.remove(candidate) would remove
		// element at index candidate.
		// To remove candidate we need
		// partialSolution.remove(Integer.valueOf(candidate));

		/*
		 * The below implementation also works:
		 * 
		 * partialSolution.remove(partialSolution.remove(partialSolution.size()
		 * - 1)); // the candidate // we would need to remove would always be
		 * the last element // at the time of deletion. // // We won't need to
		 * pass candidate in this case.
		 */
	}

	private static void makePowerSet(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> tempList, int[] array,
			int index) {

		if (index > array.length - 1) {
			result.add(new ArrayList<>(tempList));
			return;
		}

		result.add(new ArrayList<>(tempList));

		for (int i = index; i < array.length; i++) {
			if (i > 0 && array[i] == array[i - 1]) { // handle duplicates
				continue;
			}
			tempList.add(array[i]);
			makePowerSet(result, tempList, array, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}

}
