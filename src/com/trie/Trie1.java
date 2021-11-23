package com.trie;
public class Trie1 {

    private TrieNode1 rootNode;
    public static final char END_OF_WORD_MARKER = '\0';

    public Trie1() {
        this.rootNode = new TrieNode1();
    }

    public TrieNode1 getRootNode() {
		return rootNode;
	}

	public boolean addWord(String word) {

        TrieNode1 currentNode = this.rootNode;
        boolean isNewWord = false;

        // Work downwards through the trie, adding nodes
        // as needed, and keeping track of whether we add
        // any nodes.
        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);

            if (!currentNode.hasChildNode(character)) {
                isNewWord = true;
                currentNode.makeChildNode(character);
            }

            currentNode = currentNode.getChildNode(character);
        }

        // Explicitly mark the end of a word.
        // Otherwise, we might say a word is
        // present if it is a prefix of a different,
        // longer word that was added earlier.
        if (!currentNode.hasChildNode(END_OF_WORD_MARKER)) {
            isNewWord = true;
            currentNode.makeChildNode(END_OF_WORD_MARKER);
        }

        return isNewWord;
    }
}