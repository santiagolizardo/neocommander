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

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.components.filelisting.FileListing.Position;
import com.santiagolizardo.madcommander.menu.BookmarksPopup;

public class FLKeyListener extends KeyAdapter {

	private MainWindow mainWindow;

	public FLKeyListener(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		final int modifiers = event.getModifiers();
		final int keyCode = event.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_HOME: {
			FileListing fl = mainWindow.getSource();
			fl.selectFirstEntry();
		}
			break;
		case KeyEvent.VK_END: {
			FileListing fl = mainWindow.getSource();
			fl.selectLastEntry();
		}
			break;
		case KeyEvent.VK_ASTERISK:
			mainWindow.getSource().invertSelection();
			break;
		case KeyEvent.VK_ENTER:
			mainWindow.getSource().execute();
			break;
		case KeyEvent.VK_TAB:
			if (modifiers == KeyEvent.CTRL_MASK) {

			} else {
				mainWindow.currentPanel = (mainWindow.currentPanel == Position.Left ? Position.Right
						: Position.Left);
				mainWindow.getSource().requestFocus();
			}
			break;
		default:
			super.keyPressed(event);
			if (modifiers == KeyEvent.CTRL_MASK) {
				if (keyCode == KeyEvent.VK_D) {
					BookmarksPopup popup = new BookmarksPopup(mainWindow);
					popup.show(event.getComponent(), event.getComponent()
							.getX(), event.getComponent().getY());
				} else if (keyCode == KeyEvent.VK_T) {
					if (mainWindow.currentPanel == Position.Left) {
						mainWindow.leftTabs.addFileListingTab();
					} else {
						mainWindow.rightTabs.addFileListingTab();
					}
				} else if (keyCode == KeyEvent.VK_W) {
					if (mainWindow.currentPanel == Position.Left) {
						mainWindow.leftTabs.removeCurrentTab();
					} else {
						mainWindow.rightTabs.removeCurrentTab();
					}
				}
			} else {
				FileListing fl = mainWindow.getSource();
				fl.focusByString(String.format("%d", keyCode));
			}
			break;
		}
	}
}
