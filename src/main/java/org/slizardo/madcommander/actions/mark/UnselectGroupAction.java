/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: UnselectGroupAction.java,v 1.1 2006/03/23 08:18:05 slizardo Exp $
 */
package org.slizardo.madcommander.actions.mark;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.slizardo.madcommander.dialogs.UnselectGroupDialog;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;


class UnselectGroupAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4612806607228003645L;

	public UnselectGroupAction() {
		super(Translator.text("Unselect_group..."), IconFactory.newIcon("pencil_delete.png"));
	}
	
	public void actionPerformed(ActionEvent event) {
		UnselectGroupDialog groupDialog = new UnselectGroupDialog();
		groupDialog.setVisible(true);
	}
}
