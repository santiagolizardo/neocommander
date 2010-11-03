/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: FLKeyListener.java,v 1.2 2010/01/26 17:57:11 slizardo Exp $
 */
package org.slizardo.madcommander.components.filelisting;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.components.filelisting.FileListing.Position;
import org.slizardo.madcommander.controller.Controller;
import org.slizardo.madcommander.menu.BookmarksPopup;


public class FLKeyListener extends KeyAdapter {

	public void keyPressed(KeyEvent event) {
		final int modifiers = event.getModifiers();
		final int keyCode = event.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_HOME: {
			FileListing fl = MainGUI.app.getSource();
			fl.selectFirstEntry();
		}
			break;
		case KeyEvent.VK_END: {
			FileListing fl = MainGUI.app.getSource();
			fl.selectLastEntry();
		}
			break;
		case KeyEvent.VK_ASTERISK:
			Controller.invertSelection();
			break;
		case KeyEvent.VK_ENTER:
			Controller.execute();
			break;
		case KeyEvent.VK_TAB:
			if (modifiers == KeyEvent.CTRL_MASK) {

			} else {
				Controller.swapPanels();
			}
			break;
		default:
			super.keyPressed(event);
			if (modifiers == KeyEvent.CTRL_MASK) {
				if (keyCode == KeyEvent.VK_D) {
					BookmarksPopup popup = new BookmarksPopup();
					popup.show(event.getComponent(), event.getComponent()
							.getX(), event.getComponent().getY());
				} else if (keyCode == KeyEvent.VK_T) {
					if (MainGUI.app.currentPanel == Position.Left) {
						MainGUI.app.leftTabs.addFileListingTab();
					} else {
						MainGUI.app.rightTabs.addFileListingTab();
					}
				} else if (keyCode == KeyEvent.VK_W) {
					if (MainGUI.app.currentPanel == Position.Left) {
						MainGUI.app.leftTabs.removeCurrentTab();
					} else {
						MainGUI.app.rightTabs.removeCurrentTab();
					}
				}
			} else {
				FileListing fl = MainGUI.app.getSource();
				fl.focusByString(String.format("%c", keyCode));
			}
			break;
		}
	}
}
