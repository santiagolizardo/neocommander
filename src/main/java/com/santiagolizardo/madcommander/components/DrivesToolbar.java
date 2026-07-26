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

package com.santiagolizardo.madcommander.components;

import com.santiagolizardo.madcommander.MainWindow;

import javax.swing.*;
import java.io.File;

public class DrivesToolbar extends JToolBar {

	public DrivesToolbar(MainWindow mainWindow) {
		super();

		setRollover(true);

		File[] drives = File.listRoots();
		assert drives != null;
		for (File drive : drives) {
			add(new DriveButton(drive, mainWindow));
		}
	}
}
