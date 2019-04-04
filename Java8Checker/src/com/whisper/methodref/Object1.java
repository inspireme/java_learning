package com.whisper.methodref;

import java.time.LocalDate;

public class Object1 {


	public String contact(String a, String b) {
		return a + b;
	}

	public static double getRandom() {
		return Math.random();
	}

	public static void getText(String prex) {
		LocalDate date1 = LocalDate.now();
		System.out.println(prex + date1.toString());
	}
}
