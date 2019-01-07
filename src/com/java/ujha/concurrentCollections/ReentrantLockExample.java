package com.java.ujha.concurrentCollections;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class WorkerThread {

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void producer() throws InterruptedException {
		lock.lock();
		System.out.println("Producer Started...");
		condition.await();
		System.out.println("Producer again..");
		lock.unlock();
	}

	public void consumer() throws InterruptedException {
		lock.lock();
		Thread.sleep(2000);
		System.out.println("Consumer Started...");
		condition.signal();
		lock.unlock();
	}
}

public class ReentrantLockExample {

	public static void main(String args[]) throws InterruptedException{
		WorkerThread wt = new WorkerThread();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					wt.producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					wt.consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		
		t1.start();
		t2.start();
	}
}
