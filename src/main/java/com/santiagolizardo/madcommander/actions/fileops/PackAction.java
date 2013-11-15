/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions.fileops;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.santiagolizardo.madcommander.MainGUI;
import com.santiagolizardo.madcommander.components.filelisting.FileListing;
import com.santiagolizardo.madcommander.dialogs.PackDialog;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;


public class PackAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4020899583847293332L;

	public PackAction() {
		super(Translator._("Pack..."), IconFactory.newIcon("box.png"));
	}

	public void actionPerformed(ActionEvent event) {
		FileListing fileListing = MainGUI.app.getSource();
		if (fileListing.getNumberOfSelectedRows() == 0) {
			DialogFactory
					.showErrorMessage(fileListing.getParent(),
							"You need to select files before creation of a compressed file.");
			return;
		}
		PackDialog packDialog = new PackDialog();
		packDialog.setVisible(true);
	}
}
