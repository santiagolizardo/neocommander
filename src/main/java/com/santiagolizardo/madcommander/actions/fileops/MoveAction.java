/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions.fileops;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.dialogs.progressive.MoveProgressDialog;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;

class MoveAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3679234847107716636L;

	private MadCommander mainWindow;

	public MoveAction(MadCommander mainWindow) {
		super(Translator._("Move"), IconFactory.newIcon("F6.gif"));

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("F6"));

		this.mainWindow = mainWindow;
	}

	public void actionPerformed(ActionEvent event) {
		MoveProgressDialog progressDialog = new MoveProgressDialog(mainWindow);
		progressDialog.begin();
	}
}
