/**
 * MadCommander http://www.madcommander.com/
 *
 * @author slizardo
 */
package com.santiagolizardo.madcommander.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.actions.GeneralActionFactory;
import com.santiagolizardo.madcommander.components.localized.LocalizedMenuItem;
import com.santiagolizardo.madcommander.dialogs.bookmarks.ManageBookmarks;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;

public class BookmarksMenu extends JMenu implements ActionListener {

	private static final long serialVersionUID = -8222925585381513984L;

	private JMenuItem addBookmarkMenuItem;
	private JMenuItem manageBookmarksMenuItem;

	private MadCommander mainWindow;

	public BookmarksMenu(MadCommander mainWindow) {
		super(Translator._("Bookmarks"));

		this.mainWindow = mainWindow;

		addBookmarkMenuItem = new JMenuItem(
				GeneralActionFactory.getAddToBookmarksAction(mainWindow));
		manageBookmarksMenuItem = new LocalizedMenuItem("Manage bookmarks");
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
