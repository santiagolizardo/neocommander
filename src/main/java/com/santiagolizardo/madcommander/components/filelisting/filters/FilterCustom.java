/**
 * This file is part of MadCommander, a file manager with two panels.
 *
 * MadCommander is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MadCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MadCommander.  If not, see <http://www.gnu.org/licenses/>.
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
