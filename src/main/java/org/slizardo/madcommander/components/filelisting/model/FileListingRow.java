/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: FileListingRow.java,v 1.11 2010/01/21 17:43:23 slizardo Exp $
 */
package org.slizardo.madcommander.components.filelisting.model;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slizardo.madcommander.util.FormatSingleton;
import org.slizardo.madcommander.util.io.FileUtil;

public class FileListingRow {

	private static final boolean SHOW_BYTES = true;

	public File file;

	public FileListingRow(File file) {
		this.file = file;
	}

	public String getName() {
		if (file != null)
			return file.getName();
		else
			return "";
	}

	public String getExtension() {
		if (file.isDirectory())
			return "";
		else
			return FilenameUtils.getExtension(file.getName());
	}

	public String getSize() {
		if (file.isDirectory()) {
			return "<DIR>";
		}

		if (SHOW_BYTES) {
			return FileUtils.byteCountToDisplaySize(file.length());
		} else {
			return FormatSingleton.getSimpleDecimalFormat().format(
					file.length());
		}
	}

	public String getDate() {
		return FormatSingleton.getSimpleDateFormat().format(
				new Date(file.lastModified()));
	}

	public String getAttributes() {
		return FileUtil.getHumanizedAttributes(file);
	}

	public boolean equals(Object o) {
		if (o instanceof FileListingRow) {
			FileListingRow row = (FileListingRow) o;
			return (file.equals(row.file));
		}
		return false;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String toString() {
		return file.getName();
	}
}
