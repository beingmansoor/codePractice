package com.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * iven a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 
 * Example 1:
Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

Example 2:
Input: digits = ""
Output: []

Example 3:
Input: digits = "2"
Output: ["a","b","c"]

 */
public class T9KeyBoard {

	public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return res;
        }
        Map<String, List<String>> mapping = createMapping();
        backtrack(0, digits.toCharArray(), new StringBuilder(), res, mapping);
        return res;
    }
    
    private void backtrack(int index, char[] digits, StringBuilder partialSolution, List<String> solutions, Map<String, List<String>> mapping) {
        if (partialSolution.length() == digits.length) {
            solutions.add(partialSolution.toString());
            return;
        }
        List<String> candidates = mapping.get(Character.toString(digits[index]));
        for (String digit : candidates) {
            partialSolution.append(digit); // makeMove
            backtrack(index + 1, digits, partialSolution, solutions, mapping); // recursively call backtrack( ... ) for next index
            partialSolution.setLength(partialSolution.length() - 1);
        }     
    }
    
    private Map<String, List<String>> createMapping() {
        Map<String, List<String>> mapping = new HashMap<>();
        mapping.put("0", new ArrayList<String>(){{add("0");}});
        mapping.put("1", new ArrayList<String>(){{add("1");}});
        mapping.put("2", new ArrayList<String>(){{add("a"); add("b"); add("c");}});
        mapping.put("3", new ArrayList<String>(){{add("d"); add("e"); add("f");}});
        mapping.put("4", new ArrayList<String>(){{add("g"); add("h"); add("i");}});
        mapping.put("5", new ArrayList<String>(){{add("j"); add("k"); add("l");}});
        mapping.put("6", new ArrayList<String>(){{add("m"); add("n"); add("o");}});
        mapping.put("7", new ArrayList<String>(){{add("p"); add("q"); add("r"); add("s");}});
        mapping.put("8", new ArrayList<String>(){{add("t"); add("u"); add("v");}});
        mapping.put("9", new ArrayList<String>(){{add("w"); add("x"); add("y"); add("z"); }});
        
        return mapping;
    }
	
}
