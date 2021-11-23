package com.dp.cuts;
/*
Problem Statement:
Given a sorted array keys[0.. n-1] of search keys and an array freq[0.. n-1] of frequency counts, where freq[i] is the number of searches to keys[i]. Construct a binary search tree of all keys such that the total cost of all the searches is as small as possible. Let us first define the cost of a BST. The cost of a BST node is level of that node multiplied by its frequency. Level of root is 1.
Examples:
Input: keys[] = {10, 12}, freq[] = {34, 50}
There can be following two possible BSTs
        10                       12
          \                     /
           12                 10
          I                     II

Frequency of searches of 10 and 12 are 34 and 50 respectively.
The cost of tree I is 34*1 + 50*2 = 134
The cost of tree II is 50*1 + 34*2 = 118

Input: keys[] = {10, 12, 20}, freq[] = {34, 8, 50}
There can be following possible BSTs
    10                12                 20         10              20
      \             /    \              /             \            /
      12          10     20           12               20         10
        \                            /                 /           \
         20                        10                12             12
     I               II             III             IV             V

Among all possible BSTs, cost of the fifth BST is minimum.
Cost of the fifth BST is 1*50 + 2*34 + 3*8 = 142

Solution:
The optimal cost for freq[i..j] can be recursively calculated using following formula.



We need to calculate optCost(0, n-1) to find the result.
The idea of above formula is simple, we one by one try all nodes as root (r varies from i to j in second term). When we make rth node as root, we recursively calculate optimal cost from i to r-1 and r+1 to j. We add sum of frequencies from i to j (see first term in the above formula), this is added because every search will go through root and one comparison will be done for every search.

If you have already gone through Dynamic Programming:All possible Cuts in all possible Intervals for 
the Last Operation and Matrix Chain Multiplication then you might have already figured out that this 
problem follow the same pattern. I would directly jump on the code because there is nothing new to 
introduce here. The explanation would be same as that in Matrix Chain Multiplication . Just by looking 
at the problem statement the first thought that naturally comes to mind is we would have to compute 
result by making each of the given nodes a root and return the one that gives the minimum result. 
Once you can think of this, it naturally becomes a perfect fit for Dynamic Programming:
 All possible Cuts in all possible Intervals for the Last Operation concept. 
 Here the last operation is the picking root for the whole tree, because by then you have already picked
 the root for the subtrees (sub-problems).
 */
public class OptimalBST { 
	
	public int optimalSearchTree(int keys[], int freq[], int n) { 

		int cost[][] = new int[n + 1][n + 1]; 

		// For a binary tree with just one node, cost is equal to frequency of the key 
		for (int i = 0; i < n; i++)  {
			cost[i][i] = freq[i];
		}

		// Bottom-up approach: compute results of the subproblems first,
	    // i.e, subproblems with length 2, 3, .... all the way upto n
	    // where n = length of the given problem
		for (int L = 2; L <= n; L++) { 

			for (int beg = 0; beg <= n - L + 1; beg++) {

				int end = beg + L - 1;  // [beg, end]
				cost[beg][end] = Integer.MAX_VALUE; 

				// Try making all keys in interval keys[i..j] as root.
	            // Making cuts at all possible places
				for (int root = beg; root <= end; root++) {
	
					int c = ((root > beg) ? cost[beg][root - 1] : 0) 
							+ ((root < end) ? cost[root + 1][end] : 0)
	                        + sum(freq, beg, end);

					if (c < cost[beg][end]) 
						cost[beg][end] = c; 
				} 
			} 
		} 
		return cost[0][n - 1]; 
	} 
 
	private int sum(int freq[], int i, int j) { 
		int sum = 0; 
		for (int k = i; k <= j; k++) { 
			if (k >= freq.length) {
				continue;
			}
			sum += freq[k]; 
		} 
		return sum; 
	} 


} 
