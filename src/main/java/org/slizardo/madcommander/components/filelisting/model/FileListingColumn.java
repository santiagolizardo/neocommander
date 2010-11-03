/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: FileListingColumn.java,v 1.2 2006/03/16 17:41:51 slizardo Exp $
 */
package org.slizardo.madcommander.components.filelisting.model;

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
