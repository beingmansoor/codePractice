package com.array;

/*
 * 
 * Explanation

Let's say nums looks like this: [12, 13, 14, 15, 16, 17, 18, 19, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]

Because it's not fully sorted, we can't do normal binary search. But here comes the trick:

If target is let's say 14, then we adjust nums to this, where "inf" means infinity:
[12, 13, 14, 15, 16, 17, 18, 19, inf, inf, inf, inf, inf, inf, inf, inf, inf, inf, inf, inf]

If target is let's say 7, then we adjust nums to this:
[-inf, -inf, -inf, -inf, -inf, -inf, -inf, -inf, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]

And then we can simply do ordinary binary search.

Of course we don't actually adjust the whole array but instead adjust only on the fly only the elements we look at. 
And the adjustment is done by comparing both the target and the actual element against nums[0].


 * 
 */
public class SearchInRoatedSortedArray {

	private static int binarySearch(int[] nums, int low, int high, int target) {

		while (low <= high) {
			final int mid = high - ((high - low) / 2);

			if (mid >= nums.length) {
				return -1;
			}

			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] < target) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}

		}
		return -1;
	}

	private static int getPartitionKey(int[] nums) {
		int low = 0, high = nums.length - 1;
		int partitionKey = -1;

		while (low <= high) {
			final int mid = high - ((high - low) / 2);

			if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
				partitionKey = mid;
				break;
			} else if (nums[mid] < nums[mid + 1]) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}

		}
		return partitionKey;
	}

	public static void main(String[] args) {

		System.out.println(search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 0));

		System.out.println(search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 3));

	}

	public static int search(int[] nums, int target) {

		if (nums == null || nums.length == 0) {
			return -1;
		}

		int low = 0, high = nums.length - 1;

		while (low <= high) {
			final int mid = high - ((high - low) / 2);

			int num = -1;
			// If nums[mid] and target are "on the same side" of nums[0], we
			// just take nums[mid]. Otherwise we use -infinity or +infinity as
			// needed.

			if (nums[mid] < nums[0] && target < nums[0]) {
				num = nums[mid];
			} else if (target < nums[0]) {
				num = Integer.MIN_VALUE;
			} else {
				num = Integer.MAX_VALUE;
			}

			if (num < target) {
				low = mid + 1;
			} else if (num > target) {
				high = mid - 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

	public static int searchMine(int[] nums, int target) {

		final int partitionKey = getPartitionKey(nums);

		if (nums[0] < target) {
			return binarySearch(nums, 0, partitionKey + 1, target);
		} else {
			return binarySearch(nums, partitionKey + 1, nums.length, target);
		}

	}

	/*
	 * If the entire left part is monotonically increasing, which means the
	 * pivot point is on the right part If left <= target < mid ------> drop the
	 * right half Else ------> drop the left half
	 * 
	 * If the entire right part is monotonically increasing, which means the
	 * pivot point is on the left part If mid < target <= right ------> drop the
	 * left half Else ------> drop the right half
	 * 
	 */
	public int search2(int[] nums, int target) {
	    if (nums == null || nums.length == 0) {
	        return -1;
	    }
	    
	    /*.*/
	    int left = 0, right = nums.length - 1;
	    //when we use the condition "left <= right", we do not need to determine if nums[left] == target
	    //in outside of loop, because the jumping condition is left > right, we will have the determination
	    //condition if(target == nums[mid]) inside of loop
	    while (left <= right) {
	        //left bias
	        final int mid = left + (right - left) / 2;
	        if (target == nums[mid]) {
	            return mid;
	        }
	        //if left part is monotonically increasing, or the pivot point is on the right part
	        if (nums[left] <= nums[mid]) {
	            //must use "<=" at here since we need to make sure target is in the left part,
	            //then safely drop the right part
	            if (nums[left] <= target && target < nums[mid]) {
	                right = mid - 1;
	            }
	            else {
	                //right bias
	                left = mid + 1;
	            }
	        }

	        //if right part is monotonically increasing, or the pivot point is on the left part
	        else {
	            //must use "<=" at here since we need to make sure target is in the right part,
	            //then safely drop the left part
	            if (nums[mid] < target && target <= nums[right]) {
	                left = mid + 1;
	            }
	            else {
	                right = mid - 1;
	            }
	        }
	    }
	    return -1;
	}
}
