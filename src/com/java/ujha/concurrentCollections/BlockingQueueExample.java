package com.java.ujha.concurrentCollections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class BlockingQueueWorker implements Runnable {

	private BlockingQueue<Integer> blkQueue;

	public BlockingQueueWorker(BlockingQueue<Integer> blkQueue) {
		this.blkQueue = blkQueue;
	}

	@Override
	public void run() {
		int counter = 0;
		while (true) {
			try {
				blkQueue.put(counter);
				System.out.println("Putting item to the queue");
				counter++;
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

class BlockingQueueWorker1 implements Runnable {

	private BlockingQueue<Integer> blkQueue;

	public BlockingQueueWorker1(BlockingQueue<Integer> blkQueue) {
		this.blkQueue = blkQueue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("Taking item from the queue " + blkQueue.take());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class BlockingQueueExample {

	public static void main(String[] args) {
		BlockingQueue<Integer> bQueue = new ArrayBlockingQueue<Integer>(10);
		
		BlockingQueueWorker worker1 = new BlockingQueueWorker(bQueue);
		BlockingQueueWorker1 worker2 = new BlockingQueueWorker1(bQueue);
		
		new Thread(worker1).start();
		new Thread(worker2).start();
	}
}
