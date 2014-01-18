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

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;

class AddToBookmarksAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6038164265710205238L;

	private MadCommander mainWindow;

	public AddToBookmarksAction(MadCommander mainWindow) {
		super(Translator._("Add to bookmarks"), IconFactory
				.newIcon("add_to_bookmarks.png"));

		this.mainWindow = mainWindow;

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl B"));
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		String path = mainWindow.getSource().getPath();
		mainWindow.getConfigData().getBookmarks().add(path);
		mainWindow.getMainMenu().getBookmarksMenu().refreshList();
	}
}
