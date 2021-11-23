package com.trie;
import java.util.HashMap;

public class TrieNode1 {

    private HashMap<Character, TrieNode1> nodeChildren;

    public HashMap<Character, TrieNode1> getChildren() {
		return nodeChildren;
	}

	public TrieNode1() {
        this.nodeChildren = new HashMap<>();
    }

    public boolean hasChildNode(char character) {
		return this.nodeChildren.containsKey(character);
    }

    public void makeChildNode(char character) {
        this.nodeChildren.put(character, new TrieNode1());
    }

    public TrieNode1 getChildNode(char character) {
        return this.nodeChildren.get(character);
    }
}