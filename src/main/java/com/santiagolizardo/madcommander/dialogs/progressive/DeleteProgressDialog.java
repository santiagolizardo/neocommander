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
package com.santiagolizardo.madcommander.dialogs.progressive;

import java.io.File;
import java.util.List;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;
import com.santiagolizardo.madcommander.util.io.FileOperations;

public class DeleteProgressDialog extends AbstractProgressDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1031712739175885827L;
	private List<File> selectedFiles;

	public DeleteProgressDialog(MadCommander mainWindow) {
		super(mainWindow);
	}

	public void setSelectedFiles(List<File> selectedFiles) {
		this.selectedFiles = selectedFiles;
	}

	public void run() {
		for (int i = 0; i < selectedFiles.size(); i++) {
			File file = selectedFiles.get(i);
			myProcess.currentFile = file.getName();
			myProcess.currentProgress = 0;
			myProcess.totalProgress = (int) ((i * 100) / selectedFiles.size());
			StringBuffer buffer = new StringBuffer();
			buffer.append("Deleting [ ");
			buffer.append(file.getName());
			buffer.append(" ]");
			logger.info(buffer.toString());
			if (FileOperations.delete(file) == false) {
				StringBuffer errorBuffer = new StringBuffer();
				errorBuffer.append("File ");
				errorBuffer.append(file.getAbsoluteFile());
				errorBuffer.append(" cannot be deleted!");
				final String errorString = errorBuffer.toString();
				DialogFactory.showErrorMessage(src.getParent(), errorString);
				logger.severe(errorString);
			}
			myProcess.currentProgress = 100;
			if (myProcess.cancel) {
				logger.info("Cancel deleting.");
				src.refreshFiles();
				return;
			}
		}

		myProcess.totalProgress = 100;

		src.refreshFiles();
	}
}
