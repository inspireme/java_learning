package com.whisper.methodref;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MethodReference4ParentCLS {

	public static void checkNew() {
		/**
		 * メソッド参照はラムダ式と同義なので、明示的に「クラス名::メソッド名」の形式で指定しても、対象自体のインスタンスのクラスのメソッドが呼ばれる
		 *
		 */
		List<Checker> list = Arrays.asList(new Checker(1), new Checker(2), new Checker(3), new SubChecker(3),
				new SubChecker(4), new SubChecker(5));
		// ラムダ式で指定
		System.out.println(list.stream().filter(checker -> checker.check()).collect(Collectors.toList()));

		// メソッド参照で指定
		// ×System.out.println(list.stream().filter(SubChecker::check).collect(Collectors.toList()));//コンパイルエラー

		// ラムダ式で親クラスを指定といっても
		System.out.println(list.stream().filter((Checker check) -> check.check()).collect(Collectors.toList()));

		// メソッド参照で親クラスを指定といっても
		System.out.println(list.stream().filter(Checker::check).collect(Collectors.toList()));

	}

	public static void main(String[] args) {
		MethodReference4ParentCLS.checkNew();
	}

}
