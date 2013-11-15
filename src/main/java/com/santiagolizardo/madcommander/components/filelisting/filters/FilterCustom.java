/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components.filelisting.filters;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterCustom implements FileFilter {

	private Pattern pattern;

	public FilterCustom(String pattern) {
		this.pattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
	}

	public boolean accept(File file) {
		if (file == null)
			return false;
		if (file.isDirectory())
			return true;

		Matcher matcher = pattern.matcher(file.getName());

		return matcher.matches();
	}
}
