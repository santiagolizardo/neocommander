/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.dialogs;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.controller.Controller;

public class UnselectGroupDialog extends AbstractGroupDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2101906785456634573L;

	private MadCommander mainWindow;

	public UnselectGroupDialog(MadCommander mainWindow) {
		super("Unselect group", mainWindow);

		this.mainWindow = mainWindow;
	}

	protected void applyPattern(String type, String searchPattern,
			boolean caseSensitive) {
		Controller
				.unselectGroup(mainWindow, type, searchPattern, caseSensitive);
	}
}
