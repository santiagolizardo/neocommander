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

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class CreateEmptyFileAction extends AbstractAction {

	/**
	 * 
	 */
	private final MainWindow mainWindow;

	public CreateEmptyFileAction(MainWindow mainWindow) {
		super(Translator.tr("Create empty file..."), IconFactory
				.newIcon("page_white.png"));

		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String fileName = DialogFactory.showInputDialog(mainWindow,
				"File name:");
		String path = mainWindow.getSource().getPath();
		File dir = new File(path, fileName);
		if (dir.exists()) {
			DialogFactory.showErrorMessage(mainWindow,
					"The file already exists.");
		} else {
			try {
				dir.createNewFile();
				mainWindow.getSource().refreshFiles();
			} catch (IOException e) {
				DialogFactory.showErrorMessage(
						mainWindow,
						"MadCommander could not create the file.\n"
								+ e.getMessage());
			}
		}
	}
}
