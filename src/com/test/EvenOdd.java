package com.test;

import java.util.concurrent.Semaphore;

public class EvenOdd {
	public static void main(String[] args) throws InterruptedException {
		
		Semaphore oddSemaphore = new Semaphore(1);
		Semaphore evenSemaphore = new Semaphore(0);
		
		System.out.println("Using semaphores ..");
		for(int i=1; i <=10;i++)
		{
			if(i%2 ==1)
			{
				oddSemaphore.acquire();
				System.out.println(i);
				evenSemaphore.release();
			}
			else
			{
				evenSemaphore.acquire();
				System.out.println(i);
				oddSemaphore.release();
			}
		}
		
		System.out.println("Using wait and notify...");
		Shared shared = new Shared(true);
		
		new EvenThread(shared).start();
		new OddThread(shared).start();
	}
}

class EvenThread extends Thread {
	private Shared obj;

	private int i = 2;

	public EvenThread(Shared obj) {
		this.obj = obj;
	}

	@Override
	public void run() {

		while (i<=10) {
			synchronized (obj) {

				while (obj.isOdd == true) {
					try {
						obj.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				obj.isOdd = true;

				System.out.println(i);
				i += 2;

				obj.notify();
			}
		}
	}
}

class OddThread extends Thread {
	private Shared obj;
	
	int i=1;

	public OddThread(Shared obj) {
		this.obj = obj;
	}

	@Override
	public void run() {

		while (i<=10) {
			synchronized (obj) {

				while (obj.isOdd == false) {
					try {
						obj.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				obj.isOdd = false;
				System.out.println(i);
				i += 2;

				obj.notify();
			}
		}
	}
}

class Shared {
	boolean isOdd;

	public Shared(boolean isOdd) {
		this.isOdd = isOdd;
	}
}