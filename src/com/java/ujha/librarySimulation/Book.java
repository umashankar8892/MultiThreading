package com.java.ujha.librarySimulation;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Book {

	private int id;
	private Lock lock;

	public Book(int id) {
		this.id = id;
		this.lock = new ReentrantLock();
	}

	@Override
	public String toString() {
		return "Book "+this.id;
	}

	public void read(Student student) throws InterruptedException {
		if (lock.tryLock(10000, TimeUnit.MILLISECONDS)) {
			System.out.println("Student " + student.getId() + " starts reading "+this);
			Thread.sleep(2000);
			lock.unlock();
			System.out.println(student + " finished reading");
		}
	}
	
}
