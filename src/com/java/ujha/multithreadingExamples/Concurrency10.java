package com.java.ujha.multithreadingExamples;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Processor1 implements Callable<String> {

	private int id;

	public Processor1(int id) {
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(1000);
		return "id: " + id;
	}

}

public class Concurrency10 {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(2);

		List<Future<String>> result = new ArrayList<Future<String>>();
		for (int i = 0; i < 5; i++) {
			Future<String> future = executorService.submit(new Processor1(i + 1));
			result.add(future);
		}

		for (Future<String> future1 : result) {
			try {
				System.out.println(future1.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		executorService.shutdown();
	}
}
