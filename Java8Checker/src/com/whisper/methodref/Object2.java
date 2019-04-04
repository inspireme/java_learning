package com.whisper.methodref;

public class Object2 {
	protected final int value;

	public Object2() {
		this.value =  0;
	}

	public Object2(int value) {
		this.value = value;
	}

	// インスタンスメソッドのisOk
	public boolean isOk() {
		return true;
	}

	// staticメソッドのisOk
	public static boolean isOk(Object2 obj) {
		return obj.value % 2 == 0;
	}
}
