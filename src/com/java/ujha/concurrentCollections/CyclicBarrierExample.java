package com.java.ujha.concurrentCollections;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CyclicBarrierWorker implements Runnable {
	private int id;
	private Random random;
	private CyclicBarrier cyclicBarrier;

	public CyclicBarrierWorker(int id, CyclicBarrier cyclicBarrier) {
		this.id = id;
		this.cyclicBarrier = cyclicBarrier;
		this.random = new Random();
	}

	@Override
	public void run() {
		doWork();

	}

	private void doWork() {
		System.out.println("Thread with ID: " + id + "start the task");
		try {
			Thread.sleep(random.nextInt(3000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread with ID: " + id + "finished");

		try {
			cyclicBarrier.await();
			System.out.println("After Await");
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class CyclicBarrierExample {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
			@Override
			public void run() {
				System.out.println("All the tasks are finished");
			}

		});

		for (int i = 0; i < 5; i++) {
			executorService.execute(new CyclicBarrierWorker(i + 1, barrier));
		}
		executorService.shutdown();

	}
}
