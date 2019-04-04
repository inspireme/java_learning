package com.whisper.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * java7で各機能を実現する
 *
 * @author whisper
 *
 */
public class Java7 {
	/**
	 *リストにある空要素を合計する
	 * @param strings
	 * @return
	 */
	public static int getCountEmptyString(List<String> strings) {
		int count = 0;

		for (String string : strings) {

			if (string.isEmpty()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * リストにある要素のレングスが「length」の場合、合計する
	 * @param strings
	 * @param length
	 * @return
	 */
	public static int getCountLength(List<String> strings, int length) {
		int count = 0;

		for (String string : strings) {

			if (string.length() == length) {
				count++;
			}
		}
		return count;
	}

	/**
	 *　リストの空要素を削除する
	 * @param strings
	 * @return
	 */
	public static List<String> deleteEmptyStrings(List<String> strings) {
		List<String> filteredList = new ArrayList<String>();

		for (String string : strings) {

			if (!string.isEmpty()) {
				filteredList.add(string);
			}
		}
		return filteredList;
	}

	/**
	 *リストの要素を「separator」で区切りして、連結する
	 * @param strings
	 * @param separator
	 * @return
	 */
	public static String getMergedString(List<String> strings, String separator) {
		StringBuilder stringBuilder = new StringBuilder();

		for (String string : strings) {

			if (!string.isEmpty()) {
				stringBuilder.append(string);
				stringBuilder.append(separator);
			}
		}
		String mergedString = stringBuilder.toString();
		return mergedString.substring(0, mergedString.length() - 1);
	}

	/**
	 *要素の平方を算出する
	 * @param numbers
	 * @return
	 */
	public static List<Integer> getSquares(List<Integer> numbers) {
		List<Integer> squaresList = new ArrayList<Integer>();

		for (Integer number : numbers) {
			Integer square = new Integer(number.intValue() * number.intValue());

			if (!squaresList.contains(square)) {
				squaresList.add(square);
			}
		}
		return squaresList;
	}

	/**
	 * 最大値を算出する
	 * @param numbers
	 * @return
	 */
	public static int getMax(List<Integer> numbers) {
		int max = numbers.get(0);

		for (int i = 1; i < numbers.size(); i++) {

			Integer number = numbers.get(i);

			if (number.intValue() > max) {
				max = number.intValue();
			}
		}
		return max;
	}

	/**
	 *最小値を算出する
	 * @param numbers
	 * @return
	 */
	public static int getMin(List<Integer> numbers) {
		int min = numbers.get(0);

		for (int i = 1; i < numbers.size(); i++) {
			Integer number = numbers.get(i);

			if (number.intValue() < min) {
				min = number.intValue();
			}
		}
		return min;
	}

	/**
	 *リストの要素を加算する
	 * @param numbers
	 * @return
	 */
	public static int getSum(List<Integer> numbers) {
		int sum = (int) (numbers.get(0));

		for (int i = 1; i < numbers.size(); i++) {
			sum += (int) numbers.get(i);
		}
		return sum;
	}

	/**
	 *平均数を算出する
	 * @param numbers
	 * @return
	 */
	public static int getAverage(List<Integer> numbers) {
		return getSum(numbers) / numbers.size();
	}

}
