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
import java.util.List;

import javax.swing.AbstractAction;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.components.filelisting.FileListing;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;
import com.santiagolizardo.madcommander.util.io.FileOperations;

public class TouchAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2847060359677737655L;

	private MainWindow mainWindow;

	public TouchAction(MainWindow mainWindow) {
		super(Translator._("Touch"), IconFactory.newIcon("touch.gif"));

		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		FileListing listing = mainWindow.getSource();
		List<File> selectedFiles = listing.getSelectedFiles();
		for (File file : selectedFiles) {
			if (FileOperations.touch(file) == false) {
				DialogFactory.showErrorMessage(listing.getParent(), "File \""
						+ file.getName() + "\" cannot be touched.");
				return;
			}
		}

		listing.refreshFiles();
	}
}
