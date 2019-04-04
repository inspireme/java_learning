package com.whisper.methodref;

public class Checker {

	protected int value=0;

	public Checker(){

	}

	public Checker(int value) {
		this.value = value;
	}

	public boolean check() {
		return value % 2 == 0;
	}

	@Override
	public String toString() {
		return getClass().getName() + "." + value;
	}
}
