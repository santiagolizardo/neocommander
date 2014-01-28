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

public class MoveProgressDialog extends AbstractProgressDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6456147166253912620L;

	public MoveProgressDialog(MadCommander mainWindow) {
		super(mainWindow);
	}

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
