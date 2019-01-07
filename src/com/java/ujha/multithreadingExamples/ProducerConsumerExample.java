package com.java.ujha.multithreadingExamples;

import java.util.ArrayList;
import java.util.List;

class Processor {

	private List<Integer> list = new ArrayList<Integer>();
	private final int LIMIT = 5;
	private final int BOTTOM = 0;
	private final Object lock = new Object();
	private int value = 0;
	public void producer() throws InterruptedException {
		synchronized (lock) {
			while (true) {
				if (list.size() == LIMIT) {
					System.out.println("Waiting for consuming item from list..");
					lock.wait();
				}else {
					System.out.println("Adding: "+value);
					list.add(value);
					++value;
					lock.notifyAll();;
				}
				
				Thread.sleep(500);
			}
		}
	}

	public void consumer() throws InterruptedException {
		synchronized(lock) {
			while(true) {
				if(list.size() == BOTTOM) {
					System.out.println("Waiting for producer to produce to list..");
					lock.wait();
				}else {
					System.out.println("Consuming: "+list.remove(list.size()-1));
					lock.notifyAll();
				}
				Thread.sleep(500);
			}
		}
	}
}

public class ProducerConsumerExample {

	public static void main(String args[]) {
		Processor p1 = new Processor();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					p1.producer();
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
					p1.consumer();
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
