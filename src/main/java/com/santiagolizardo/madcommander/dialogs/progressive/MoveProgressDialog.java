/*
 * Copyright (C) 2026 Santiago Lizardo
 *
 * This file is part of NeoCommander.
 *
 * NeoCommander is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NeoCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.santiagolizardo.madcommander.dialogs.progressive;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;

import java.io.File;
import java.util.List;

public class MoveProgressDialog extends AbstractProgressDialog {



	public MoveProgressDialog(MainWindow mainWindow) {
		super(mainWindow);
	}

	@Override
	public void run() {
		if (srcPath.equals(dstPath)) {
			DialogFactory.showErrorMessage(sourceListing.getParent(),
					"You cannot move a file to itself!");
			return;
		}

		List<File> selectedFiles = sourceListing.getSelectedFiles();

		for (int i = 0; i < selectedFiles.size(); i++) {
			myProcess.currentFile = selectedFiles.get(i).getName();
			myProcess.currentProgress = 0;
			myProcess.totalProgress = (i * 100) / selectedFiles.size();
			String fullSrc = srcPath + File.separator + myProcess.currentFile;
			String fullDst = dstPath + File.separator + myProcess.currentFile;
			File srcFile = new File(fullSrc);
			File dstFile = new File(fullDst);
			
			String logMessage = "Moving [ %s => %s ]".formatted(fullSrc, fullDst);
			logger.info(logMessage);
			if (srcFile.renameTo(dstFile) == false) {

			}
			myProcess.currentProgress = 100;
			if (myProcess.cancel) {
				logger.info("Cancel moving.");
				sourceListing.refreshFiles();
				return;
			}
		}

		myProcess.totalProgress = 100;
	}
}
