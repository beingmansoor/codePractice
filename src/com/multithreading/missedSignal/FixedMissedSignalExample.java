package com.multithreading.missedSignal;

import java.util.concurrent.Semaphore;

class FixedMissedSignalExample {
	
	public static void main(String[] args) throws InterruptedException {
		example();
	}

    public static void example() throws InterruptedException {

        final Semaphore semaphore = new Semaphore(1);

        Thread signaller = new Thread(new Runnable() {

            public void run() {
                semaphore.release();
                System.out.println("Sent signal..permits" + semaphore.availablePermits());
            }
        });

        Thread waiter = new Thread(new Runnable() {

            public void run() {
                try {
                    semaphore.acquire();
                    System.out.println("Received signal..permits" + semaphore.availablePermits());
                } catch (InterruptedException ie) {
                    // handle interruption
                }
            }
        });

        signaller.start();
        signaller.join();
        Thread.sleep(500);
        waiter.start();
        waiter.join();

        System.out.println("Program Exiting.");
    }
}