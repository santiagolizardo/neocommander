/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions.fileops;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.components.filelisting.FileListing;
import com.santiagolizardo.madcommander.dialogs.delete.DeleteDialog;
import com.santiagolizardo.madcommander.dialogs.progressive.DeleteProgressDialog;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;

class DeleteAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5996615163711698788L;
	private MadCommander mainWindow;

	public DeleteAction(MadCommander mainWindow) {
		super(Translator._("Delete"), IconFactory.newIcon("F8.gif"));

		this.mainWindow = mainWindow;

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("F8"));
	}

	public void actionPerformed(ActionEvent event) {
		FileListing listing = mainWindow.getSource();

		List<File> selectedFiles = listing.getSelectedFiles();
		DeleteDialog dialog = new DeleteDialog();
		for (File file : selectedFiles) {
			dialog.addFile(file);
		}
		dialog.setVisible(true);
		if (dialog.getReturnValue() == DeleteDialog.OK) {
			selectedFiles = dialog.getSelectedFiles();
			DeleteProgressDialog progressDialog = new DeleteProgressDialog(
					mainWindow);
			progressDialog.setSelectedFiles(selectedFiles);
			progressDialog.begin();
		}
	}
}
