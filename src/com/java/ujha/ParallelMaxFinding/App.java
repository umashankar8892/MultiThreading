package com.java.ujha.ParallelMaxFinding;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {

	static int THRESHOLD = 0;

	public static void main(String[] args) {
		Random random = new Random();
		int inp[] = new int[10000000];
		for (int i = 0; i < 10000000; i++)
			inp[i] = random.nextInt(10000);
		
		
		
		long start = System.currentTimeMillis();
		SequentialMaxFinding maxFi = new SequentialMaxFinding();
		System.out.println("Max: "+maxFi.seqMaxFind(inp, 10000000));
		System.out.println("Time taken by Sequential Process: "+(System.currentTimeMillis()-start));
		
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		start = System.currentTimeMillis();
		ParallelMaxFinding maxFinding = new ParallelMaxFinding(inp, 0, inp.length);
		System.out.println("Max: "+pool.invoke(maxFinding));
		System.out.println("Time taken by parallel process: "+(System.currentTimeMillis()-start));
	}
}
