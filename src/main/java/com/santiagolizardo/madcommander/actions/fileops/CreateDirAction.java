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
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.logging.Logger;

class CreateDirAction extends AbstractAction {


	private static final Logger LOGGER = Logger.getLogger(CreateDirAction.class
			.getName());
	
	private final MainWindow mainWindow;

	public CreateDirAction(MainWindow mainWindow) {
		super(Translator.tr("Create dir"), IconFactory.newIcon("F7.gif"));

		this.mainWindow = mainWindow;

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("F7"));
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		FileListing listing = mainWindow.getSource();
		String name = DialogFactory.showInputDialog(listing.getParent(),
				Translator.tr("Directory name:"));

		if (name == null)
			return;

		name = name.trim();
		if (name.isEmpty())
			return;

		String srcPath = listing.getPath();
		File file = new File(srcPath + File.separator + name);
		LOGGER.info(String.format("Creating directory '%s'", name));
		if (!file.mkdir()) {
			String errorMessage = String.format(Translator.tr("Directory '%s' could not be created."), name);
			DialogFactory.showErrorMessage(listing.getParent(), errorMessage);
			LOGGER.warning(errorMessage);
			return;
		}

		// Refresh the listing only if the creation succeeded.
		listing.refreshFiles();
		listing.focusOnFileName(name);
	}
}
