/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions.mark;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.dialogs.UnselectGroupDialog;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;

class UnselectGroupAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4612806607228003645L;

	private MadCommander mainWindow;

	public UnselectGroupAction(MadCommander mainWindow) {
		super(Translator._("Unselect group..."), IconFactory
				.newIcon("pencil_delete.png"));

		this.mainWindow = mainWindow;
	}

	public void actionPerformed(ActionEvent event) {
		UnselectGroupDialog groupDialog = new UnselectGroupDialog(mainWindow);
		groupDialog.setVisible(true);
	}
}
