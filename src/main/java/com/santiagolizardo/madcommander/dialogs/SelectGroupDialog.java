/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.dialogs;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.controller.Controller;
import com.santiagolizardo.madcommander.resources.languages.Translator;

public class SelectGroupDialog extends AbstractGroupDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8402206622370638785L;

	private MadCommander mainWindow;

	public SelectGroupDialog(MadCommander mainWindow) {
		super(Translator._("Select group"), mainWindow);

		this.mainWindow = mainWindow;
	}

	protected void applyPattern(String type, String searchPattern,
			boolean caseSensitive) {
		Controller.selectGroup(mainWindow, type, searchPattern, caseSensitive);
	}
}
