package com.whisper.lambda;

import java.util.function.Consumer;

public class LambdaScope {

	// ラムダ式の外側で定義されている変数を、ラムダ式内部から参照することが出来る。
	void scope1() {
		int n = 123;

		Runnable runner = () -> {
			System.out.println(n);
		};
		runner.run();
	}

	// 匿名クラスの内側からも外側の変数を参照できる。（JDK1.7以前はfinal変数である必要があった)
	void scope1a() {
		int n = 123;

		Runnable runner = new Runnable() {
			@Override
			public void run() {
				System.out.println(n);
			}
		};
		runner.run();
	}

	/** 内側再代入 **/

	// その変数の値を変える（再代入する）ことは出来ない。
	void scope2() {
		int n = 123;

		Runnable runner = () -> {
			n++;// コンパイルエラーになる
		};
		runner.run();
	}

	void scope2a() {
		int n = 123;

		Runnable runner = new Runnable() {
			@Override
			public void run() {
				n++;// コンパイルエラーになる
			}
		};
		runner.run();
	}

	/** 外側再代入 **/
	void scope3() {
		int n = 123;

		Runnable runner = () -> {
			System.out.println(n);
		};

		n++;// コンパイルエラーになる
		runner.run();
	}

	void scope3a() {
		int n = 123;

		Runnable runner = new Runnable() {
			@Override
			public void run() {
				System.out.println(n);
			}
		};

		n++;// コンパイルエラーになる
		runner.run();
	}

	/*** 変数が外側で既に定義されている **/

	void scope4() {
		int t = 123;

		Consumer<String> consumer = (String t) -> {// コンパイルエラーになる,「String
													// t」を「String
													// m」に入れ替えたら、エラーにならない
			System.out.println(t);
		};
		consumer.accept("abc");
	}

	void scope4a() {
		int t = 123;

		Consumer<String> consumer = new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println(t);// 匿名クラスでは別スコープになる。
			}
		};
		consumer.accept("abc");
	}

	/*** this **/
	// 「ラムダ式を定義したメソッド」が属しているクラスのインスタンスを指す。
	void scopeThis() {
		Runnable runner = () -> {
			System.out.println(this);//☞com.whisper.Test$1@548c4f57
		};
		runner.run();
	}

	// thisは、匿名クラスのインスタンスを指す。
	void scopeThisA() {
		Runnable runner = new Runnable() {
			@Override
			public void run() {
				System.out.println(this);//☞com.whisper.Test@15db9742　匿名クラスのインスタンスを指す(new LMDExpressionScope())
			}
		};
		runner.run();
	}

	/*** static **/
	static void scopeThisStatic() {
		  Runnable runner = () -> {
		    System.out.println(this);// コンパイルエラーになる
		  };
		  runner.run();
		}

	static void scopeThisStaticA() {
		  Runnable runner = new Runnable() {
		    @Override
		    public void run() {
		      System.out.println(this);//匿名クラスのインスタンスを指す(new LMDExpressionScope())
		    }
		  };
		  runner.run();
		}
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
