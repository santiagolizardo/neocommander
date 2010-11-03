/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: UnselectGroupDialog.java,v 1.3 2006/03/06 17:19:21 slizardo Exp $
 */
package org.slizardo.madcommander.dialogs;

import org.slizardo.madcommander.controller.Controller;

public class UnselectGroupDialog extends AbstractGroupDialog {

	public UnselectGroupDialog() {
		super("Unselect group");
	}

	protected void applyPattern(String type, String searchPattern,
			boolean caseSensitive) {
		Controller.unselectGroup(type, searchPattern, caseSensitive);
	}
}
