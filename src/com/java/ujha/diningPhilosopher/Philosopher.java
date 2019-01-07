package com.java.ujha.diningPhilosopher;

import java.util.Random;

public class Philosopher implements Runnable {

	private int id;
	private Chopstick leftChopStick;
	private Chopstick rightChopStick;
	private Random random;
	private int eatingCounter;
	private volatile boolean isFull = false;

	public int getCounter() {
		return eatingCounter;
	}
	
	public Philosopher(int id, Chopstick leftChopStick, Chopstick rightChopStick) {
		this.id = id;
		this.leftChopStick = leftChopStick;
		this.rightChopStick = rightChopStick;
		this.random = new Random();
	}

	@Override
	public void run() {
		try {
			while (!isFull) {
				think();
				if (leftChopStick.pickUp(this, State.LEFT)) {
					if (rightChopStick.pickUp(this, State.RIGHT)) {
						eat();
						rightChopStick.putDown(this, State.RIGHT);
						leftChopStick.putDown(this, State.LEFT);
					}
				}
			}
		} catch (Exception ie) {
			ie.printStackTrace();
		}

	}

	private void think() throws InterruptedException {
		System.out.println(this + " is thinking..");
		Thread.sleep(random.nextInt(1000));
	}

	private void eat() throws InterruptedException {
		System.out.println(this + " is eating..");
		this.eatingCounter++;
		Thread.sleep(random.nextInt(1000));
	}

	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}

	@Override
	public String toString() {
		return "Philosopher " + id;
	}
}
