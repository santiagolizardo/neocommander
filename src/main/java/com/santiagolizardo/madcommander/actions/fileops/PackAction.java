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
package com.santiagolizardo.madcommander.actions.fileops;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.components.filelisting.FileListing;
import com.santiagolizardo.madcommander.dialogs.PackDialog;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PackAction extends AbstractAction {



	private final MainWindow mainWindow;

	public PackAction(MainWindow mainWindow) {
		super(Translator.tr("Pack..."), IconFactory.newIcon("box.png"));

		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		FileListing fileListing = mainWindow.getSource();
		if (fileListing.getNumberOfSelectedRows() == 0) {
			DialogFactory
					.showErrorMessage(fileListing.getParent(),
							"You need to select files before creation of a compressed file.");
			return;
		}
		PackDialog packDialog = new PackDialog(mainWindow);
		packDialog.setVisible(true);
	}
}
