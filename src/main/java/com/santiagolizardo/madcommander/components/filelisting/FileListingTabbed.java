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
package com.santiagolizardo.madcommander.components.filelisting;

import java.io.File;

import javax.swing.JTabbedPane;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.components.filelisting.FileListing.Position;

public class FileListingTabbed extends JTabbedPane {
	
	private static final long serialVersionUID = 6010265431790349455L;

	private Position panelPosition;

	private MainWindow mainWindow;

	public FileListingTabbed(MainWindow mainWindow, Position panelPosition) {
		super();

		this.panelPosition = panelPosition;

		this.mainWindow = mainWindow;

		addFileListingTab();
	}

	public void addFileListingTab() {
		FileListing fileListing = new FileListing(mainWindow, panelPosition);

		File file = new File(fileListing.getPath());
		addTab(file.getName(), fileListing);
	}

	public void removeCurrentTab() {
		remove(getSelectedIndex());
	}
}
