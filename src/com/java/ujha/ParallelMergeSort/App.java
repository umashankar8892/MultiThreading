package com.java.ujha.ParallelMergeSort;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {

	public static int THRESHOLD = 10;

	public static void main(String[] args) {

		int[] inp = initializeNums();
		long start = System.currentTimeMillis();
		SequentialMergeSort maxFi = new SequentialMergeSort();
		maxFi.mergeSort(inp);
		System.out.println("Time taken by Sequential Process: " + (System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		ParallelMergeSort pMergeSort = new ParallelMergeSort(inp);
		pool.invoke(pMergeSort);
		System.out.println("Time taken by Parallel Process: " + (System.currentTimeMillis() - start));
	}

	public static int[] initializeNums() {
		Random random = new Random();
		int inp[] = new int[10000000];
		for (int i = 0; i < inp.length; i++)
			inp[i] = random.nextInt(10000);
		return inp;
	}
}
