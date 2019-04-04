package com.whisper.lambda;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.function.Supplier;

public class LambdaSerialize {

	// シリアライズ
	static byte[] serialize(String s) throws IOException {
		Object object = (Supplier<String> & Serializable) () -> "serialize-" + s;

		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
				oos.writeObject(object);
			}
			return bos.toByteArray();
		}
	}

	// デシリアライズ
	static Supplier<String> deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
		try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
				ObjectInputStream ois = new ObjectInputStream(bis)) {
			@SuppressWarnings("unchecked")
			Supplier<String> supplier = (Supplier<String>) ois.readObject();
			return supplier;
		}
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		byte[] buf1 = serialize("str1");
		byte[] buf2 = serialize("str2");

		Supplier<String> supplier1 = deserialize(buf1);
		Supplier<String> supplier2 = deserialize(buf2);

		System.out.println(supplier1.get());
		System.out.println(supplier2.get());
	}

}
