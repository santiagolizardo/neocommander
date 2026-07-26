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

package com.santiagolizardo.madcommander.resources.images;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.net.URL;
import java.util.logging.Logger;

public class IconFactory {

	private static final Logger logger = Logger.getLogger(IconFactory.class.getName());

	private static final FileSystemView fileSystemView;

	static {
		fileSystemView = FileSystemView.getFileSystemView();
	}

	public static ImageIcon newIcon(String name) {
		URL url = IconFactory.class.getResource(name);
		if (url == null) {
			return null;
		}

		return new ImageIcon(url);
	}

	public static Icon getIconForFile(File file) {
		return fileSystemView.getSystemIcon(file);
	}
}
