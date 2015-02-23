/**
 * This file is part of MadCommander, a file manager with two panels.
 *
 * MadCommander is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MadCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MadCommander.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.components.filelisting;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.santiagolizardo.madcommander.MainWindow;

public class FileListingMouseListener extends MouseAdapter {

	private PopupMenu popupMenu;

	private MainWindow mainWindow;

	public FileListingMouseListener(final MainWindow mainWindow) {
		super();

		this.mainWindow = mainWindow;

		popupMenu = new PopupMenu(mainWindow);
	}

	public void mouseClicked(MouseEvent event) {
		if (event.getButton() == MouseEvent.BUTTON1
				&& event.getClickCount() == 2) {
			mainWindow.getSource().execute();
		}
	}

	public void mousePressed(MouseEvent event) {
		if (event.getButton() == MouseEvent.BUTTON3) {
			showPopup(event);
		}
	}

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
