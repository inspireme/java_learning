package com.whisper.methodref;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MethodReference4Special {
	public int method2() {
		return 123;
	}

	private static int method4() {
		return 1234;
	}

	public void method1() {// 自分自身のクラスにあるインスタンスメソッドを渡す場合は、「this::」を付ける
		IntSupplier supplier = this::method2;
		System.out.println(supplier.getAsInt());
	}

	public void method3() {// 自分自身のクラスにあるstaticメソッドを渡す場合は、「自クラス名::」を付ける
		IntSupplier supplier = MethodReference4Special::method4;
		System.out.println(supplier.getAsInt());
	}

	public void method5() {// インスタンスメソッドを渡す際に「クラス名::」を使っている例。
		/**
		 * 引数が2つ（A a, B b）ある関数を渡す場合、 「a.method(b)」という呼び出しになるなら、
		 * 「A::method」という形でクラス名を用いたメソッド参照が渡せる。
		 */
		BiFunction<List<String>, String, Boolean> f = List<String>::add;

		List<String> list = new ArrayList<>();
		f.apply(list, "abc");
		System.out.println(list);

		// ラムダ式を使ってもメソッド参照を使っても同じ結果が出力されている。
		List<String> list1 = new ArrayList<>();
		BiFunction<List<String>, String, Boolean> f2 = (list2, s) -> list2.add(s);
		f2.apply(list1, "abc");
		System.out.println(list);

	}



	public void method6(){//曖昧なメソッド参照
		Object2 obj2 = new Object2();
		IntPredicate p = value -> Object2.isOk(obj2);
		System.out.println(p.test(5));

		BooleanSupplier b = () -> obj2.isOk();
		System.out.println(b.getAsBoolean());

		//×　Predicate<Object2> p2 = obj2::isOk;//The method isOk(Object2) from the type Object2 should be accessed in a static

		//×　Predicate<Object2> p2 = Object2::isOk;//Ambiguous method reference: both isOk() and isOk(Object2) from the type Object2 are eligible

		List<Object2> list = IntStream.rangeClosed(1, 10).mapToObj(Object2::new).collect(Collectors.toList());

		// ラムダ式によるインスタンスメソッド呼び出し
		System.out.println(list.stream().filter(value -> value.isOk()).collect(Collectors.toList()));

		// ラムダ式によるstaticメソッド呼び出し
		System.out.println(list.stream().filter(value -> Object2.isOk(value)).collect(Collectors.toList()));

		// メソッド参照→コンパイルエラー
        //×　System.out.println(list.stream().filter(Value::isOk).collect(Collectors.toList()));

	}

	public static <T> void methodG(T arg) {//ジェネリクス
		System.out.println(arg);

	}

	public static void main(String[] args) {

		Consumer<String> c = MethodReference4Special::<String>methodG;// 引数型を明示的に指定したい場合、「::」の直後に「<型引数>」を指定する
		c.accept("111");

	}

}
