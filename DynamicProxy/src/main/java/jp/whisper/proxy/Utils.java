package jp.whisper.proxy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import com.sun.istack.internal.Nullable;

import sun.misc.ProxyGenerator;

public class Utils {

	private Utils() {

	}

	public static void printClassFile(String proxyName, @SuppressWarnings("rawtypes") Class c) {
		byte[] data = ProxyGenerator.generateProxyClass(proxyName, new Class[] { c });

		//ファイルに書き込む
		try (FileOutputStream fos = new FileOutputStream(proxyName + ".class");) {
			fos.write(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public final static int BUFFER_SIZE = 1000;

	/**
	 * Copy the contents of the given input File into a new byte array.
	 * @param in the file to copy from
	 * @return the new byte array that has been copied to
	 * @throws IOException in case of I/O errors
	 */
	public static byte[] copyToByteArray(File in) throws IOException {
		return copyToByteArray(Files.newInputStream(in.toPath()));
	}

	public static byte[] copyToByteArray(@Nullable InputStream in) throws IOException {
		if (in == null) {
			return new byte[0];
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream(BUFFER_SIZE);
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = in.read(buffer)) != -1) {
			out.write(buffer, 0, bytesRead);
		}
		out.flush();
		return out.toByteArray();
	}

	/**
	 * Copy the contents of the given input File to the given output File.
	 * @param in the file to copy from
	 * @param out the file to copy to
	 * @return the number of bytes copied
	 * @throws Exception
	 */
	public static int copy(File in, File out) throws Exception {
		if (in == null) {
			throw new Exception("No input File specified");
		}
		if (out == null) {
			throw new Exception("No output File specified");
		}

		return copy(Files.newInputStream(in.toPath()), Files.newOutputStream(out.toPath()));
	}

	/**
	 * Copy the contents of the given byte array to the given output File.
	 * @param in the byte array to copy from
	 * @param out the file to copy to
	 * @throws Exception
	 */
	public static void copy(byte[] in, File out) throws Exception {
		if (in == null || in.length == 0) {
			throw new Exception("No input byte array specified");
		}
		if (out == null) {
			throw new Exception("No output File specified");
		}
		copy(new ByteArrayInputStream(in), Files.newOutputStream(out.toPath()));
	}

	/**
	 * Copy the contents of the given InputStream to the given OutputStream.
	 * Closes both streams when done.
	 * @param in the stream to copy from
	 * @param out the stream to copy to
	 * @return the number of bytes copied
	 * @throws Exception
	 */
	public static int copy(InputStream in, OutputStream out) throws Exception {
		if (in == null) {
			throw new Exception("No inputStream specified");
		}
		if (out == null) {
			throw new Exception("No output File specified");
		}

		try {
			return copyStream(in, out);
		} finally {
			try {
				in.close();
			} catch (IOException ex) {
			}
			try {
				out.close();
			} catch (IOException ex) {
			}
		}
	}

	/**
	 * Copy the contents of the given InputStream to the given OutputStream.
	 * Leaves both streams open when done.
	 * @param in the InputStream to copy from
	 * @param out the OutputStream to copy to
	 * @return the number of bytes copied
	 * @throws Exception
	 */
	public static int copyStream(InputStream in, OutputStream out) throws Exception {
		if (in == null) {
			throw new Exception("No inputStream specified");
		}
		if (out == null) {
			throw new Exception("No output File specified");
		}

		int byteCount = 0;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = in.read(buffer)) != -1) {
			out.write(buffer, 0, bytesRead);
			byteCount += bytesRead;
		}
		out.flush();
		return byteCount;
	}

}
