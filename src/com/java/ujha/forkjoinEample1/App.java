package com.java.ujha.forkjoinEample1;

import java.util.concurrent.ForkJoinPool;

public class App {
	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		SimpleRecursiveAction recursiveAction = new SimpleRecursiveAction(120);
		pool.invoke(recursiveAction);
		
		SimpleRecursiveTask task = new SimpleRecursiveTask(120);
		System.out.println(pool.invoke(task));
	}
}
