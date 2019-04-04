package jp.whisper.proxy.extend;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import jp.whisper.proxy.Utils;

public class OrderServiceProxy {

	private final static String rt = "\r";

	public static Object newProxyInstance(MyClassLoader loader,
			Class<?> interfaces,
			MyInvocationHandler h)
			throws IllegalArgumentException {

		if (h == null) {
			throw new NullPointerException();
		}

		Method[] methods = interfaces.getMethods();
		StringBuffer proxyClassStr = new StringBuffer();
		proxyClassStr.append("package ").append(loader.getProxyClassPackage()).append(";").append(rt)
				.append("import java.lang.reflect.Method;").append(rt)
				.append("public class $OrderServiceProxy0 implements ").append(interfaces.getName()).append("{")
				.append(rt)
				.append("OrderServiceHandler h;").append(rt)
				.append("public $OrderServiceProxy0(OrderServiceHandler h){ this.h = h;}").append(rt)
				.append(getMethodString(methods, interfaces)).append("}");

		//generate proxy file
		String fileName = loader.getDir() + File.separator + "$OrderServiceProxy0.java";
		try {
			compile(proxyClassStr, new File(fileName));

			//class load
			Class $orderServiceProxy0 = loader.findClass("$OrderServiceProxy0");

			//init $OrderServiceProxy0
			Constructor constructor = $orderServiceProxy0.getConstructor(OrderServiceHandler.class);

			return constructor.newInstance(h);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * インタフェースを実現する一連メソッドの内容を取得
	 * @param methods     インタフェースに定義されたメソッド配列
	 * @param interfaces  インタフェースクラス
	 * @return
	 */
	private static String getMethodString(Method[] methods, Class interfaces) {
		StringBuffer sb = new StringBuffer();
		for (Method method : methods) {
			Class<?>[] parameterTypes = method.getParameterTypes();
			String params = Arrays.stream(parameterTypes)
					.map(parameterType -> parameterType.getName() + " " + parameterType.getSimpleName().toLowerCase())
					.collect(Collectors.joining(","));
			String clsParams = Arrays.stream(parameterTypes).map(parameterType -> parameterType.getName() + ".class")
					.collect(Collectors.joining(","));
			String invokeParams = Arrays.stream(parameterTypes)
					.map(parameterType -> parameterType.getSimpleName().toLowerCase()).collect(Collectors.joining(","));

			String rtnType = method.getReturnType().getName();

			sb.append("public ").append(rtnType).append(" ").append(method.getName()).append("(").append(params).append(") {")
					.append(" Method method1 = null;")
					.append("  try { method1 = ").append(interfaces.getName()).append(".class.getMethod(\"").append(method.getName()).append("\", new Class[]{").append(clsParams).append("});")
					.append(" return (").append(rtnType).append(")this.h.invoke(this, method1, new Object[]{").append(invokeParams).append("});")
					.append("} catch (Throwable e) { e.printStackTrace();} return null;}")
					.append(rt);
		}
		return sb.toString();
	}

	/**
	 * javaファイルをコンパイルする
	 * @param proxyClassString プロキシjavaソース
	 * @param myProxyFile classファイルの保存先
	 * @throws Exception
	 */
	private static void compile(StringBuffer proxyClassString, File myProxyFile) throws Exception {
		Utils.copy(proxyClassString.toString().getBytes(), myProxyFile);
		JavaCompiler jCompiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = jCompiler.getStandardFileManager(null, null, null);
		Iterable<? extends JavaFileObject> javaFileObjects = fileManager.getJavaFileObjects(myProxyFile);
		JavaCompiler.CompilationTask task = jCompiler.getTask(null, fileManager, null, null, null, javaFileObjects);
		task.call();
		fileManager.close();

	}

}
