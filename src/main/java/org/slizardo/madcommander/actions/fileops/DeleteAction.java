/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: DeleteAction.java,v 1.3 2010/01/22 17:57:54 slizardo Exp $
 */
package org.slizardo.madcommander.actions.fileops;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.components.filelisting.FileListing;
import org.slizardo.madcommander.dialogs.delete.DeleteDialog;
import org.slizardo.madcommander.dialogs.progressive.DeleteProgressDialog;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;

class DeleteAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5996615163711698788L;

	public DeleteAction() {
		super(Translator.text("Delete"), IconFactory.newIcon("F8.gif"));

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("F8"));
	}

	public void actionPerformed(ActionEvent event) {
		FileListing listing = MainGUI.app.getSource();

		List<File> selectedFiles = listing.getSelectedFiles();
		DeleteDialog dialog = new DeleteDialog();
		for (File file : selectedFiles) {
			dialog.addFile(file);
		}
		dialog.setVisible(true);
		if (dialog.getReturnValue() == DeleteDialog.OK) {
			selectedFiles = dialog.getSelectedFiles();
			DeleteProgressDialog progressDialog = new DeleteProgressDialog();
			progressDialog.setSelectedFiles(selectedFiles);
			progressDialog.begin();
		}
	}
}
