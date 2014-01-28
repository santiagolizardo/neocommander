package com.santiagolizardo.madcommander.util;

import java.util.regex.Pattern;

public class GlobUtils {

	public static Pattern convertGlobToRegexp(String glob) {
		String regexp = glob.replace(".", "\\.");
		regexp = regexp.replace("?", ".");
		regexp = regexp.replace("*", ".*");
		regexp = "^" + regexp + "$";
		return Pattern.compile(regexp);
	}
}
