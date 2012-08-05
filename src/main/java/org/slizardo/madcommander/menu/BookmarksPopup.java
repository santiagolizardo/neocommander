/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: BookmarksPopup.java,v 1.5 2010/01/21 17:02:48 slizardo Exp $
 */
package org.slizardo.madcommander.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.slizardo.madcommander.MainGUI;

public class BookmarksPopup extends JPopupMenu implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3384212187171084919L;

	public BookmarksPopup() {
		super();

		setLightWeightPopupEnabled(false);

		List<String> bookmarks = new ArrayList<String>();//.app.mainMenu.bookmarksMenu.bookmarks;
		for (String bookmark : bookmarks) {
			addBookmark(bookmark);
		}
	}

	private void addBookmark(String bookmark) {
		JMenuItem item = new JMenuItem(bookmark);
		item.addActionListener(this);
		add(item);
	}

	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		String path = ((JMenuItem) source).getText();
		MainGUI.app.getSource().setPath(path);
	}
}
