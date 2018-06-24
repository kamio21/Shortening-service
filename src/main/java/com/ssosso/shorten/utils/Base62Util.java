package com.ssosso.shorten.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Base62Util {
	static final private long BASE = 62;
	static final private List BASE62_TABLE = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
	
	static public String toBase62(long decimal) {
		StringBuilder builder = new StringBuilder();
		
		do {
			int remainder = Math.toIntExact(decimal % BASE);
			builder.insert(0, BASE62_TABLE.get(remainder));
			decimal = decimal/ BASE;
		} while (decimal > 0);
		
		
		return builder.toString();
	}
	
	static public long fromBase62(String base62) {
		char[] base62Array = base62.toCharArray();
		int base62Length = base62.length();
		
		return IntStream.range(0, base62Length)
				.mapToLong(i -> (long) (BASE62_TABLE.indexOf(base62Array[i]) * Math.pow(BASE, base62Length - 1 - i)))
				.sum();
	}
}
