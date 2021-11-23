package com.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * In this problem, you have to implement the void reverseK(Queue<V> queue, int k) function;
 *  this will take a queue and any number (k) as input, and reverse the first k elements of the queue. 
 *  An illustration is also provided for your understanding.

Function Prototype #
void reverseK(Queue<V> queue, int k) 
Output #
An array with the first “k” elements reversed.

Sample Input #
Queue = {1,2,3,4,5,6,7,8,9,10}
k = 5
Sample Output #
result = {5,4,3,2,1,6,7,8,9,10}
 */
class CheckReverse {

	// 1.Push first k elements in queue in a stack.
	// 2.Pop Stack elements and enqueue them at the end of queue
	// 3.Dequeue queue elements till "k" and append them at the end of queue
	// 4.Dequeue the remaining elements and enqueue them again to append them at
	// end of the queue
	public static <V> void reverseK(Queue queue, int k) {
		if (queue.isEmpty() || k <= 0)
			return;
		Stack stack = new Stack();

		for (int i = 0; i < k; i++)
			stack.push(queue.poll());

		while (!stack.isEmpty())
			queue.add(stack.pop());

		int size = queue.size();
		for (int i = 0; i < size - k; i++)
			queue.add(queue.poll());
	}

	public static void main(String args[]) {

		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		queue.add(5);
		queue.add(6);
		queue.add(7);
		queue.add(8);
		queue.add(9);
		queue.add(10);

		reverseK(queue, 5);

		System.out.print("Queue: ");
		while (!queue.isEmpty()) {
			System.out.print(queue.poll() + " ");
		}
	}
}
