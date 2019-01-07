package com.java.ujha.concurrentCollections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class FirstWorker implements Runnable {

	private BlockingQueue<Integer> blockingQueue;

	public FirstWorker(BlockingQueue<Integer> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		try {
			blockingQueue.put(5);
			blockingQueue.put(6);
			blockingQueue.put(2);
			Thread.sleep(1000);
			blockingQueue.put(1);
			Thread.sleep(1000);
			blockingQueue.put(4);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}

	}

}

class SecondWorker implements Runnable {

	private BlockingQueue<Integer> blockingQueue;

	public SecondWorker(BlockingQueue<Integer> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(5000);
			System.out.println(blockingQueue.take());
			Thread.sleep(1000);
			System.out.println(blockingQueue.take());
			Thread.sleep(1000);
			System.out.println(blockingQueue.take());
			System.out.println(blockingQueue.take());
			Thread.sleep(1000);
			System.out.println(blockingQueue.take());
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}

	}

}

public class PriorityBlockingQueueExample {
	public static void main(String[] args) {
		BlockingQueue<Integer> queue = new PriorityBlockingQueue<>();
		new Thread(new FirstWorker(queue)).start();
		new Thread(new SecondWorker(queue)).start();
	}
}
