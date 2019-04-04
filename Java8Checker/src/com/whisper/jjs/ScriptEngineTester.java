package com.whisper.jjs;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *JSはビルドする必要がないから、何か面白いことが作れるのかな。例え：EXCELの仕様書を読み込んで、ソースを生成するとか、
 *　計算式をDBに保存するに代わって、JSに保つとか
 * @author whisper
 *
 */
public class ScriptEngineTester {

	public static void main(String[] args) {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");

		String name = "Mahesh";
		Integer result = null;

		try {
			nashorn.eval("print('" + name + "')");
			result = (Integer) nashorn.eval("10 + 2");

		} catch (ScriptException e) {
			System.out.println("Error executing script: " + e.getMessage());
		}

		System.out.println(result.toString());

	}

}
