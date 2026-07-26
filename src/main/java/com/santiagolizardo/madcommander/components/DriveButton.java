/*
 * Copyright (C) 2026 Santiago Lizardo
 *
 * This file is part of NeoCommander.
 *
 * NeoCommander is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NeoCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DriveButton extends JButton implements ActionListener {


	private static final Icon DRIVE_ICON = IconFactory.newIcon("drive.gif");
	
	private final File drive;
	private final String absolutePath;
	
	private final MainWindow mainWindow;

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

