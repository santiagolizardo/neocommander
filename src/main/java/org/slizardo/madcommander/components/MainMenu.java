/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: MainMenu.java,v 1.8 2010/01/20 16:29:07 slizardo Exp $
 */
package org.slizardo.madcommander.components;

import javax.swing.Box;
import javax.swing.JMenuBar;

import org.slizardo.madcommander.MadCommander;
import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.menu.BookmarksMenu;
import org.slizardo.madcommander.menu.CommandsMenu;
import org.slizardo.madcommander.menu.ConfigurationMenu;
import org.slizardo.madcommander.menu.FilesMenu;
import org.slizardo.madcommander.menu.HelpMenu;
import org.slizardo.madcommander.menu.MarkMenu;
import org.slizardo.madcommander.menu.ShowMenu;


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
