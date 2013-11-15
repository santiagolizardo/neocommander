/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions.fileops;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import com.santiagolizardo.madcommander.MainGUI;
import com.santiagolizardo.madcommander.components.filelisting.FileListing;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;

class ViewAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3940664656256749257L;

	public ViewAction() {
		super(Translator._("View"), IconFactory.newIcon("F3.gif"));

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("F3"));
	}

	public void actionPerformed(ActionEvent event) {
		FileListing listing = MainGUI.app.getSource();
		List<File> files = listing.getSelectedFiles();
		if (files.size() == 1) {
			File file = files.get(0);
			try {
				Desktop desktop = Desktop.getDesktop();
				desktop.open(file);
			} catch (Exception d) {
				DialogFactory.showErrorMessage(MainGUI.app,
						d.getLocalizedMessage());
			}
		}
	}
}
