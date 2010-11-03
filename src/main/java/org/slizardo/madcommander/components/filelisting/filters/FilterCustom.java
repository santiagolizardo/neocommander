/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: FilterCustom.java,v 1.2 2006/03/06 17:19:22 slizardo Exp $
 */
package org.slizardo.madcommander.components.filelisting.filters;

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
