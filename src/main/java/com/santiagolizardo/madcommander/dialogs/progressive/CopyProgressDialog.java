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

import org.apache.commons.io.FileUtils;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;

public class CopyProgressDialog extends AbstractProgressDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4051353584577931986L;

	public CopyProgressDialog(MadCommander madCommander) {
		super(madCommander);
	}

	public void run() {
		if (srcPath.equals(dstPath)) {
			DialogFactory.showErrorMessage(src.getParent(),
					"You cannot copy a file to itself!");
			dispose();
			return;
		}

		List<File> selectedFiles = src.getSelectedFiles();
		int numFiles = selectedFiles.size();
		myProcess.totalProgress = 0;
		for (int i = 0; i < selectedFiles.size(); i++) {
			myProcess.currentFile = selectedFiles.get(i).getName();
			myProcess.currentProgress = 0;
			currentFileLabel.setText("Current file: " + myProcess.currentFile);
			String fullSrc = srcPath + File.separator + myProcess.currentFile;
			String fullDst = dstPath + File.separator + myProcess.currentFile;
			StringBuffer buffer = new StringBuffer();
			buffer.append("Copying [ ");
			buffer.append(fullSrc);
			buffer.append(" => ");
			buffer.append(fullDst);
			buffer.append(" ]");
			logger.info(buffer.toString());
			try {
				File fileSrc = new File(fullSrc);
				File fileDst = new File(fullDst);

				if (fileSrc.isDirectory())
					FileUtils.copyDirectory(fileSrc, fileDst);
				else
					FileUtils.copyFile(fileSrc, fileDst);

				myProcess.currentProgress = 100;
				myProcess.totalProgress = (i * 100) / numFiles;

				if (myProcess.cancel) {
					logger.info("Cancel copying.");
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		myProcess.totalProgress = 100;
	}
}
