package com.java.ujha.multithreadingExamples;

class Runner1 extends Thread{

	@Override
	public void run() {
		for(int i=0;i<5;i++) {
			System.out.println("Runner1: "+i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class Runner2 extends Thread{

	@Override
	public void run() {
		for(int i=0;i<5;i++)
			System.out.println("Runner2: "+i);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class Concurrency1 {

	public static void main(String[] args) throws InterruptedException {
		System.out.println(Runtime.getRuntime().availableProcessors());
	
		Runner1 runner1 = new Runner1();
		Runner2 runner2 = new Runner2();
		runner1.start();
		runner2.start();
		runner1.join();
		runner2.join();
		System.out.println("Finished the task");
	}
}
