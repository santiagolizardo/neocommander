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
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

class EditAction extends AbstractAction {


	private final MainWindow mainWindow;

	public EditAction(MainWindow mainWindow) {
		super(Translator.tr("Edit"), IconFactory.newIcon("F4.gif"));

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("F4"));
		
		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		FileListing listing = mainWindow.getSource();
		List<File> files = listing.getSelectedFiles();
		if (files.size() == 1) {
			File file = files.get(0);
			if (Desktop.getDesktop().isSupported(Desktop.Action.EDIT)) {

				try {
					Desktop desktop = Desktop.getDesktop();
					desktop.edit(file);
				} catch (IOException e) {
					DialogFactory.showErrorMessage(listing.getParent(),
							e.getMessage());
				}
			} else {
				DialogFactory.showErrorMessage(listing.getParent(),
						"The file isn't editable");
			}
		} else {
			DialogFactory.showErrorMessage(listing.getParent(),
					"You cannot edit more than one file at the same time!");
		}
	}
}
