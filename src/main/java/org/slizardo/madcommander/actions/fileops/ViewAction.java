/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: ViewAction.java,v 1.5 2010/01/22 17:57:54 slizardo Exp $
 */
package org.slizardo.madcommander.actions.fileops;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.components.filelisting.FileListing;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;
import org.slizardo.madcommander.util.gui.DialogFactory;

class ViewAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3940664656256749257L;

	public ViewAction() {
		super(Translator.text("View"), IconFactory.newIcon("F3.gif"));

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
