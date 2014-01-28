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

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.components.filelisting.FileListing;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;

class CreateDirAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7734722213031842205L;

	private static final Logger LOGGER = Logger.getLogger(CreateDirAction.class
			.getName());
	private MainWindow mainWindow;

	public CreateDirAction(MainWindow mainWindow) {
		super(Translator._("Create dir"), IconFactory.newIcon("F7.gif"));

		this.mainWindow = mainWindow;

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("F7"));
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		FileListing listing = mainWindow.getSource();
		String name = DialogFactory.showInputDialog(listing.getParent(),
				"Directory name:");

		if (name == null)
			return;

		name = name.trim();
		if (name.isEmpty())
			return;

		String srcPath = listing.getPath();
		File file = new File(srcPath + File.separator + name);
		StringBuffer buffer = new StringBuffer();
		buffer.append("Creating directory [ ");
		buffer.append(name);
		buffer.append(" ]");
		LOGGER.info(buffer.toString());
		if (file.mkdir() == false) {
			StringBuilder errorBuffer = new StringBuilder();
			errorBuffer.append("Directory '");
			errorBuffer.append(name);
			errorBuffer.append("' could not be created!");
			DialogFactory.showErrorMessage(listing.getParent(),
					errorBuffer.toString());
			LOGGER.severe(errorBuffer.toString());
			return;
		}

		// Refresh the listing only if the creation succeeded.
		listing.refreshFiles();
	}
}
