package com.java.ujha.forkjoinEample1;

import java.util.concurrent.RecursiveAction;

public class SimpleRecursiveAction extends RecursiveAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int simulatedWork;
	
	public SimpleRecursiveAction(int simulatedWork) {
		this.simulatedWork = simulatedWork;
	}
	
	@Override
	protected void compute() {
		
		if(simulatedWork>100) {
			System.out.println("Parallel execution and split task.."+simulatedWork);
			SimpleRecursiveAction simpleRecursiveAction1 = new SimpleRecursiveAction(simulatedWork/2);
			SimpleRecursiveAction simpleRecursiveAction2 = new SimpleRecursiveAction(simulatedWork/2);
			
			simpleRecursiveAction1.fork();
			simpleRecursiveAction2.fork();
		}else {
			System.out.println("No need for parallel execution, Sequential algorithm is okay "+simulatedWork);
		}
	}

}
