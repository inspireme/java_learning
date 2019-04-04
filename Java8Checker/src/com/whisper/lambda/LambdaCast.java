package com.whisper.lambda;

import java.io.Serializable;

public class LambdaCast {

	public static void main(String[] args) {
		Runnable object = () -> System.out.println("abc");
		Object object1 = () -> System.out.println("abc"); // コンパイルエラー。ラムダ式がどの関数型インターフェースのものなのか分からない為。
		Object object2 = (Runnable) () -> System.out.println("abc"); // 関数型にキャストしたうえで、Objectクラスの変数に代入できるようになる

		Runnable runner = () -> System.out.println(123);
		System.out.println("runner-------:");
		System.out.println(runner instanceof Runnable);
		System.out.println(runner instanceof Serializable);
		runner.run();

		Object object3 = (Runnable) () -> System.out.println(123);
		System.out.println("object3-------:");
		System.out.println(object3 instanceof Runnable);
		System.out.println(object3 instanceof Serializable);

		// 交差型キャストでSerializableを指定するとSerializableが実装（合成）されている扱いになる。
		Runnable runner3 = (Runnable & Serializable) () -> System.out.println(123);
		System.out.println("runner3------:");
		System.out.println(runner3 instanceof Runnable);
		System.out.println(runner3 instanceof Serializable);
		runner3.run();

		Object object4 = (Runnable & Serializable & Cloneable) () -> System.out.println("abc");//CloneableでもOK。
		System.out.println("object4------:");
		System.out.println(object4 instanceof Runnable);
		System.out.println(object4 instanceof Serializable);
		System.out.println(object4 instanceof Cloneable);

	}

}
