package com.java.ujha.ParallelMaxFinding;

import java.util.concurrent.RecursiveTask;

public class ParallelMaxFinding extends RecursiveTask<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int[] nums;
	private int lowIndex;
	private int highIndex;
	
	public ParallelMaxFinding(int[] nums,int lowIndex,int highIndex) {
		this.nums=nums;
		this.lowIndex=lowIndex;
		this.highIndex=highIndex;
	}
	
	@Override
	protected Integer compute() {
		if(highIndex-lowIndex<App.THRESHOLD) {
			//System.out.printf("Calling Sequential flow as element count=%s is less..",nums.length);
			//SequentialMaxFinding s = new SequentialMaxFinding();
			return seqMaxFind(nums, nums.length);
		}else {
			//System.out.printf("Calling parallel flow as element count=%s is more..",nums.length);
			int midIndex = (lowIndex+highIndex)/2;
			ParallelMaxFinding task1 = new ParallelMaxFinding(nums,lowIndex,midIndex);
			ParallelMaxFinding task2 = new ParallelMaxFinding(nums,midIndex+1,highIndex);
			invokeAll(task1,task2);
			return Math.max(task1.join(), task2.join());
		}
	}
	
	public int seqMaxFind(int[] input, int highIndex) {
		int max = input[0];

		for (int i = 1; i < highIndex; i++) {
			if (input[i] > max)
				max = input[i];
		}
		return max;
	}

}
