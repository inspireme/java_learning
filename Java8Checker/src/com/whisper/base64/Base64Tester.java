package com.whisper.base64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

public class Base64Tester {

	public static void main(String[] args) {
		try {

			System.out.println("Original String: " + "TutorialsPoint?java8");

			/************Basic***********/
			String base64encodedString = Base64.getEncoder().encodeToString("TutorialsPoint?java8".getBytes("utf-8"));
			System.out.println("Base64 Encoded String (Basic) :" + base64encodedString);

			// Decode
			byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);

			System.out.println("Base64 Decoded String (Basic): " + new String(base64decodedBytes, "utf-8"));

			/*********URL******/

			base64encodedString = Base64.getUrlEncoder().encodeToString("TutorialsPoint?java8".getBytes("utf-8"));
			System.out.println("Base64 Encoded String (URL) :" + base64encodedString);

			// Decode
			base64decodedBytes = Base64.getUrlDecoder().decode(base64encodedString);
			System.out.println("Base64 Decoded String (URL): " + new String(base64decodedBytes, "utf-8"));


			/*********MIME ☞複数行で一行ずつ76文字まで******/
			StringBuilder stringBuilder = new StringBuilder();

			for (int i = 0; i < 10; ++i) {
				stringBuilder.append(UUID.randomUUID().toString());
			}
			System.out.println("Original String: " + stringBuilder.toString());

			byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
			String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
			System.out.println("Base64 Encoded String (MIME) :" + mimeEncodedString);

			System.out.println("Base64 Decoded String (MIME) :"
					+ new String(Base64.getMimeDecoder().decode(mimeEncodedString), "utf-8"));

		} catch (UnsupportedEncodingException e) {
			System.out.println("Error :" + e.getMessage());
		}
	}

}
