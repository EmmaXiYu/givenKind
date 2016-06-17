package org.givenkind.common;

public class ConversionUtil {

	private static final int RADIX = 36;

	public static Long alphanumToNumeric(String alphanum) {
		return Long.valueOf(alphanum.toLowerCase(), RADIX);
	}
	
	public static String numericToAlphanum(Long numeric) {
		return Long.toString(numeric, RADIX).toUpperCase();
	}
	
}
