/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: MyProcess.java,v 1.3 2006/03/06 17:19:24 slizardo Exp $
 */
package org.slizardo.madcommander.dialogs.progressive;

import java.util.ArrayList;
import java.util.logging.Logger;

public class MyProcess {

	protected static Logger logger = Logger
			.getLogger(MyProcess.class.getName());

	protected String currentFile;

	public int currentProgress;

	public int totalProgress;

	protected String srcPath;

	protected String dstPath;

	protected ArrayList<String> files;

	public boolean cancel;

	public MyProcess() {
		super();

		cancel = false;
	}

	public void cancel() {
		cancel = true;
	}

	public String getCurrentFile() {
		return currentFile;
	}

	public boolean isCurrentComplete() {
		return (currentProgress >= 100);
	}

	public boolean isTotalComplete() {
		return (totalProgress >= 100);
	}
}
