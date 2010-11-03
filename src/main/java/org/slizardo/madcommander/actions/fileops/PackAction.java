/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: PackAction.java,v 1.1 2006/03/21 17:25:36 slizardo Exp $
 */
package org.slizardo.madcommander.actions.fileops;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.components.filelisting.FileListing;
import org.slizardo.madcommander.dialogs.PackDialog;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;
import org.slizardo.madcommander.util.gui.DialogFactory;


public class PackAction extends AbstractAction {

	public PackAction() {
		super(Translator.text("Pack..."), IconFactory.newIcon("box.png"));
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
