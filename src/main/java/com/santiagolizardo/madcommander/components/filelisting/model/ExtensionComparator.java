/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components.filelisting.model;

import java.util.Comparator;

public class ExtensionComparator implements Comparator<FileListingRow> {

	public int compare(FileListingRow arg0, FileListingRow arg1) {
		FileListingRow rowa = (FileListingRow) arg0;
		FileListingRow rowb = (FileListingRow) arg1;

		return rowa.getExtension().compareTo(rowb.getExtension());
	}
}
