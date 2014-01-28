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
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class FileOperations {

	public static boolean touch(File file) {
		long currentTime = System.currentTimeMillis();
		return file.setLastModified(currentTime);
	}

	public static boolean delete(File file) {
		if (file == null) {
			throw new IllegalArgumentException();
		}

		if (file.isDirectory()) {
			String[] list = file.list();
			for (String child : list) {
				boolean success = delete(new File(file, child));
				if (!success) {
					return false;
				}
			}
		}

		return file.delete();
	}

	public static boolean copy(InputStream is, File file) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			IOUtils.copy(is, fos);
			is.close();
			fos.close();

			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

}
