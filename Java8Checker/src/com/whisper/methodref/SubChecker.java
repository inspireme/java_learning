package com.whisper.methodref;

public class SubChecker extends Checker {

	public SubChecker(int value) {
		super.value = value;
	}

	public boolean check() {
		return value % 5 == 0;
	}

}
