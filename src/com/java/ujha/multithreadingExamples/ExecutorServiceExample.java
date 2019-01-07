package com.java.ujha.multithreadingExamples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WorkerT implements Runnable {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
			try {
				Thread.sleep(300);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
}

public class ExecutorServiceExample {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		for(int i=0;i<5;i++) {
			executorService.submit(new WorkerT());
		}
		executorService.shutdown();
	}
}
