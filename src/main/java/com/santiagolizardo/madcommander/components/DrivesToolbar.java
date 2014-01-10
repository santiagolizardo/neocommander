/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.components.filelisting.FileListing;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;

public class DrivesToolbar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4975350309638036942L;
	private static final Icon DRIVE_ICON = IconFactory.newIcon("drive.gif");

	private MadCommander mainWindow;

	public DrivesToolbar(MadCommander mainWindow) {
		super();

		this.mainWindow = mainWindow;

		setRollover(true);

		File[] drives = File.listRoots();
		assert drives != null;
		for (File drive : drives) {
			add(new FileButton(drive));
		}
	}

	private class FileButton extends JButton implements ActionListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = -8679110634791181972L;
		private File drive;
		private String absolutePath;

		public FileButton(File drive) {
			super();

			this.drive = drive;
			this.absolutePath = drive.getAbsolutePath();

			setText(absolutePath);
			setIcon(DRIVE_ICON);
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent ev) {
			if (drive.canRead()) {
				FileListing listing = mainWindow.getSource();
				listing.setPath(absolutePath);
				listing.refreshFiles();
			} else {
				DialogFactory.showErrorMessage(mainWindow,
						Translator._("Device not available"));
			}
		}
	}
}
