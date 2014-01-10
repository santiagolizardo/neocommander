/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.actions.GeneralActionFactory;
import com.santiagolizardo.madcommander.components.localized.LocalizedMenuItem;
import com.santiagolizardo.madcommander.dialogs.bookmarks.ManageBookmarks;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;

public class BookmarksMenu extends JMenu implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8222925585381513984L;

	public List<String> bookmarks;

	private JMenuItem addBookmark;

	private JMenuItem manageBookmarks;

	private MadCommander mainWindow;

	public BookmarksMenu(MadCommander mainWindow) {
		super(Translator._("Bookmarks"));

		this.mainWindow = mainWindow;

		addBookmark = new JMenuItem(
				GeneralActionFactory.getAddToBookmarksAction(mainWindow));
		manageBookmarks = new LocalizedMenuItem("Manage bookmarks");
		manageBookmarks.setIcon(IconFactory.newIcon("manage_bookmarks.png"));
		manageBookmarks.addActionListener(this);

		bookmarks = new ArrayList<String>();

		add(addBookmark);
		add(manageBookmarks);
	}

	public void addBookmark(String bookmark) {
		if (!bookmarks.contains(bookmark)) {
			JMenuItem item = new JMenuItem(bookmark);
			item.addActionListener(this);
			if (bookmarks.size() == 0) {
				addSeparator();
			}
			add(item);
			bookmarks.add(bookmark);
		}
	}

	public void actionPerformed(ActionEvent ev) {
		Object source = ev.getSource();
		if (source == manageBookmarks) {
			ManageBookmarks manageBookmarks = new ManageBookmarks(mainWindow);
			manageBookmarks.setVisible(true);
		} else {
			String path = ((JMenuItem) source).getText();
			mainWindow.getSource().setPath(path);
		}
	}
}
