package com.java.ujha.multithreadingExamples;

class Worker implements Runnable {

	public boolean isTerminated() {
		return isTerminated;
	}

	public void setTerminated(boolean isTerminated) {
		this.isTerminated = isTerminated;
	}

	private volatile boolean isTerminated = false;

	@Override
	public void run() {
		while (!isTerminated) {
			System.out.println("Hello from worker class........");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}

public class VolatileExample {
	public static void main(String args[]) throws InterruptedException {
		Worker worker = new Worker();
		Thread t1 = new Thread(worker);
		t1.start();
		Thread.sleep(3000);
		worker.setTerminated(true);
		System.out.println("Finished");
	}
}
