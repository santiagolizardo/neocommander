/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components.filelisting.model;

import java.util.List;

import javax.swing.AbstractListModel;


public class BookmarksModel extends AbstractListModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7176387701702459011L;

	private List<String> bookmarks;
	
	public BookmarksModel(List<String> bookmarks) {
		super();
		
		this.bookmarks = bookmarks;
	}
	
	public Object getElementAt(int index) {
		return bookmarks.get(index);
	}

	public int getSize() {
		return bookmarks.size();
	}
	
	public void addBookmark(String bookmark) {
		//MainGUI.app.mainMenu.bookmarksMenu.addBookmark(bookmark);
		fireIntervalAdded(bookmark, getSize()-1, 1);
	}
}
