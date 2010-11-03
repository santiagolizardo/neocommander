/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: SelectDrivePopup.java,v 1.3 2010/01/22 10:03:54 slizardo Exp $
 */
package org.slizardo.madcommander.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.components.filelisting.FileListing;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;
import org.slizardo.madcommander.util.gui.DialogFactory;


public class SelectDrivePopup extends JPopupMenu {

	private static final Icon DRIVE_ICON = IconFactory.newIcon("drive.gif");

	public SelectDrivePopup() {
		super();

		File[] drives = File.listRoots();
		assert drives != null;
		for (File drive : drives) {
			add(new FileButton(drive));
		}
	}

	private class FileButton extends JMenuItem implements ActionListener {

		private File drive;
		private String absolutePath;

		public FileButton(File drive) {
			super();

			this.drive = drive;
			this.absolutePath = drive.getAbsolutePath();

			setText(absolutePath);
			setIcon(SelectDrivePopup.DRIVE_ICON);
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent event) {
			if (drive.canRead()) {
				FileListing listing = MainGUI.app.getSource();
				listing.setPath(absolutePath);
				listing.refreshFiles();
			} else {
				DialogFactory.showErrorMessage(MainGUI.app, Translator
						.text("Device_not_available"));
			}
		}
	}
}
