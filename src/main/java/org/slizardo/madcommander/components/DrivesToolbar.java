/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: DrivesToolbar.java,v 1.6 2010/01/22 10:03:54 slizardo Exp $
 */
package org.slizardo.madcommander.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.components.filelisting.FileListing;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;
import org.slizardo.madcommander.util.gui.DialogFactory;


public class DrivesToolbar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4975350309638036942L;
	private static final Icon DRIVE_ICON = IconFactory.newIcon("drive.gif");

	public DrivesToolbar() {
		super();

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

		public void actionPerformed(ActionEvent event) {
			if(drive.canRead()) {
				FileListing listing = MainGUI.app.getSource();
				listing.setPath(absolutePath);
				listing.refreshFiles();
			} else {
				DialogFactory.showErrorMessage(MainGUI.app, Translator.text("Device_not_available"));
			}
		}
	}
}
