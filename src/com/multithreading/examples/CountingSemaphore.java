package com.multithreading.examples;
public class CountingSemaphore {

	int usedPermits = 0; // permits given out
	int maxCount; // max permits to give out

    public CountingSemaphore(int count) {
        this.maxCount = count;
    }

    public CountingSemaphore(int count, int initialPermits) {
        this.maxCount = count;
        this.usedPermits = this.maxCount - initialPermits;
    }

    public synchronized void acquire() throws InterruptedException {

        while (usedPermits == maxCount)
            wait();

        notify();
        usedPermits++;
    }

    public synchronized void release() throws InterruptedException {

        while (usedPermits == 0)
            wait();

        usedPermits--;
        notify();
    }
    
    public static void main( String args[] ) throws InterruptedException {
        
        final CountingSemaphore cs = new CountingSemaphore(1);

          Thread t1 = new Thread(new Runnable() {

              @Override
              public void run() {
                  try {
                      for (int i = 0; i < 5; i++) {
                          cs.acquire();
                          System.out.println("Ping " + i);
                      }
                  } catch (InterruptedException ie) {

                  }
              }
          });

          Thread t2 = new Thread(new Runnable() {

              @Override
              public void run() {
                  for (int i = 0; i < 5; i++) {
                      try {
                          cs.release();
                          System.out.println("Pong " + i);
                      } catch (InterruptedException ie) {

                      }
                  }
              }
          });

          t2.start();
          t1.start();
          t1.join();
          t2.join();
      }
  }
