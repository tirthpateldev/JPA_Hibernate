package com.gjj.igden.utils;
import java.util.concurrent.TimeUnit;

public class NanoStopWatch {

	private static long startTime;

	public static long startNanoStopWatch() {
		startTime = System.nanoTime();
		return startTime;
	}

	public static long getElapsedNanoTime() {
		long endTime = System.nanoTime();
		return endTime - startTime;
	}


	public static void printTime(String message) {
		System.out.println(message);
		System.out.println(TimeUnit.MILLISECONDS.convert(getElapsedNanoTime(), TimeUnit.NANOSECONDS));
	}

	public static String getTimeMillisec(String message) {
		return message + " " + TimeUnit.MILLISECONDS.convert(getElapsedNanoTime(), TimeUnit.NANOSECONDS);
	}
}
