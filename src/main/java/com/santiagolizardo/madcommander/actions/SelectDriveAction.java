/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.components.filelisting.FileListing;
import com.santiagolizardo.madcommander.dialogs.SelectDrivePopup;

public class SelectDriveAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5388378675835343628L;

	private MadCommander mainWindow;

	public SelectDriveAction(MadCommander mainWindow) {
		super("SelectDriveAction");

		this.mainWindow = mainWindow;

		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_MASK));
	}

	public void actionPerformed(ActionEvent event) {
		FileListing fileListing = mainWindow.getSource();
		SelectDrivePopup popup = new SelectDrivePopup(mainWindow);
		popup.show(fileListing, fileListing.getLocation().x,
				fileListing.getLocation().y);
	}
}
