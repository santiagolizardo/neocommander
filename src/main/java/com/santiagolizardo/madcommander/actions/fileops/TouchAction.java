/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions.fileops;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

import javax.swing.AbstractAction;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.components.filelisting.FileListing;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;
import com.santiagolizardo.madcommander.util.io.FileOperations;

public class TouchAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2847060359677737655L;

	private MadCommander mainWindow;

	public TouchAction(MadCommander mainWindow) {
		super(Translator._("Touch"), IconFactory.newIcon("touch.gif"));

		this.mainWindow = mainWindow;
	}

	public void actionPerformed(ActionEvent event) {
		FileListing listing = mainWindow.getSource();
		List<File> selectedFiles = listing.getSelectedFiles();
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
