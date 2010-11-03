/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: NameComparator.java,v 1.2 2006/01/25 17:00:04 slizardo Exp $
 */
package org.slizardo.madcommander.components.filelisting.model;

import java.util.Comparator;

public class NameComparator implements Comparator<FileListingRow> {

	public int compare(FileListingRow arg0, FileListingRow arg1) {
		FileListingRow rowa = (FileListingRow) arg0;
		FileListingRow rowb = (FileListingRow) arg1;

		return rowa.file.getName().compareTo(rowb.file.getName());
	}

}
