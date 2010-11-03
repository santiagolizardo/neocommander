/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: DeleteProgressDialog.java,v 1.10 2010/01/22 17:57:54 slizardo Exp $
 */
package org.slizardo.madcommander.dialogs.progressive;

import java.io.File;
import java.util.ArrayList;

import org.slizardo.madcommander.util.gui.DialogFactory;
import org.slizardo.madcommander.util.io.FileOperations;


public class DeleteProgressDialog extends AbstractProgressDialog {

	private ArrayList<File> selectedFiles;
	
	public void setSelectedFiles(ArrayList<File> selectedFiles) {
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
				DialogFactory.showErrorMessage(src.getParent(),
						errorString);
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
