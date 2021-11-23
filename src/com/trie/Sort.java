package com.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

//Create Trie => Trie trie = new Trie();
//getRoot => trie.getRoot();
//Insert a Word => trie.insert(key,value); where key is string, and value is int
//Search a Word => trie.search(key); return true or false
//Delete a Word => trie.delete(key);
//TrieNode => {TrieNode[] children, boolean isEndWord, int value, 
//markAsLeaf(int val), unMarkAsLeaf()}
public class Sort {
	// Recursive Function to generate all words in alphabetic order
	public static void getWords(TrieNode root, ArrayList<String> result, int level, char[] str) {
		// Leaf denotes end of a word
		if (root.isEndWord) {
			// current word is stored till the 'level' in the character array
			String temp = "";
			for (int x = 0; x < level; x++) {
				temp += Character.toString(str[x]);
			}
			result.add(temp);
		}

		for (int i = 0; i < 26; i++) {
			if (root.children[i] != null) {
				// Non-null child, so add that index to the character array
				str[level] = (char) (i + 'a');
				getWords(root.children[i], result, level + 1, str);
			}
		}
	}

	public static ArrayList<String> sortArray(String[] arr) {
		ArrayList<String> result = new ArrayList<String>();

		// Creating Trie and Inserting words from array
		Trie trie = new Trie();
		for (int x = 0; x < arr.length; x++)
			trie.insert(arr[x]);

		char[] char_arr = new char[20];
		getWords(trie.getRoot(), result, 0, char_arr);
		return result;
	}
	
	

	public static void main(String args[]) {
		// Input keys (use only 'a' through 'z' and lower case)
		String keys[] = { "the", "a", "there", "answer", "any", "by", "bye", "their", "abc" };

		Trie t = new Trie();

		System.out.println("Keys: " + Arrays.toString(keys));

		// Construct trie

		for (int i = 0; i < keys.length; i++)
			t.insert(keys[i]);

		ArrayList<String> list = sortArray(keys);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		Trie1 trie1 = new Trie1();
		
		for (String string : keys) {
			trie1.addWord(string);
		}
		
		ArrayList<String> result = new ArrayList<String>();
		sort2(trie1.getRootNode(),result, new StringBuilder());
		System.out.println(result);

	}

	private static void sort2(TrieNode1 rootNode, ArrayList<String> result, StringBuilder sb) {
		if(rootNode == null)
		{
			return;
		}
		
		if(rootNode.hasChildNode(Trie1.END_OF_WORD_MARKER))
		{
			result.add(sb.toString());
		}
		HashMap<Character,TrieNode1> children = rootNode.getChildren();
		Set<Entry<Character,TrieNode1>> entrySet = children.entrySet();
		for (Entry<Character, TrieNode1> entry : entrySet) {
			if(entry.getValue() !=null && Trie1.END_OF_WORD_MARKER != entry.getKey())
			{
				sb.append(entry.getKey());
				sort2(entry.getValue(), result, sb);
				sb.deleteCharAt(sb.length()-1);
			}
		}
		
	}

}