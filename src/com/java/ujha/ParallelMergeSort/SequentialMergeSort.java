package com.java.ujha.ParallelMergeSort;

import java.util.Arrays;

public class SequentialMergeSort {

	public static void main(String args[]) {
		SequentialMergeSort sms = new SequentialMergeSort();
		int inp[] = {10,15,5,4,8,9};
		sms.mergeSort(inp);
		for(int i : inp) {
			System.out.println(i);
		}
	}
	public void mergeSort(int input[]) {
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
