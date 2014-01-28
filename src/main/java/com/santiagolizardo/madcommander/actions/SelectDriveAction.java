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
package com.santiagolizardo.madcommander.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.components.filelisting.FileListing;
import com.santiagolizardo.madcommander.dialogs.SelectDrivePopup;

public class SelectDriveAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5388378675835343628L;

	private MainWindow mainWindow;

	public SelectDriveAction(MainWindow mainWindow) {
		super("SelectDriveAction");

		this.mainWindow = mainWindow;

		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		FileListing fileListing = mainWindow.getSource();
		SelectDrivePopup popup = new SelectDrivePopup(mainWindow);
		popup.show(fileListing, fileListing.getLocation().x,
				fileListing.getLocation().y);
	}
}
