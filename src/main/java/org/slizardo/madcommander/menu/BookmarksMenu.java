/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: BookmarksMenu.java,v 1.11 2006/03/21 17:25:35 slizardo Exp $
 */
package org.slizardo.madcommander.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.actions.GeneralActionFactory;
import org.slizardo.madcommander.components.localized.LocalizedMenuItem;
import org.slizardo.madcommander.config.ConfigWrapper;
import org.slizardo.madcommander.dialogs.bookmarks.ManageBookmarks;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;


public class BookmarksMenu extends JMenu implements ActionListener {

	public ArrayList<String> bookmarks;

	private JMenuItem addBookmark;

	private JMenuItem manageBookmarks;

	public BookmarksMenu() {
		super(Translator.text("Bookmarks"));

		addBookmark = new JMenuItem(GeneralActionFactory.getAddToBookmarksAction());
		manageBookmarks = new LocalizedMenuItem("Manage_bookmarks");
		manageBookmarks.setIcon(IconFactory.newIcon("manage_bookmarks.png"));
		manageBookmarks.addActionListener(this);

		bookmarks = new ArrayList<String>();

		add(addBookmark);
		add(manageBookmarks);
	}

	public void loadProperties() {
		String[] bookmarks = ConfigWrapper.getProperties("bookmarks");
		for (String bookmark : bookmarks) {
			File file = new File(bookmark);
			if (file.exists()) {
				addBookmark(bookmark);
			}
		}
	}

	public void saveProperties() {
		ConfigWrapper.setProperties("bookmarks", bookmarks
				.toArray(new String[] {}));
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
