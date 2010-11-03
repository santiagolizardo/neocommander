/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: FilterHiddens.java,v 1.2 2006/03/06 17:19:22 slizardo Exp $
 */
package org.slizardo.madcommander.components.filelisting.filters;

import java.io.File;
import java.io.FileFilter;

public class FilterHiddens implements FileFilter {

	public boolean accept(File file) {
		if (file == null)
			return false;

		return (file.isHidden());
	}
}
