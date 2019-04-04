package com.whisper.methodref;

import java.time.LocalDate;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.IntBinaryOperator;

public class MethodReference4Static {

	public static void main(String[] args) {

		/***** 引数がなし ****/

		DoubleSupplier supplier = Object1::getRandom;// 参照メソッド
		System.out.println(supplier.getAsDouble());

		DoubleSupplier supplier1 = () -> Object1.getRandom();
		System.out.println(supplier1.getAsDouble());// ラムダ式

		DoubleSupplier supplier2 = new DoubleSupplier() {// 匿名クラス
			@Override
			public double getAsDouble() {
				return Math.random();
			}
		};
		System.out.println(supplier2.getAsDouble());

		/***** 引数が１個 ****/
		Consumer<String> c = Object1::getText;// 参照メソッド
		c.accept("テスト１");

		Consumer<String> c2 = (String s) -> {
			LocalDate date1 = LocalDate.now();
			System.out.println(s + date1.toString());
		};// ラムダ式
		c2.accept("テスト２");

		Consumer<String> c3 = new Consumer<String>() {// 匿名クラス
			@Override
			public void accept(String s) {
				LocalDate date1 = LocalDate.now();
				System.out.println(s + date1.toString());
			}
		};
		c3.accept("テスト３");

		/***** 引数が２個 ****/
		IntBinaryOperator b = Integer::sum;// 参照メソッド
		System.out.println(b.applyAsInt(100, 200));

		IntBinaryOperator b2 = (str1, str2) -> {
			return str1 + str2;
		};// ラムダ式
		System.out.println(b2.applyAsInt(100, 200));

		IntBinaryOperator b3 = new IntBinaryOperator() {// 匿名クラス
			@Override
			public int applyAsInt(int t, int u) {
				return t + u;
			}
		};
		System.out.println(b3.applyAsInt(100, 200));

	}

}
