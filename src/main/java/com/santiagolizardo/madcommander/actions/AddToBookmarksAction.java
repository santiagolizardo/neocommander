/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import com.santiagolizardo.madcommander.MainGUI;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;

class AddToBookmarksAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6038164265710205238L;

	public AddToBookmarksAction() {
		super(Translator._("Add_to_bookmarks"), IconFactory
				.newIcon("add_to_bookmarks.png"));

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl B"));
	}

	public void actionPerformed(ActionEvent event) {
		String path = MainGUI.app.getSource().getPath();
		MainGUI.app.mainMenu.bookmarksMenu.addBookmark(path);
	}
}
