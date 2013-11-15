/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions.mark;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.santiagolizardo.madcommander.dialogs.SelectGroupDialog;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;


class SelectGroupAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1662191719198242841L;

	public SelectGroupAction() {
		super(Translator._("Select_group..."), IconFactory.newIcon("pencil_add.png"));
	}
	
	public void actionPerformed(ActionEvent event) {
		SelectGroupDialog groupDialog = new SelectGroupDialog();
		groupDialog.setVisible(true);
	}
}
