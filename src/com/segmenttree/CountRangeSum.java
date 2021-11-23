package com.segmenttree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CountRangeSum {
	/*
	 * Given an integer array nums, return the number of range sums that lie in
	 * [lower, upper] inclusive. Range sum S(i, j) is defined as the sum of the
	 * elements in nums between indices i and j (i ≤ j), inclusive.
	 * 
	 * Note: A naive algorithm of O(n2) is trivial. You MUST do better than
	 * that.
	 * 
	 * Example:
	 * 
	 * Input: nums = [-2,5,-1], lower = -2, upper = 2, Output: 3 Explanation:
	 * The three ranges are : [0,0], [2,2], [0,2] and their respective sums are:
	 * -2, -1, 2.
	 */
	class SegmentTreeNode {
		SegmentTreeNode left;
		SegmentTreeNode right;
		int count;
		long min;
		long max;

		public SegmentTreeNode(long min, long max) {
			this.min = min;
			this.max = max;
		}
	}

	private SegmentTreeNode buildSegmentTree(Long[] valArr, int low, int high) {
		if (low > high)
			return null;
		SegmentTreeNode stn = new SegmentTreeNode(valArr[low], valArr[high]);
		if (low == high)
			return stn;
		int mid = (low + high) / 2;
		stn.left = buildSegmentTree(valArr, low, mid);
		stn.right = buildSegmentTree(valArr, mid + 1, high);
		return stn;
	}

	private void updateSegmentTree(SegmentTreeNode stn, Long val) {
		if (stn == null)
			return;
		if (val >= stn.min && val <= stn.max) {
			stn.count++;
			updateSegmentTree(stn.left, val);
			updateSegmentTree(stn.right, val);
		}
	}

	private int getCount(SegmentTreeNode stn, long min, long max) {
		if (stn == null)
			return 0;
		if (min > stn.max || max < stn.min)
			return 0;
		if (min <= stn.min && max >= stn.max)
			return stn.count;
		return getCount(stn.left, min, max) + getCount(stn.right, min, max);
	}
	
	public int countRangeSum_NaiveSol(int[] nums, int lower, int upper) {
	    int n = nums.length;
	    long[] sums = new long[n + 1];
	    for (int i = 0; i < n; ++i)
	        sums[i + 1] = sums[i] + nums[i];
	    int ans = 0;
	    for (int i = 0; i < n; ++i)
	        for (int j = i + 1; j <= n; ++j)
	            if (sums[j] - sums[i] >= lower && sums[j] - sums[i] <= upper)
	                ans++;
	    return ans;
	}

	public int countRangeSum(int[] nums, int lower, int upper) {

		if (nums == null || nums.length == 0)
			return 0;
		int ans = 0;
		Set<Long> valSet = new HashSet<Long>();
		long sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += (long) nums[i];
			valSet.add(sum);
		}

		Long[] valArr = valSet.toArray(new Long[0]);

		Arrays.sort(valArr);
		SegmentTreeNode root = buildSegmentTree(valArr, 0, valArr.length - 1);

		for (int i = nums.length - 1; i >= 0; i--) {
			updateSegmentTree(root, sum);
			sum -= (long) nums[i];
			ans += getCount(root, (long) lower + sum, (long) upper + sum);
		}
		return ans;
	}

	public int countRangeSum2(int[] nums, int lower, int upper) {
		if (nums == null || nums.length == 0)
			return 0;
		TreeMap<Long, Long> tr = new TreeMap<Long, Long>();
		tr.put((long) 0, (long) 1);
		long sum = 0;
		long count = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			long from = sum - upper;
			long to = sum - lower;
			Map<Long, Long> sub = tr.subMap(from, true, to, true);
			for (Long value : sub.values()) {
				count += value;
			}
			if (tr.containsKey(sum)) {
				tr.put(sum, tr.get(sum) + 1);
			} else {
				tr.put(sum, (long) 1);
			}
		}
		return (int) count;
	}

	public int countRangeSum1(int[] nums, int lower, int upper) {
		long[] accumulativeSum = new long[nums.length + 1];

		for (int i = 1; i < accumulativeSum.length; i++) {
			accumulativeSum[i] = accumulativeSum[i - 1] + nums[i - 1];
		}

		return countRangeSumUtil(accumulativeSum, lower, upper);
	}

	private int countRangeSumUtil(long[] nums, int lower, int upper) {
		int numRangeSum = 0;

		for (int j = 0; j < nums.length; j++) {
			for (int i = 1; i - 1 < j; i++) {
				if (nums[j] >= nums[i - 1] + lower && nums[j] <= nums[i - 1] + upper) {
					// S(i, j) is valid
					numRangeSum++;
				}
			}
		}

		return numRangeSum;
	}

	public static void main(String[] args) {
		int i = new CountRangeSum().countRangeSum2(new int[] { -2, 5, -1 }, -2, 2);
		System.out.println(i);
	}

}