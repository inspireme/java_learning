package com.whisper.lambda;

public class LMDExpression1 {

	/**
	 * 対象の行動を定義する ラムダ式を使用するため、インタフェースのメソッド数が一つしかないとする。 すなわち0個か2個以上かを定義してはいけない
	 * ただ、defaultメソッドとstatic メソッドに限らない
	 *
	 * @author whisper
	 *
	 */
	interface Operation {
		int operation(int a, int b);
	}

	interface GreetingService {
		void sayMessage(String message);
	}

	private int operate(int a, int b, Operation Operation) {
		return Operation.operation(a, b);
	}

	public static void main(String[] args) {

		LMDExpression1 tester = new LMDExpression1();

		// パラメータの型を宣言して、OK
		Operation addition = (int a, int b) -> a + b;

		// パラメータの型を宣言しなくても、OK。自動的に該当するインタフェースを探し出す
		Operation subtraction = (a, b) -> a - b;

		// 実現したソースが複数行である場合、{句①;句②}の形となる
		Operation multiplication = (int a, int b) -> {
			System.out.println("multiplication");
			return a * b;
		};

		Operation division = (int a, int b) -> a / b;

		System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
		System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
		System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
		System.out.println("10 / 5 = " + tester.operate(10, 5, division));

		// パラメータが一つのみでしたら、かっこに囲まれなくてもOK
		GreetingService greetService1 = message -> System.out.println("Hello " + message);

		GreetingService greetService2 = (message) -> System.out.println("Hello " + message);

		greetService1.sayMessage("Mahesh");
		greetService2.sayMessage("Suresh");

	}

}
