package com.multithreading.examples;

public class BlockingQueueWithSemaphore<T> {
	T[] array;
	int size = 0;
	int capacity;
	int head = 0;
	int tail = 0;
	CountingSemaphore semLock = new CountingSemaphore(1, 1);
	CountingSemaphore semItems;

	@SuppressWarnings("unchecked")
	public BlockingQueueWithSemaphore(int capacity) {
		// The casting results in a warning
		array = (T[]) new Object[capacity];
		this.capacity = capacity;
		semItems = new CountingSemaphore(capacity, 0);
	}

	public T dequeue() throws InterruptedException {

		T item = null;

		semItems.acquire();
		semLock.acquire();

		if (head == capacity) {
			head = 0;
		}

		item = array[head];
		array[head] = null;
		head++;
		size--;
		semLock.release();

		return item;
	}

	public void enqueue(T item) throws InterruptedException {

		semItems.release();
		semLock.acquire();

		if (tail == capacity) {
			tail = 0;
		}

		array[tail] = item;
		size++;
		tail++;
		semLock.release();
	}

	public static void main(String args[]) throws InterruptedException {
		final BlockingQueueWithSemaphore<Integer> q = new BlockingQueueWithSemaphore<Integer>(5);

		Thread t1 = new Thread(new Runnable() {

			public void run() {
				try {
					for (int i = 0; i < 20; i++) {
						q.enqueue(new Integer(i));
						System.out.println("enqueued " + i);
					}
				} catch (InterruptedException ie) {

				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			public void run() {
				try {
					for (int i = 0; i < 10; i++) {
						System.out.println("Thread 2 dequeued: " + q.dequeue());
					}
				} catch (InterruptedException ie) {

				}
			}
		});

		Thread t3 = new Thread(new Runnable() {

			public void run() {
				try {
					for (int i = 0; i < 10; i++) {
						System.out.println("Thread 3 dequeued: " + q.dequeue());
					}
				} catch (InterruptedException ie) {

				}
			}
		});

		t1.start();
		Thread.sleep(4000);
		t2.start();
		t2.join();

		t3.start();
		t1.join();
		t3.join();

	}
}