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
package com.santiagolizardo.madcommander.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.components.filelisting.FileListing;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;

public class SelectDrivePopup extends JPopupMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4959814209723911694L;
	private static final Icon DRIVE_ICON = IconFactory.newIcon("drive.gif");

	public SelectDrivePopup(MainWindow mainWindow) {
		super();

		File[] drives = File.listRoots();
		assert drives != null;
		for (File drive : drives) {
			add(new FileButton(mainWindow, drive));
		}
	}

	private class FileButton extends JMenuItem implements ActionListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = -694233547831994294L;
		private File drive;
		private String absolutePath;

		private MainWindow mainWindow;

		public FileButton(MainWindow mainWindow, File drive) {
			super();

			this.mainWindow = mainWindow;

			this.drive = drive;
			this.absolutePath = drive.getAbsolutePath();

			setText(absolutePath);
			setIcon(SelectDrivePopup.DRIVE_ICON);
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent event) {
			if (drive.canRead()) {
				FileListing listing = mainWindow.getSource();
				listing.setPath(absolutePath);
				listing.refreshFiles();
			} else {
				DialogFactory.showErrorMessage(mainWindow,
						Translator.tr("Device_not_available"));
			}
		}
	}
}
