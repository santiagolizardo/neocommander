/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
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
