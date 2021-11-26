/*
  This file is part of MadCommander, a file manager with two panels.

  MadCommander is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  MadCommander is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with MadCommander.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.util.io;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

public class FileOperations {
	
	private static final Logger logger = Logger.getLogger(FileOperations.class.getName());

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
			if (list != null) {
				for (String child : list) {
					boolean success = delete(new File(file, child));
					if (!success) {
						return false;
					}
				}
			}
		}

		return file.delete();
	}

	public static boolean copy(InputStream is, File file) {
		try {
			try (FileOutputStream fos = new FileOutputStream(file)) {
				IOUtils.copy(is, fos);
				is.close();
			}

			return true;
		} catch (IOException e) {
			logger.warning(e.getMessage());
			return false;
		}
	}

}
