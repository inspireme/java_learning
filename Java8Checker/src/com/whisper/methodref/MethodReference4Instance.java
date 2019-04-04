package com.whisper.methodref;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.IntSupplier;

public class MethodReference4Instance {

	public static void main(String[] args) {

		/***** 引数がなし ****/
		String str = "test";
		IntSupplier supplier = str::length;// 参照メソッド
		System.out.println(supplier.getAsInt());

		IntSupplier supplier1 = () -> str.length();
		System.out.println(supplier1.getAsInt());// ラムダ式

		IntSupplier supplier2 = new IntSupplier() {// 匿名クラス
			@Override
			public int getAsInt() {
				return str.length();
			}
		};
		System.out.println(supplier2.getAsInt());

		/***** 引数が１個 ****/
		Consumer<String> c = System.out::println;// 参照メソッド
		c.accept("テスト１");

		Consumer<String> c2 = (String s) -> System.out.println(s);// ラムダ式
		c2.accept("テスト２");

		Consumer<String> c3 = new Consumer<String>() {// 匿名クラス
			@Override
			public void accept(String s) {
				System.out.println(s);
			}
		};
		c3.accept("テスト３");

		/***** 引数が２個 ****/
		Object1 obj1 = new Object1();
		BinaryOperator<String> b = obj1::contact;// 参照メソッド
		System.out.println(b.apply("abc", "123"));

		BinaryOperator<String> b2 = (str1, str2) -> {
			return str1 + str2;
		};// ラムダ式
		System.out.println(b2.apply("abc", "123"));

		BinaryOperator<String> b3 = new BinaryOperator<String>() {// 匿名クラス
			@Override
			public String apply(String t, String u) {
				return t + u;
			}
		};
		System.out.println(b3.apply("abc", "123"));

	}

}
