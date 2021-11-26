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
package com.santiagolizardo.madcommander.menu;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.actions.GeneralActionFactory;
import com.santiagolizardo.madcommander.dialogs.bookmarks.ManageBookmarks;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookmarksMenu extends JMenu implements ActionListener {


	private final JMenuItem addBookmarkMenuItem;
	private final JMenuItem manageBookmarksMenuItem;

	private final MainWindow mainWindow;

	public BookmarksMenu(MainWindow mainWindow) {
		super(Translator.tr("Bookmarks"));

		this.mainWindow = mainWindow;

		addBookmarkMenuItem = new JMenuItem(
				GeneralActionFactory.getAddToBookmarksAction(mainWindow));
		manageBookmarksMenuItem = new JMenuItem(Translator.tr("Manage bookmarks"));
		manageBookmarksMenuItem.setIcon(IconFactory.newIcon("manage_bookmarks.png"));
		manageBookmarksMenuItem.addActionListener(this);

		add(addBookmarkMenuItem);
		add(manageBookmarksMenuItem);
		
		refreshList();
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		Object source = ev.getSource();
		if (source == manageBookmarksMenuItem) {
			ManageBookmarks manageBookmarks = new ManageBookmarks(mainWindow);
			manageBookmarks.setVisible(true);
		} else {
			String path = ((JMenuItem) source).getText();
			mainWindow.getSource().setPath(path);
		}
	}

	public void refreshList() {
		// Remove all previous entries.
		int numMenuItems = getMenuComponentCount();
		if( numMenuItems > 2 ) {
			for( int i = numMenuItems - 1; i > 1; i--) {
				remove(i);
			}
		}
		
		if (mainWindow.getConfigData().getBookmarks().isEmpty()) {
			return;
		}

		addSeparator();
		for (String bookmark : mainWindow.getConfigData().getBookmarks()) {
			addBookmarkItem(bookmark);
		}
	}

	private void addBookmarkItem(String bookmark) {
		JMenuItem item = new JMenuItem(bookmark);
		item.addActionListener(this);
		add(item);
	}
}
