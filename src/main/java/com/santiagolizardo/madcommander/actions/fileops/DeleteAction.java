/**
 * This file is part of MadCommander, a file manager with two panels.
 *
 * MadCommander is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MadCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MadCommander.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.actions.fileops;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import com.santiagolizardo.madcommander.MainWindow;
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
	private MainWindow mainWindow;

	public DeleteAction(MainWindow mainWindow) {
		super(Translator.tr("Delete"), IconFactory.newIcon("F8.gif"));

		this.mainWindow = mainWindow;

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("F8"));
	}

	@Override
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
