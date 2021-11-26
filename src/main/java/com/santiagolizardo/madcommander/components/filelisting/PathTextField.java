/*
  This file is part of MadCommander, a file manager with two panels.

  MadCommander is free software: you can redistribute it and/or modify it under
  the terms of the GNU General Public License as published by the Free Software
  Foundation, either version 3 of the License, or (at your option) any later
  version.

  MadCommander is distributed in the hope that it will be useful, but WITHOUT
  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
  FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
  details.

  You should have received a copy of the GNU General Public License along with
  MadCommander. If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.components.filelisting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

public class PathTextField extends JTextField {

	private final FileListing fileListing;

	public PathTextField(FileListing fileListing) {
		super(30);

		this.fileListing = fileListing;
		
		Dimension preferredSize = getPreferredSize();
		setMaximumSize(new Dimension(Integer.MAX_VALUE, preferredSize.height));
		
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent ev) {
				if (ev.getKeyCode() == KeyEvent.VK_ENTER) {
					String newPathString = getText();
					File newPath = new File(newPathString);
					if (newPath.isDirectory()) {
						fileListing.setPath(newPathString);
					} else {
						setText(fileListing.getPath());
					}
				}
			}
		});
	}
}
