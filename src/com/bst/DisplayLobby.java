package com.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class DisplayLobby
{
	
	
	Stack<Node> stack;

	public DisplayLobby(Node root)
	{
		this.stack = new Stack<Node>();
		this.push_all(root);
	}

	public void push_all(Node node)
	{
		while (node != null)
		{
			this.stack.push(node);
			node = node.leftChild;
		}
	}

	public boolean has_next()
	{
		return !stack.isEmpty();
	}

	public String next_name()
	{
		Node tmpNode = this.stack.pop();
		this.push_all(tmpNode.rightChild);
		return tmpNode.val;
	}

	public String[] next_page()
	{
		ArrayList<String> res = new ArrayList<>();
		for (int i = 0; i < 10; i++)
		{
			if (this.has_next())
				res.add(this.next_name());
			else
				break;
		}
		return res.toArray(new String[res.size()]);
	}

	public static void main(String args[])
	{
		BinarySearchTree bst = new BinarySearchTree();
		String[] names = { "Jeanette", "Latasha", "Elvira", "Caryl", "Antoinette", "Cassie", "Charity", "Lyn", "Elia",
				"Anya", "Albert", "Cherlyn", "Lala", "Kandice", "Iliana" };
		for (String name : names)
			bst.insert(name);

		DisplayLobby display = new DisplayLobby(bst.root);
		System.out.println(Arrays.toString(display.next_page()));
		System.out.println(Arrays.toString(display.next_page()));
		System.out.println(Arrays.toString(display.next_page()));
	}
	
	static class Node{
	    public String val;
	    public Node leftChild;
	    public Node rightChild;

	    public Node(String val){
	        this.val = val;
	        this.leftChild = null;
	        this.rightChild = null;
	    }

	    public void insert(String val){
	        Node current = this;
	        Node parent = null;
	        while(current!= null){
	            parent = current;
	            if(val.compareTo(current.val) < 0)
	                current = current.leftChild;
	            else
	                current = current.rightChild;
	        }
	        if(val.compareTo(parent.val) < 0)
	            parent.leftChild = new Node(val);
	        else
	            parent.rightChild = new Node(val);
	    }
	}
	
	static class BinarySearchTree{
	    public Node root;

	    public BinarySearchTree(){
	        this.root = null;
	    }

	    public void insert(String val){
	        if(root == null)
	            root = new Node(val);
	        else
	            this.root.insert(val);
	    }
	}
}