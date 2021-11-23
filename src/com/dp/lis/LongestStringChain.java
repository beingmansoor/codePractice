package com.dp.lis;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/*
Problem Statement:

Given a list of words, each word consists of English lowercase letters.
Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1
 to make it equal to word2. For example, "abc" is a predecessor of "abac".
A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a 
predecessor of word_2, word_2 is a predecessor of word_3, and so on.
Return the longest possible length of a word chain with words chosen from the given list of words.

Example 1:
Input: words = ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: One of the longest word chain is "a","ba","bda","bdca".

Example 2:
Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]

Output: 5
 */
public class LongestStringChain
{
	public int longestStrChain(String[] words) {
        Arrays.sort(words, new Comparator<String>(){
            public int compare(String a, String b) {
                return a.length() - b.length();
            }
        });
        int len = words.length;
        int[] lis = new int[len];
        Arrays.fill(lis, 1);
        int max = 1;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (isPredecessor(words[j], words[i])) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
            max = Math.max(max, lis[i]);
        }
        return max;
    }
    
    private boolean isPredecessor(String a, String b) {
        if (b.length() - a.length() != 1) {
            return false;
        }
        return longestCommonSubsequence(a, b) == a.length();
    /*
     Another implementation could be:
    comparing the characters of string a and string b
    and if the characters doesn't match,then skip that character in the larger string,
    but if we encounter such dissimilar characters more than 1 time we return false
    */
    }
        
        private int longestCommonSubsequence(String a, String b) {
            int[][] lcs = new int[a.length() + 1][b.length() + 1];
            for (int i = 1; i < a.length() + 1; i++) {
                for (int j = 1; j < b.length() + 1; j++) {
                    if (a.charAt(i - 1) == b.charAt(j - 1)) {
                        lcs[i][j] = lcs[i - 1][j - 1] + 1;
                    } else {
                        lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
                    }
                }
            }
            return lcs[a.length()][b.length()];
        }
        
        public int longestStrChain_2(String[] words) {
            Map<String, Integer> dp = new HashMap<>();
            Arrays.sort(words, (a, b)->a.length() - b.length());
            int res = 0;
            for (String word : words) {
                int best = 0;
                for (int i = 0; i < word.length(); ++i) {
                    String prev = word.substring(0, i) + word.substring(i + 1);
                    best = Math.max(best, dp.getOrDefault(prev, 0) + 1);
                }
                dp.put(word, best);
                res = Math.max(res, best);
            }
            return res;
        }
}
