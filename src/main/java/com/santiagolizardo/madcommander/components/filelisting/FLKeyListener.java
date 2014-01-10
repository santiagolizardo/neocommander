/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components.filelisting;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.components.filelisting.FileListing.Position;
import com.santiagolizardo.madcommander.menu.BookmarksPopup;

public class FLKeyListener extends KeyAdapter {

	private MadCommander mainWindow;

	public FLKeyListener(MadCommander mainWindow) {
		this.mainWindow = mainWindow;
	}

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
				fl.focusByString(String.format("%c", keyCode));
			}
			break;
		}
	}
}
