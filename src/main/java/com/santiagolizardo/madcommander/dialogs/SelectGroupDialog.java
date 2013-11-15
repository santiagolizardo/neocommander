/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.dialogs;

import com.santiagolizardo.madcommander.controller.Controller;

public class SelectGroupDialog extends AbstractGroupDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8402206622370638785L;

	public SelectGroupDialog() {
		super("Select group");
	}

	protected void applyPattern(String type, String searchPattern,
			boolean caseSensitive) {
		Controller.selectGroup(type, searchPattern, caseSensitive);
	}
}
