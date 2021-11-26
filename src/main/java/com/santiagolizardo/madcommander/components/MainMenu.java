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
package com.santiagolizardo.madcommander.components;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.menu.*;

import javax.swing.*;
import java.io.File;
import java.util.List;

public class MainMenu extends JMenuBar {


	public ConfigurationMenu configurationMenu;

	private final BookmarksMenu bookmarksMenu;

	private final FilesMenu filesMenu;

	public MainMenu(MainWindow mainWindow) {
		super();

		filesMenu = new FilesMenu(mainWindow);
		add(filesMenu);
		MarkMenu markMenu = new MarkMenu(mainWindow);
		add(markMenu);
		CommandsMenu commandsMenu = new CommandsMenu(mainWindow);
		add(commandsMenu);
		ShowMenu showMenu = new ShowMenu(mainWindow);
		add(showMenu);
		configurationMenu = new ConfigurationMenu(mainWindow);
		add(configurationMenu);
		bookmarksMenu = new BookmarksMenu(mainWindow);
		add(bookmarksMenu);
		add(Box.createHorizontalGlue());
		HelpMenu helpMenu = new HelpMenu(mainWindow);
		add(helpMenu);
	}

	public void refreshButtons(List<File> selectedFiles) {
		filesMenu.refreshButtons(selectedFiles);
	}

	public BookmarksMenu getBookmarksMenu() {
		return bookmarksMenu;
	}
}
