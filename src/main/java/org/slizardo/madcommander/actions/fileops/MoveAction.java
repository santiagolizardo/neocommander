/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: MoveAction.java,v 1.1 2006/03/21 17:25:36 slizardo Exp $
 */
package org.slizardo.madcommander.actions.fileops;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.slizardo.madcommander.dialogs.progressive.MoveProgressDialog;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;


class MoveAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3679234847107716636L;

	public MoveAction() {
		super(Translator.text("Move"), IconFactory.newIcon("F6.gif"));
		
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("F6"));
	}

	public void actionPerformed(ActionEvent event) {
		MoveProgressDialog progressDialog = new MoveProgressDialog();
		progressDialog.begin();
	}
}
