/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.dialogs;

import com.santiagolizardo.madcommander.controller.Controller;

public class UnselectGroupDialog extends AbstractGroupDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2101906785456634573L;

	public UnselectGroupDialog() {
		super("Unselect group");
	}

	protected void applyPattern(String type, String searchPattern,
			boolean caseSensitive) {
		Controller.unselectGroup(type, searchPattern, caseSensitive);
	}
}
