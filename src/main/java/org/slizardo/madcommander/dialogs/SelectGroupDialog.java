/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: SelectGroupDialog.java,v 1.3 2006/03/06 17:19:21 slizardo Exp $
 */
package org.slizardo.madcommander.dialogs;

import org.slizardo.madcommander.controller.Controller;

public class SelectGroupDialog extends AbstractGroupDialog {

	public SelectGroupDialog() {
		super("Select group");
	}

	protected void applyPattern(String type, String searchPattern,
			boolean caseSensitive) {
		Controller.selectGroup(type, searchPattern, caseSensitive);
	}
}
