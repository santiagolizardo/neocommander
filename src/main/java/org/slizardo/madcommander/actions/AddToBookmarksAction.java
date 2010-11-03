/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: AddToBookmarksAction.java,v 1.2 2010/01/22 10:03:53 slizardo Exp $
 */
package org.slizardo.madcommander.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;


class AddToBookmarksAction extends AbstractAction {

	public AddToBookmarksAction() {
		super(Translator.text("Add_to_bookmarks"), IconFactory
				.newIcon("add_to_bookmarks.png"));
		
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl B"));
	}

	public void actionPerformed(ActionEvent event) {
		String path = MainGUI.app.getSource().getPath();
		MainGUI.app.mainMenu.bookmarksMenu.addBookmark(path);
	}
}
