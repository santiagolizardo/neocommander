/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: FLMouseListener.java,v 1.1 2010/01/21 17:02:45 slizardo Exp $
 */
package org.slizardo.madcommander.components.filelisting;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.slizardo.madcommander.controller.Controller;


public class FLMouseListener extends MouseAdapter {

	private PopupMenu popupMenu;

	public FLMouseListener() {
		super();

		popupMenu = new PopupMenu();
	}

	public void mouseClicked(MouseEvent event) {
		if (event.getButton() == MouseEvent.BUTTON1
				&& event.getClickCount() == 2) {
			Controller.execute();
		}
	}

	public void mousePressed(MouseEvent event) {
		super.mousePressed(event);

		if (event.getButton() == MouseEvent.BUTTON3) {
			showPopup(event);
		}
	}

	public void mouseReleased(MouseEvent event) {
		super.mouseReleased(event);

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
