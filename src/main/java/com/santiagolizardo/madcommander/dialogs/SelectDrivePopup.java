/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.santiagolizardo.madcommander.MadCommander;
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

	public SelectDrivePopup(MadCommander mainWindow) {
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

		private MadCommander mainWindow;

		public FileButton(MadCommander mainWindow, File drive) {
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
						Translator._("Device_not_available"));
			}
		}
	}
}
