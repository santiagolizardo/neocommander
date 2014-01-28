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

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.components.filelisting.FileListing;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;

public class InterchangePanelsAction extends AbstractAction {

	private static final long serialVersionUID = 2374316824230143289L;

	private MadCommander mainWindow;

	public InterchangePanelsAction(MadCommander mainWindow) {
		super(Translator._("Interchange panels"));

		this.mainWindow = mainWindow;

		putValue(AbstractAction.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_MASK));
		putValue(AbstractAction.SMALL_ICON, IconFactory.newIcon("swap.png"));
	}

	public void actionPerformed(ActionEvent event) {
		FileListing sourceListing = mainWindow
				.getCurrentTab(mainWindow.leftTabs);
		FileListing destListing = mainWindow
				.getCurrentTab(mainWindow.rightTabs);

		String sourcePath = sourceListing.getPath();
		String destPath = destListing.getPath();

		sourceListing.setPath(destPath);
		destListing.setPath(sourcePath);
	}
}
