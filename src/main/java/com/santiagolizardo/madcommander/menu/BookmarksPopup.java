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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.santiagolizardo.madcommander.MainWindow;

public class BookmarksPopup extends JPopupMenu implements ActionListener {

	private static final long serialVersionUID = -3384212187171084919L;

	private MainWindow mainWindow;

	public BookmarksPopup(MainWindow mainWindow) {
		super();

		this.mainWindow = mainWindow;

		setLightWeightPopupEnabled(false);

		List<String> bookmarks = new ArrayList<>();
		for (String bookmark : bookmarks) {
			addBookmark(bookmark);
		}
	}

	private void addBookmark(String bookmark) {
		JMenuItem item = new JMenuItem(bookmark);
		item.addActionListener(this);
		add(item);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		String path = ((JMenuItem) source).getText();
		mainWindow.getSource().setPath(path);
	}
}
