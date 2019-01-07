package com.java.ujha.multithreadingExamples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

enum Downloader {
	INSTANCE;
	private Semaphore semaphore = new Semaphore(5, true);

	public void downloadData() {
		try {
			//System.out.println("Acquiring lock");
			semaphore.acquire();
			download();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Releasing lock");
			semaphore.release();
		}
	}

	private void download() {
		System.out.println("Downloading data from web");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class SemaphoreExample {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 12; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					Downloader.INSTANCE.downloadData();
				}

			});
		}
	}
}
