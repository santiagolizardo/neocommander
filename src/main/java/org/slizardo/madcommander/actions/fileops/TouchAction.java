/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: TouchAction.java,v 1.3 2010/01/22 17:57:54 slizardo Exp $
 */
package org.slizardo.madcommander.actions.fileops;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.components.filelisting.FileListing;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;
import org.slizardo.madcommander.util.gui.DialogFactory;
import org.slizardo.madcommander.util.io.FileOperations;


public class TouchAction extends AbstractAction {

	public TouchAction() {
		super(Translator.text("Touch"), IconFactory.newIcon("touch.gif"));
	}

	public void actionPerformed(ActionEvent event) {
		FileListing listing = MainGUI.app.getSource();
		ArrayList<File> selectedFiles = listing.getSelectedFiles();
		for (File file : selectedFiles) {
			if (FileOperations.touch(file) == false) {
				DialogFactory.showErrorMessage(listing.getParent(), "File \""
						+ file.getName() + "\" cannot be touched.");
				return;
			}
		}

		listing.refreshFiles();
	}
}
