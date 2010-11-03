/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: FileAttributes.java,v 1.3 2006/03/06 17:19:23 slizardo Exp $
 */
package org.slizardo.madcommander.util.io;

import java.util.Calendar;

public class FileAttributes {

	public boolean readOnly;

	public boolean hidden;

	public boolean system;

	public boolean archive;

	public Calendar modificationTime;

	public FileAttributes() {

	}
}
