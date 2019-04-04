package com.whisper.methodref;

import java.util.ArrayList;
import java.util.List;

public class MethodReference1 {

	public static void main(String[] args) {
		List<String> names = new ArrayList<String>();

		names.add("Mahesh");
		names.add("Suresh");
		names.add("Ramesh");
		names.add("Naresh");
		names.add("Kalpesh");

		//names.forEach(System.out::println);
		names.forEach(s -> System.out.println(s));

	}

}
