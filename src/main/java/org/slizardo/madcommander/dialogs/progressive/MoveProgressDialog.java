/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: MoveProgressDialog.java,v 1.7 2010/01/22 17:57:54 slizardo Exp $
 */
package org.slizardo.madcommander.dialogs.progressive;

import java.io.File;
import java.util.List;

import org.slizardo.madcommander.util.gui.DialogFactory;

public class MoveProgressDialog extends AbstractProgressDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6456147166253912620L;

	public void run() {
		if (srcPath.equals(dstPath)) {
			DialogFactory.showErrorMessage(src.getParent(),
					"You cannot move a file to itself!");
			return;
		}

		List<File> selectedFiles = src.getSelectedFiles();

		for (int i = 0; i < selectedFiles.size(); i++) {
			myProcess.currentFile = selectedFiles.get(i).getName();
			myProcess.currentProgress = 0;
			myProcess.totalProgress = (int) ((i * 100) / selectedFiles.size());
			String fullSrc = srcPath + File.separator + myProcess.currentFile;
			String fullDst = dstPath + File.separator + myProcess.currentFile;
			File srcFile = new File(fullSrc);
			File dstFile = new File(fullDst);
			StringBuffer buffer = new StringBuffer();
			buffer.append("Moving [ ");
			buffer.append(fullSrc);
			buffer.append(" => ");
			buffer.append(fullDst);
			buffer.append(" ]");
			logger.info(buffer.toString());
			if (srcFile.renameTo(dstFile) == false) {

			}
			myProcess.currentProgress = 100;
			if (myProcess.cancel) {
				logger.info("Cancel moving.");
				src.refreshFiles();
				return;
			}
		}

		myProcess.totalProgress = 100;
	}
}
