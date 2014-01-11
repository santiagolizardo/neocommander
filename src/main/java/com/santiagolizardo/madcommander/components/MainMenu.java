/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components;

import java.io.File;
import java.util.List;

import javax.swing.Box;
import javax.swing.JMenuBar;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.menu.BookmarksMenu;
import com.santiagolizardo.madcommander.menu.CommandsMenu;
import com.santiagolizardo.madcommander.menu.ConfigurationMenu;
import com.santiagolizardo.madcommander.menu.FilesMenu;
import com.santiagolizardo.madcommander.menu.HelpMenu;
import com.santiagolizardo.madcommander.menu.MarkMenu;
import com.santiagolizardo.madcommander.menu.ShowMenu;

public class MainMenu extends JMenuBar {

	private static final long serialVersionUID = -8070437809650388843L;

	public ConfigurationMenu configurationMenu;

	private BookmarksMenu bookmarksMenu;

	private FilesMenu filesMenu;

	public MainMenu(MadCommander mainWindow) {
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
