/**
 * This file is part of MadCommander, a file manager with two panels.
 *
 * MadCommander is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * MadCommander is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * MadCommander. If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.util.io;

import com.santiagolizardo.madcommander.util.OsDetector;
import com.santiagolizardo.madcommander.util.Os;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utilities for work with files.
 */
public class FileUtil {

	private static final Logger logger = Logger.getLogger(FileUtil.class.getName());

	public static final String LINE_SEP = System.getProperty("line.separator");

	public static String extractDirPart(File file) {
		if (file == null) {
			return "null";
		}
		String fileName = file.getAbsolutePath();
		if (file.isDirectory()) {
			return fileName;
		}

		int last = fileName.lastIndexOf(File.separator);
		String dirPart = fileName.substring(0, last);

		return dirPart;
	}

	public static String extractFilePart(File file) {
		if (file == null) {
			return "null";
		}
		String fileName = file.getAbsolutePath();
		if (file.isDirectory()) {
			return "";
		}

		int last = fileName.lastIndexOf(File.separator);
		String filePart = fileName.substring(last + 1);

		return filePart;
	}

	/**
	 * Returns the humanized attributes for the file passed as parameter, as a
	 * string.
	 *
	 * @param file
	 * @return
	 */
	public static String getHumanizedAttributes(File file) {
		Os os = null;
		try {
			os = OsDetector.get();
		} catch(Exception e) { 
			logger.warning(e.getMessage());
			return "";
		}
		switch (os) {
			case Osx:
			case Linux:
				return getPosixHumanizedAttributes(file);
			case Windows:
				BasicFileAttributes attributes;
				try {
					attributes = Files.getFileAttributeView(Paths.get(file.getAbsolutePath()), BasicFileAttributeView.class).readAttributes();
					return attributes.toString();
				} catch (IOException ex) {
					Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
				}
		}
		return "";
	}

	private static String getPosixHumanizedAttributes(File file) {
		Set<PosixFilePermission> set;
		try {
			set = Files.getPosixFilePermissions(file.toPath());
			return PosixFilePermissions.toString(set);
		} catch (IOException ex) {
			logger.log(Level.WARNING, null, ex);
			return "";
		}
	}
}
