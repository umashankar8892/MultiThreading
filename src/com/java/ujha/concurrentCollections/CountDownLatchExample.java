package com.java.ujha.concurrentCollections;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CountDownLatchWorker implements Runnable {

	private int id;
	private CountDownLatch countDownLatch;
	private Random random;

	public CountDownLatchWorker(int id, CountDownLatch countDownLatch) {
		this.id = id;
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		doWork();
		countDownLatch.countDown();
	}

	private void doWork() {
		System.out.println("Thread with id " + id + " started working");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

public class CountDownLatchExample {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		CountDownLatch latch = new CountDownLatch(5);

		for (int i = 0; i < 5; i++) {
			executorService.execute(new CountDownLatchWorker(i + 1, latch));
		}

		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("All the prerequisites are done...");
		executorService.shutdown();
	}
}
