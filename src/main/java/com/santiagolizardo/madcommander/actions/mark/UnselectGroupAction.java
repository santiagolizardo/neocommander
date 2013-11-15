/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions.mark;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.santiagolizardo.madcommander.dialogs.UnselectGroupDialog;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;


class UnselectGroupAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4612806607228003645L;

	public UnselectGroupAction() {
		super(Translator._("Unselect_group..."), IconFactory.newIcon("pencil_delete.png"));
	}
	
	public void actionPerformed(ActionEvent event) {
		UnselectGroupDialog groupDialog = new UnselectGroupDialog();
		groupDialog.setVisible(true);
	}
}
