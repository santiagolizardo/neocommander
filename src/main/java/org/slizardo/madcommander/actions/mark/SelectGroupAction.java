/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: SelectGroupAction.java,v 1.1 2006/03/23 08:18:05 slizardo Exp $
 */
package org.slizardo.madcommander.actions.mark;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.slizardo.madcommander.dialogs.SelectGroupDialog;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;


class SelectGroupAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1662191719198242841L;

	public SelectGroupAction() {
		super(Translator.text("Select_group..."), IconFactory.newIcon("pencil_add.png"));
	}
	
	public void actionPerformed(ActionEvent event) {
		SelectGroupDialog groupDialog = new SelectGroupDialog();
		groupDialog.setVisible(true);
	}
}
