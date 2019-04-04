package com.whisper.lambda;

public class LambdaScope2 {

	interface IntArgument {
		public void method(int n);
	}

	interface IntegerArgument {
		public void method(Integer n);
	}

	void call(IntArgument func) {
		func.method(123);
	}

	IntArgument test11 = (int n) -> System.out.println(n);
	IntArgument test12 = (Integer n) -> System.out.println(n);// 引数の型が異なるのでコンパイルエラー。
	IntArgument test13 = (n) -> System.out.println(n);// 引数の型を省略していれば問題ない。

	IntegerArgument test21 = (int n) -> System.out.println(n); // 引数の型が異なるのでコンパイルエラー。
	IntegerArgument test22 = (Integer n) -> System.out.println(n);
	IntegerArgument test23 = (n) -> System.out.println(n); // 引数の型を省略していれば問題ない。



	public static void main(String[] args) {
		IntArgument test31 = (n) -> System.out.println(n);
		LambdaScope2 tester = new LambdaScope2();
		tester.call(test31);
		IntegerArgument test32 = (n) -> System.out.println(n);
		tester.call(test32);	//IntArgumentとIntegerArgumentに互換性（継承関係）は無いのでコンパイルエラー。
		tester.call((int n) -> System.out.println(n));
		tester.call((Integer n) -> System.out.println(n));	//引数の型が異なるのでコンパイルエラー。
		tester.call(n -> System.out.println(n));	//引数の型を省略していれば問題ない。
	}
}
