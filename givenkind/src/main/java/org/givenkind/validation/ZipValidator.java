package org.givenkind.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZipValidator{
	
	private Pattern pattern;
	private Matcher matcher;
	
	private static final String ZIP_PATTERN = "^[0-9]{5}(?:-[0-9]{4})?$";
	
	public ZipValidator() {
		pattern = Pattern.compile(ZIP_PATTERN);
	}
	
	public boolean validate(final String test){
		matcher = pattern.matcher(test);
		return matcher.matches();
	}
}
