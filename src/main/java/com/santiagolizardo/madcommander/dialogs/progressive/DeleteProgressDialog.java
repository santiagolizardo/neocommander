/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
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
