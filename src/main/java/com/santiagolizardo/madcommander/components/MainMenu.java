/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components;

import javax.swing.Box;
import javax.swing.JMenuBar;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.MainGUI;
import com.santiagolizardo.madcommander.menu.BookmarksMenu;
import com.santiagolizardo.madcommander.menu.CommandsMenu;
import com.santiagolizardo.madcommander.menu.ConfigurationMenu;
import com.santiagolizardo.madcommander.menu.FilesMenu;
import com.santiagolizardo.madcommander.menu.HelpMenu;
import com.santiagolizardo.madcommander.menu.MarkMenu;
import com.santiagolizardo.madcommander.menu.ShowMenu;


public class MainMenu extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8070437809650388843L;

	public ConfigurationMenu configurationMenu;

	public BookmarksMenu bookmarksMenu;

	public MainMenu() {
		super();

		MadCommander app = MainGUI.app;

		FilesMenu filesMenu = new FilesMenu();
		add(filesMenu);
		MarkMenu markMenu = new MarkMenu();
		add(markMenu);
		CommandsMenu commandsMenu = new CommandsMenu();
		add(commandsMenu);
		ShowMenu showMenu = new ShowMenu();
		add(showMenu);
		configurationMenu = new ConfigurationMenu(app);
		add(configurationMenu);
		bookmarksMenu = new BookmarksMenu();
		add(bookmarksMenu);
		add(Box.createHorizontalGlue());
		HelpMenu helpMenu = new HelpMenu();
		add(helpMenu);
	}
}
