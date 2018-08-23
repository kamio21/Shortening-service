package com.ssosso.shorten.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Base61Util {
	public static final int BASE = 61;
	private static final List BASE61_TABLE = new ArrayList<>(Arrays.asList('G', '3', 'c', 'd', 'Z', 'r', 'b', 'I', 'g', 'O', 'i', 'l', 'p', 'm', 'W', '1', 'q', 'u', 'C', 'w', '9', 'Y', 'x', 'D', 'z', 'B', 'a', 'E', 'F', 'o', 'K', 'H', 'L', '4', 'h', 'M', 'f', '2', 'N', 'e', 't', 'S', 'P', 'n', '5', 'T', 'k', 'V', '0', 'R', 'U', '6', 'A', 'v', 'J', '7', 'y', 'j', 'X', '8', 'Q'));

	public static String toBase61(long decimal) {
		StringBuilder builder = new StringBuilder();
		
		do {
			int remainder = Math.toIntExact(decimal % BASE);
			builder.insert(0, BASE61_TABLE.get(remainder));
			decimal = decimal / BASE;
		} while (decimal > 0);
		
		
		return builder.toString();
	}

	public static long fromBase61(String base62) {
		char[] base62Array = base62.toCharArray();
		int base62Length = base62.length();
		
		return IntStream.range(0, base62Length)
				.mapToLong(i -> (long) (BASE61_TABLE.indexOf(base62Array[i]) * Math.pow(BASE, base62Length - 1 - i)))
				.sum();
	}
}
