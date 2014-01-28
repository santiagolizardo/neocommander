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
package com.santiagolizardo.madcommander.util.io;

import java.io.File;

/**
 * Utilities for work with files.
 */
public class FileUtil {

	public static final String LINE_SEP = System.getProperty("line.separator");

	public static String extractDirPart(File file) {
		if (file == null)
			return "null";
		String fileName = file.getAbsolutePath();
		if (file.isDirectory())
			return fileName;

		int last = fileName.lastIndexOf(File.separator);
		String dirPart = fileName.substring(0, last);

		return dirPart;
	}

	public static String extractFilePart(File file) {
		if (file == null)
			return "null";
		String fileName = file.getAbsolutePath();
		if (file.isDirectory())
			return "";

		int last = fileName.lastIndexOf(File.separator);
		String filePart = fileName.substring(last + 1);

		return filePart;
	}

	/**
	 * Returns the attributes for the file passed as parameter, as a string.
	 * 
	 * @param file
	 * @return
	 */
	public static String getAttributes(File file) {
		StringBuffer buffer = new StringBuffer();

		buffer.append(file.canRead() && file.canWrite() == false ? "r" : "-");
		buffer.append("a");
		buffer.append(file.isHidden() ? "h" : "-");
		buffer.append("-");

		return buffer.toString();
	}

	/**
	 * Returns the humanized attributes for the file passed as parameter, as a string.
	 * 
	 * @param file
	 * @return
	 */
	public static String getHumanizedAttributes(File file) {
		String att = "";
		if (file.canRead())
			att += "r ";
		if (file.canWrite())
			att += "w ";
		if (file.canExecute())
			att += "e ";
		return att;
	}
}
