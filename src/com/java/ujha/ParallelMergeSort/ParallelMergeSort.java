package com.java.ujha.ParallelMergeSort;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort extends RecursiveAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] inp;
	
	public ParallelMergeSort(int inp[]) {
		this.inp=inp;
	}
	
	@Override
	protected void compute() {
		if(inp.length<App.THRESHOLD) {
			mergeSort(inp);
			return;
		}
			int mid = inp.length/2;
			int[] leftSubArray = Arrays.copyOfRange(inp, 0, mid);
			int[] rightSubArray = Arrays.copyOfRange(inp, mid+1, inp.length);
			
			ParallelMergeSort task1 = new ParallelMergeSort(leftSubArray);
			ParallelMergeSort task2 = new ParallelMergeSort(rightSubArray);
			
			invokeAll(task1,task2);
			
			merge(leftSubArray,rightSubArray,inp);
		
	}
	
	private void mergeSort(int input[]) {
		if(input.length<=1)
			return;
		
		int middleIndex = input.length/2;
		int[] left = Arrays.copyOfRange(input,0,middleIndex);
		int[] right = Arrays.copyOfRange(input, middleIndex, input.length);
		
		mergeSort(left);
		mergeSort(right);
		merge(left,right,input);
	}
	
	private void merge(int[] leftSubArray, int[] rightSubArray, int[] originalArray) {
		int i=0,j=0,k=0;
		while(i<leftSubArray.length && j<rightSubArray.length) {
			if(leftSubArray[i]<rightSubArray[j])
				originalArray[k++] = leftSubArray[i++];
			else
				originalArray[k++] = rightSubArray[j++];
		}
		
		while(i<leftSubArray.length)
			originalArray[k++] = leftSubArray[i++];
		
		while(j<rightSubArray.length)
			originalArray[k++] = rightSubArray[j++];
	}

	
}
