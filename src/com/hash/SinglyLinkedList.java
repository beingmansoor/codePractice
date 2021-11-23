package com.hash;
public class SinglyLinkedList<T> {
    //Node inner class for SLL
    public class Node {
        public T data;
        public Node next;

    }

    //head node of the linked list
    public Node headNode;
    public int size;

    //constructor
    public SinglyLinkedList() {
        headNode = null;
        size = 0;
    }

     public Node getHeadNode() {
        return headNode;
    }

    public void setHeadNode(Node headNode) {
        this.headNode = headNode;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public boolean isEmpty() {

        if (headNode == null) return true;
        return false;
    }

    //Inserts new data at the start of the linked list
    public void insertAtHead(T data) {
        //Creating a new node and assigning it the new data value
        Node newNode = new Node();
        newNode.data = data;
        //Linking head to the newNode's nextNode
        newNode.next = headNode;
        headNode = newNode;
        size++;
    }

    //Inserts new data at the end of the linked list
    public void insertAtEnd(T data) {
        //if the list is empty then call insertATHead()
        if (isEmpty()) {
            insertAtHead(data);
            return;
        }
        //Creating a new Node with value data
        Node newNode = new Node();
        newNode.data = data;
        newNode.next = null;

        Node last = headNode;
        //iterate to the last element
        while (last.next != null) {
            last = last.next;
        }
        //make newNode the next element of the last node
        last.next = newNode;
        size++;
    }

    //inserts data after the given prev data node
    public void insertAfter(T data, T previous) {

        //Creating a new Node with value data
        Node newNode = new Node();
        newNode.data = data;
        //Start from head node
        Node currentNode = this.headNode;
        //traverse the list until node having data equal to previous is found
        while (currentNode != null && currentNode.data != previous) {
            currentNode = currentNode.next;
        }
        //if such a node was found
        //then point our newNode to currentNode's nextElement
        if (currentNode != null) {
            newNode.next = currentNode.next;
            currentNode.next = newNode;
            size++;
        }
    }

    public void printList() {
        if (isEmpty()) {
            System.out.println("List is Empty!");
            return;
        }

        Node temp = headNode;
        System.out.print("List : ");

        while (temp.next != null) {
            System.out.print(temp.data.toString() + " -> ");
            temp = temp.next;
        }

        System.out.println(temp.data.toString() + " -> null");
    }

    //Searches a value in the given list.
    public boolean searchNode(T data) {
        //Start from first element
        Node currentNode = this.headNode;

        //Traverse the list till you reach end
        while (currentNode != null) {
            if (currentNode.data.equals(data))
                return true; //value found

            currentNode = currentNode.next;
        }
        return false; //value not found
    }

    //Deletes data from the head of list
    public void deleteAtHead() {
        //if list is empty then simply return
        if (isEmpty())
            return;
        //make the nextNode of the headNode equal to new headNode
        headNode = headNode.next;
        size--;
    }

    //Deletes data given from the linked list
    public void deleteByValue(T data) {
        //if empty then simply return
        if (isEmpty())
            return;

        //Start from head node
        Node currentNode = this.headNode;
        Node prevNode = null; //previous node starts from null

        if(currentNode.data.equals(data)) {
            //data is at head so delete from head
            deleteAtHead();
            return;
        }
        //traverse the list searching for the data to delete
        while (currentNode != null) {
            //node to delete is found
            if (data.equals(currentNode.data)){
                prevNode.next = currentNode.next;
            }
            prevNode = currentNode;
            currentNode = currentNode.next;
        }
    }
    //Reverses the linked list
    public void reverse() {
        Node prev = null; //To keep track of the previous element, will be used in swapping links
		Node current = this.headNode; //firstElement
		Node next = null;

		//While Traversing the list, swap links
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		//Linking Head Node with the new First Element
		this.headNode = prev;
    }

    public void removeDuplicates() {
        Node current = headNode; // will be used for outer loop
        Node compare = null;     // will be used for inner loop

        while (current != null && current.next != null) {
            compare = current;

            while (compare.next != null) {
                if (current.data == compare.next.data) {
                    compare.next = compare.next.next;
                } else {
                    compare = compare.next;
                }
            }
            current = current.next;
        }
    }
}