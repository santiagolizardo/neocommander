/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components.filelisting.filters;

import java.io.File;
import java.io.FileFilter;

public class FilterHiddens implements FileFilter {

	public boolean accept(File file) {
		if (file == null)
			return false;

		return (file.isHidden());
	}
}
