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

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;

class AddToBookmarksAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6038164265710205238L;

	private MainWindow mainWindow;

	public AddToBookmarksAction(MainWindow mainWindow) {
		super(Translator._("Add to bookmarks"), IconFactory
				.newIcon("add_to_bookmarks.png"));

		this.mainWindow = mainWindow;

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl B"));
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		String path = mainWindow.getSource().getPath();
		mainWindow.getConfigData().getBookmarks().add(path);
		mainWindow.getMainMenu().getBookmarksMenu().refreshList();
	}
}
