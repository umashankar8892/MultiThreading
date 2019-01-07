package com.java.ujha.librarySimulation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
	public static void main(String[] args) {

		Student[] student = null;
		Book[] book = null;
		ExecutorService exeService = Executors.newFixedThreadPool(Constants.NUMBER_OF_STUDENTS);

		try {
			book = new Book[Constants.NUMBER_OF_BOOKS];
			student = new Student[Constants.NUMBER_OF_STUDENTS];
			
			for(int i=0;i<Constants.NUMBER_OF_BOOKS;i++)
				book[i] = new Book(i);
			
			for(int i=0;i<Constants.NUMBER_OF_STUDENTS;i++) {
				student[i] = new Student(i,book);
				exeService.execute(student[i]);
			}
			
		}catch(Exception ie) {
			ie.printStackTrace();
			exeService.shutdown();
		}finally {
			exeService.shutdown();
		}
	}
}
