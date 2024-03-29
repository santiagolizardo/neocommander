/*
  This file is part of MadCommander, a file manager with two panels.

  MadCommander is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  MadCommander is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with MadCommander.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.components.filelisting;

import com.santiagolizardo.madcommander.MainWindow;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FileListingMouseListener extends MouseAdapter {

	private final PopupMenu popupMenu;

	private final MainWindow mainWindow;

	public FileListingMouseListener(final MainWindow mainWindow) {
		super();

		this.mainWindow = mainWindow;

		popupMenu = new PopupMenu(mainWindow);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if (event.getButton() == MouseEvent.BUTTON1
				&& event.getClickCount() == 2) {
			mainWindow.getSource().execute();
		}
	}

	@Override
	public void mousePressed(MouseEvent event) {
		if (event.getButton() == MouseEvent.BUTTON3) {
			showPopup(event);
		}
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		if (event.getButton() == MouseEvent.BUTTON3) {
			showPopup(event);
		}
	}

	private void showPopup(MouseEvent event) {
		if (event.isPopupTrigger()) {
			popupMenu.show(event.getComponent(), event.getX(), event.getY());
		}
	}
}
