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

import com.santiagolizardo.madcommander.MainGUI;
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

	public BookmarksMenu() {
		super(Translator._("Bookmarks"));

		addBookmark = new JMenuItem(
				GeneralActionFactory.getAddToBookmarksAction());
		manageBookmarks = new LocalizedMenuItem("Manage_bookmarks");
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

	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == manageBookmarks) {
			ManageBookmarks manageBookmarks = new ManageBookmarks();
			manageBookmarks.setVisible(true);
		} else {
			String path = ((JMenuItem) source).getText();
			MainGUI.app.getSource().setPath(path);
		}
	}
}
