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
import com.santiagolizardo.madcommander.dialogs.progressive.CopyProgressDialog;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;

class CopyAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9188948997680927042L;

	private MadCommander mainWindow;

	public CopyAction(MadCommander mainWindow) {
		super(Translator._("Copy"), IconFactory.newIcon("F5.gif"));

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("F5"));

		this.mainWindow = mainWindow;
	}

	public void actionPerformed(ActionEvent event) {
		CopyProgressDialog copyDialog = new CopyProgressDialog(mainWindow);
		copyDialog.begin();
	}
}
