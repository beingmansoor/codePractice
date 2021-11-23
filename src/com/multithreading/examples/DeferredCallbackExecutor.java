package com.multithreading.examples;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DeferredCallbackExecutor {

	private static Random random = new Random(System.currentTimeMillis());

	PriorityQueue<CallBack> q = new PriorityQueue<CallBack>(new Comparator<CallBack>() {
		public int compare(CallBack o1, CallBack o2) {
			return (int) (o1.executeAt - o2.executeAt);
		}
	});
	ReentrantLock lock = new ReentrantLock();
	Condition newCallbackArrived = lock.newCondition();

	private long findSleepDuration() {
		long currentTime = System.currentTimeMillis();
		return q.peek().executeAt - currentTime;
	}

	public void start() throws InterruptedException {
		long sleepFor = 0;

		while (true) {

			lock.lock();

			while (q.size() == 0) {
				newCallbackArrived.await();
			}

			while (q.size() != 0) {
				sleepFor = findSleepDuration();

				if (sleepFor <= 0)
					break;

				newCallbackArrived.await(sleepFor, TimeUnit.MILLISECONDS);
			}

			CallBack cb = q.poll();
			System.out.println("Executed at " + System.currentTimeMillis() / 1000 + " required at "
					+ cb.executeAt / 1000 + ": message:" + cb.message);

			lock.unlock();
		}
	}

	public void registerCallback(CallBack callBack) {
		lock.lock();
		q.add(callBack);
		newCallbackArrived.signal();
		lock.unlock();
	}

	static class CallBack {
		long executeAt;
		String message;

		public CallBack(long executeAfter, String message) {
			this.executeAt = System.currentTimeMillis() + (executeAfter * 1000);
			this.message = message;
		}
	}

	public static void runTestTenCallbacks() throws InterruptedException {
		Set<Thread> allThreads = new HashSet<Thread>();
		final DeferredCallbackExecutor deferredCallbackExecutor = new DeferredCallbackExecutor();

		Thread service = new Thread(new Runnable() {
			public void run() {
				try {
					deferredCallbackExecutor.start();
				} catch (InterruptedException ie) {

				}
			}
		});

		service.start();

		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					CallBack cb = new CallBack(1, "Hello this is " + Thread.currentThread().getName());
					deferredCallbackExecutor.registerCallback(cb);
				}
			});
			thread.setName("Thread_" + (i + 1));
			thread.start();
			allThreads.add(thread);
			Thread.sleep(1000);
		}

		for (Thread t : allThreads) {
			t.join();
		}
	}

	/*
	 * Here's another test-case, which first submits a callback that should get
	 * executed after eight seconds. Three seconds later another call back is
	 * submitted which should be executed after only one second. The callback
	 * being submitted later should execute first. The test run would timeout if
	 * run in the browser since the callback service is a perpetual thread but
	 * from the output you can observe the callback submitted second execute
	 * first.
	 */
	public static void runLateThenEarlyCallback() throws InterruptedException {
		final DeferredCallbackExecutor deferredCallbackExecutor = new DeferredCallbackExecutor();

		Thread service = new Thread(new Runnable() {
			public void run() {
				try {
					deferredCallbackExecutor.start();
				} catch (InterruptedException ie) {
				}
			}
		});

		service.start();

		Thread lateThread = new Thread(new Runnable() {
			public void run() {
				CallBack cb = new CallBack(8, "Hello this is the callback submitted first");
				deferredCallbackExecutor.registerCallback(cb);
			}
		});
		lateThread.start();

		Thread.sleep(3000);

		Thread earlyThread = new Thread(new Runnable() {
			public void run() {
				CallBack cb = new CallBack(1, "Hello this is callback sumbitted second");
				deferredCallbackExecutor.registerCallback(cb);
			}
		});
		earlyThread.start();

		lateThread.join();
		earlyThread.join();
	}

	public static void main(String args[]) throws InterruptedException {
		DeferredCallbackExecutor.runTestTenCallbacks();
		
//		runLateThenEarlyCallback();
	}
}