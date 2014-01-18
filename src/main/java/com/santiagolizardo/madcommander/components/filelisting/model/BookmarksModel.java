/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components.filelisting.model;

import java.util.List;

import javax.swing.AbstractListModel;


public class BookmarksModel extends AbstractListModel<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7176387701702459011L;

	private List<String> bookmarks;
	
	public BookmarksModel(List<String> bookmarks) {
		super();
		
		this.bookmarks = bookmarks;
	}
	
	public String getElementAt(int index) {
		return bookmarks.get(index);
	}

	public int getSize() {
		return bookmarks.size();
	}
	
	public List<String> getBookmarks() {
		return bookmarks;
	}
}
