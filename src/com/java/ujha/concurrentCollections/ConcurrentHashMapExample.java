package com.java.ujha.concurrentCollections;

import java.util.concurrent.ConcurrentHashMap;

class FirstWorkerEx implements Runnable {

	private ConcurrentHashMap<String, Integer> map;

	public FirstWorkerEx(ConcurrentHashMap<String, Integer> map) {
		this.map = map;
	}

	@Override
	public void run() {

		try {
			map.put("B", 1);
			map.put("H", 2);
			Thread.sleep(1000);
			map.put("F", 3);
			map.put("A", 4);
			map.put("E", 5);
			Thread.sleep(1000);
			map.put("C", 1);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

}

class SecondWorkerEx implements Runnable {

	private ConcurrentHashMap<String, Integer> map;

	public SecondWorkerEx(ConcurrentHashMap<String, Integer> map) {
		this.map = map;
	}

	@Override
	public void run() {

		try {
			Thread.sleep(5000);
			System.out.println(map.get("A"));
			Thread.sleep(1000);
			System.out.println(map.get("E"));
			Thread.sleep(1000);
			System.out.println(map.get("C"));
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

}

public class ConcurrentHashMapExample {
	public static void main(String[] args) {
		ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();
		new Thread(new FirstWorkerEx(map)).start();
		new Thread(new SecondWorkerEx(map)).start();
	}
}
