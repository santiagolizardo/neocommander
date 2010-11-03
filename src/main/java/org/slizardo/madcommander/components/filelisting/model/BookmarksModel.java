/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: BookmarksModel.java,v 1.2 2006/03/21 17:25:36 slizardo Exp $
 */
package org.slizardo.madcommander.components.filelisting.model;

import javax.swing.AbstractListModel;

import org.slizardo.madcommander.MainGUI;


public class BookmarksModel extends AbstractListModel {

	public BookmarksModel() {
		super();
	}
	
	public Object getElementAt(int index) {
		return MainGUI.app.mainMenu.bookmarksMenu.bookmarks.get(index);
	}

	public int getSize() {
		return MainGUI.app.mainMenu.bookmarksMenu.bookmarks.size();
	}
	
	public void addBookmark(String bookmark) {
		MainGUI.app.mainMenu.bookmarksMenu.addBookmark(bookmark);
		fireIntervalAdded(bookmark, getSize()-1, 1);
	}
}
