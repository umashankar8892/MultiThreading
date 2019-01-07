package com.java.ujha.multithreadingExamples;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {

	private static int counter = 0;
	private static Lock lock = new ReentrantLock();

	public static void increment() {
		lock.lock();
		try {
			for (int i = 0; i < 5000; i++)
				++counter;
		} finally {
			lock.unlock();
		}
	}

	public static void main(String args[]) throws InterruptedException {

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				increment();

			}

		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				increment();
			}

		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println("Counter is " + counter);
	}
}
