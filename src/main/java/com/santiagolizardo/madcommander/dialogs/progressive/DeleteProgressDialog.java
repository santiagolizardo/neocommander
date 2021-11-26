/*
  This file is part of MadCommander, a file manager with two panels.

  MadCommander is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  MadCommander is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with MadCommander.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.dialogs.progressive;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;
import com.santiagolizardo.madcommander.util.io.FileOperations;

import java.io.File;
import java.util.List;

public class DeleteProgressDialog extends AbstractProgressDialog {


	private List<File> selectedFiles;

	public DeleteProgressDialog(MainWindow mainWindow) {
		super(mainWindow);
	}

	public void setSelectedFiles(List<File> selectedFiles) {
		this.selectedFiles = selectedFiles;
	}

	@Override
	public void run() {
		for (int i = 0; i < selectedFiles.size(); i++) {
			File file = selectedFiles.get(i);
			myProcess.currentFile = file.getName();
			myProcess.currentProgress = 0;
			myProcess.totalProgress = (i * 100) / selectedFiles.size();
			
			logger.info(String.format("Deleting '%s'", file.getName()));
			if (FileOperations.delete(file) == false) {
				String errorString = String.format(Translator.tr("File '%s' could not be deleted."), file.getAbsolutePath());
				DialogFactory.showErrorMessage(sourceListing.getParent(), errorString);
				logger.warning(errorString);
			}
			myProcess.currentProgress = 100;
			if (myProcess.cancel) {
				logger.info("Cancel deleting.");
				sourceListing.refreshFiles();
				return;
			}
		}

		myProcess.totalProgress = 100;

		sourceListing.refreshFiles();
	}
}
