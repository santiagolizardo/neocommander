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
package com.santiagolizardo.madcommander.components.filelisting.model;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.santiagolizardo.madcommander.util.FormatSingleton;
import com.santiagolizardo.madcommander.util.io.FileUtil;

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
