package jp.whisper.agent;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.LoaderClassPath;
import javassist.NotFoundException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

public class NewClassFileTransformer implements ClassFileTransformer {

	public byte[] transform(ClassLoader loader,
			String className,
			Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain,
			byte[] classfileBuffer)
			throws IllegalClassFormatException {
		if (!className.startsWith("jp/whisper/agent")) {//mainクラスが含まれないと
			return null;
		}
		String newClassName = className.replace("/", ".");
		System.out.println("Transforming: " + newClassName);

		ClassPool pool = ClassPool.getDefault();
		CtClass cl = null;
		try {
			pool.insertClassPath(new LoaderClassPath(loader));
			try {
				cl = pool.get(newClassName);
			} catch (NotFoundException e) {
				//springはバイトを基づいて拡張されたクリアのインスタンスを生成するため、オリジナルクラスを見つからない可能性があるため
				//classfileBufferを利用して、CtClassインスタンスを作る
				ByteArrayInputStream is = null;
				try {
					is = new ByteArrayInputStream(classfileBuffer);
					cl = pool.makeClass(is);
				} finally {
					if (null != is) {
						is.close();
						is = null;
					}
				}
			}

			if (cl.isInterface()) {
				return null;
			}

			CtMethod[] methods = cl.getDeclaredMethods();
			for (CtMethod method : methods) {
				enhance(method);
			}
			return cl.toBytecode();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (null != cl) {
				cl.detach();
			}
		}
	}

	private void enhance(CtMethod method) throws CannotCompileException {
		method.insertBefore("{ System.out.println(\"" + method.getLongName() + " called ...\"); }");
		method.instrument(new ExprEditor() {
			public void edit(MethodCall m) throws CannotCompileException {
				if (m.getClassName().startsWith("jp.whisper.agent.service")) {
					StringBuilder sb = new StringBuilder();
					sb.append("{long startTime = System.currentTimeMillis();");
					sb.append("$_=$proceed($$) + \" amended...\";");
					sb.append("System.out.println(\"");
					sb.append(m.getClassName()).append(".").append(m.getMethodName());
					sb.append(" cost: \" + (System.currentTimeMillis() - startTime) + \" ms\");}");
					m.replace(sb.toString());
				}
			}
		});
	}

}
