package com.java.ujha.ParallelMaxFinding;

public class SequentialMaxFinding {

	//O(N)
	public int seqMaxFind(int[] input, int highIndex) {
		int max = input[0];

		for (int i = 1; i < highIndex; i++) {
			if (input[i] > max)
				max = input[i];
		}
		return max;
	}
}
