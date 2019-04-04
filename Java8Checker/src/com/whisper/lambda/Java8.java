/**
 *
 */
package com.whisper.lambda;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author whisper
 *
 */
public class Java8 {
	/**
	 * リストにある空要素を合計する
	 *
	 * @param strings
	 * @return
	 */
	public static int getCountEmptyString(List<String> strings) {
		return (int) strings.stream().filter(string -> string.isEmpty()).count();
	}

	/**
	 * リストにある要素のレングスが「length」の場合、合計する
	 *
	 * @param strings
	 * @param length
	 * @return
	 */
	public static int getCountLength(List<String> strings, int length) {
		return (int) strings.stream().filter(string -> string.length() == length).count();
	}

	/**
	 * リストの空要素を削除する
	 *
	 * @param strings
	 * @return
	 */
	public static List<String> deleteEmptyStrings(List<String> strings) {
		return strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
	}

	/**
	 * リストの要素を「separator」で区切りして、連結する
	 *
	 * @param strings
	 * @param separator
	 * @return
	 */
	public static String getMergedString(List<String> strings, String separator) {
		return strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(separator));
	}

	/**
	 * 要素の平方を算出する
	 *
	 * @param numbers
	 * @return
	 */
	public static List<Integer> getSquares(List<Integer> numbers) {
		return  numbers.stream().map( i ->i*i).distinct().collect(Collectors.toList());
	}

	/**
	 * 最大値を算出する
	 *
	 * @param numbers
	 * @return
	 */
	public static int getMax(List<Integer> numbers) {
		IntSummaryStatistics stats = numbers.stream().mapToInt((x) ->x).summaryStatistics();

		return stats.getMax();
	}

	/**
	 * 最小値を算出する
	 *
	 * @param numbers
	 * @return
	 */
	public static int getMin(List<Integer> numbers) {
		IntSummaryStatistics stats = numbers.stream().mapToInt((x) ->x).summaryStatistics();

		return stats.getMin();
	}

	/**
	 * リストの要素を加算する
	 *
	 * @param numbers
	 * @return
	 */
	public static int getSum(List<Integer> numbers) {
		IntSummaryStatistics stats = numbers.stream().mapToInt((x) ->x).summaryStatistics();

		return (int) stats.getSum();
	}

	/**
	 * 平均数を算出する
	 *
	 * @param numbers
	 * @return
	 */
	public static int getAverage(List<Integer> numbers) {
		IntSummaryStatistics stats = numbers.stream().mapToInt((x) ->x).summaryStatistics();

		return (int) stats.getAverage();
	}

}
