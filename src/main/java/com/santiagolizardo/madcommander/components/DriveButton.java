/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santiagolizardo.madcommander.components;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.components.filelisting.FileListing;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Icon;
import javax.swing.JButton;

public class DriveButton extends JButton implements ActionListener {

	private static final long serialVersionUID = -8679110634791181972L;

	private static final Icon DRIVE_ICON = IconFactory.newIcon("drive.gif");
	
	private File drive;
	private String absolutePath;
	
	private MainWindow mainWindow;

	public DriveButton(File drive, MainWindow mainWindow) {
		super();

		this.drive = drive;
		this.absolutePath = drive.getAbsolutePath();

		setText(absolutePath);
		setIcon(DRIVE_ICON);
		addActionListener(this);
		
		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		if (drive.canRead()) {
			FileListing listing = mainWindow.getSource();
			listing.setPath(absolutePath);
			listing.refreshFiles();
		} else {
			DialogFactory.showErrorMessage(mainWindow,
					Translator.tr("Device not available"));
		}
	}
}

