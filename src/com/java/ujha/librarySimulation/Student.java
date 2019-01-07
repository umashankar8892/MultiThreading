package com.java.ujha.librarySimulation;

import java.util.Random;

public class Student implements Runnable {

	private int id;
	private Book[] book;

	public Student(int id, Book[] book) {
		this.id = id;
		this.book = book;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Student " + this.id;
	}

	@Override
	public void run() {
		Random random = new Random();
		while (true) {
			int bookId = random.nextInt(Constants.NUMBER_OF_BOOKS);
			try {
				book[bookId].read(this);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
}
