package com.whisper.methodref;

import java.util.function.IntFunction;

public class MethodReference4new {

	public static void main(String[] args) {
		IntFunction<String[]> factory = String[]::new;//メソッド参照
		String[] array = factory.apply(10);

		IntFunction<String[]> factory1 = (int n) -> new String[n];//ラムダ式
		String[] array1 = factory1.apply(10);

		IntFunction<String[]> factory2 = new IntFunction<String[]>() {//匿名クラス
			@Override
			public String[] apply(int value) {
				return new String[value];
			}
		};
		String[] array3 = factory2.apply(10);
	}

}
