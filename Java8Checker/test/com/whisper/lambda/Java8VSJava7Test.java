/**
 *
 */
package com.whisper.lambda;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author whisper
 *
 */
public class Java8VSJava7Test {

	//テスト対象
	private static List<String> strings = null;

	private static List<Integer> numbers = null;

	private static List<Integer> integers = null;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		 numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		 integers = Arrays.asList(1,2,13,4,15,6,17,8,19);
	}

	@Test
	public void test() {
		assertEquals(Java8.getCountEmptyString(strings), Java7.getCountEmptyString(strings));
		assertEquals(Java8.getCountLength(strings,3), Java7.getCountLength(strings,3));
		assertEquals(Java8.deleteEmptyStrings(strings), Java7.deleteEmptyStrings(strings));
		assertEquals(Java8.getMergedString(strings,","), Java7.getMergedString(strings,","));
		assertEquals(Java8.getSquares(numbers), Java7.getSquares(numbers));
		assertEquals(Java8.getMax(integers), Java7.getMax(integers));
		assertEquals(Java8.getMin(integers), Java7.getMin(integers));
		assertEquals(Java8.getAverage(integers), Java7.getAverage(integers));
		assertEquals(Java8.getSum(integers), Java7.getSum(integers));
	}

}
