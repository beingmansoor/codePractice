package com.heap;
import java.util.Arrays;

public class MinHeap {
 
  private void minHeapify(int[] heapArray, int index) {
		int smallest = index;

		int heapSize = heapArray.length;
		while (smallest < heapSize / 2) { // check parent nodes only
			int left = (2 * index) + 1; //left child
			int right = (2 * index) + 2; //right child
			if (left < heapSize && heapArray[left] < heapArray[index]) {
				smallest = left;
			}

			if (right < heapSize && heapArray[right] < heapArray[smallest]) {
				smallest = right;
			}

			if (smallest != index) { // swap parent with smallest child
				int temp = heapArray[index];
				heapArray[index] = heapArray[smallest];
				heapArray[smallest] = temp;
				index = smallest;
			} else {
				break; // if heap property is satisfied
			}

		} //end of while
	}
 
	public void buildMinHeap(int[] heapArray) {
    
		int heapSize = heapArray.length;
		// swap smallest child to parent node 
		for (int i = (heapSize - 1) / 2; i >= 0; i--) {
			minHeapify(heapArray, i);
		}
	}
	
	public static void main(String[] args) {
		int[] heapArray = { 31, 11, 7, 12, 15, 14, 9, 2, 3, 16 };
		
		System.out.println("Before heapify: "+Arrays.toString(heapArray));		
		new MinHeap().buildMinHeap(heapArray);
		System.out.println("After heapify: "+Arrays.toString(heapArray));
		
	}
}