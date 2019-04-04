package jp.whisper.proxy.extend;

import java.io.File;
import java.io.IOException;

import jp.whisper.proxy.Utils;

public class MyClassLoader extends ClassLoader {

	//プロクシクラスのパス
	private File dir;

	private String proxyClassPackage;

	public String getProxyClassPackage() {
		return this.proxyClassPackage;
	}

	public File getDir() {
		return this.dir;
	}

	public MyClassLoader(String path, String proxyClassPackage) {
		super();
		this.dir = new File(path);
		this.proxyClassPackage = proxyClassPackage;
	}

	protected Class<?> findClass(String name) throws ClassNotFoundException {
		if(this.dir != null) {
			File classFile = new File(dir, name + ".class");
			if(classFile.exists()) {
				try {
					byte[] classBytes = Utils.copyToByteArray(classFile);

					return super.defineClass(this.proxyClassPackage + "." + name, classBytes, 0, classBytes.length);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

		return super.findClass(name);
	}


}
