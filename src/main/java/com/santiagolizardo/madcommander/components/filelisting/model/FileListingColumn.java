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

public class FileListingColumn {

	private byte index;

	private File file;

	private Object value;

	public FileListingColumn(byte index, File file, Object value) {
		this.index = index;
		this.file = file;
		this.value = value;
	}

	public byte getIndex() {
		return index;
	}

	public File getFile() {
		return file;
	}

	public Object getValue() {
		return value;
	}

	public String toString() {
		return value.toString();
	}
}
