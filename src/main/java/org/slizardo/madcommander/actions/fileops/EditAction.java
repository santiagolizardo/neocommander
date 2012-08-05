/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: EditAction.java,v 1.5 2010/01/22 17:57:54 slizardo Exp $
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

class EditAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -982849699897975067L;

	public EditAction() {
		super(Translator.text("Edit"), IconFactory.newIcon("F4.gif"));

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("F4"));
	}

	public void actionPerformed(ActionEvent event) {
		FileListing listing = MainGUI.app.getSource();
		List<File> files = listing.getSelectedFiles();
		if (files.size() == 1) {
			File file = files.get(0);
			if (Desktop.getDesktop().isSupported(Desktop.Action.EDIT)) {

				try {
					Desktop desktop = Desktop.getDesktop();
					desktop.edit(file);
				} catch (Exception e) {
					DialogFactory.showErrorMessage(listing.getParent(),
							e.getMessage());
				}
			} else {
				DialogFactory.showErrorMessage(listing.getParent(),
						"The file isn't editable");
			}
		} else {
			DialogFactory.showErrorMessage(listing.getParent(),
					"You cannot edit more than one file at the same time!");
		}
	}
}
