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
import com.santiagolizardo.madcommander.util.gui.DialogFactory;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CopyProgressDialog extends AbstractProgressDialog {



	public CopyProgressDialog(MainWindow madCommander) {
		super(madCommander);
	}

	@Override
	public void run() {
		if (srcPath.equals(dstPath)) {
			DialogFactory.showErrorMessage(sourceListing.getParent(),
					"You cannot copy a file to itself!");
			dispose();
			return;
		}

		List<File> selectedFiles = sourceListing.getSelectedFiles();
		int numFiles = selectedFiles.size();
		myProcess.totalProgress = 0;
		for (int i = 0; i < selectedFiles.size(); i++) {
			myProcess.currentFile = selectedFiles.get(i).getName();
			myProcess.currentProgress = 0;
			currentFileLabel.setText("Current file: " + myProcess.currentFile);
			String fullSrc = srcPath + File.separator + myProcess.currentFile;
			String fullDst = dstPath + File.separator + myProcess.currentFile;
			
			StringBuilder buffer = new StringBuilder();
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
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		myProcess.totalProgress = 100;
	}
}
