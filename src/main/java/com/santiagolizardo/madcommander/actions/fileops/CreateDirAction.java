/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions.fileops;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.components.filelisting.FileListing;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;

class CreateDirAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7734722213031842205L;

	private static final Logger LOGGER = Logger.getLogger(CreateDirAction.class
			.getName());
	private MadCommander mainWindow;

	public CreateDirAction(MadCommander mainWindow) {
		super(Translator._("Create_dir"), IconFactory.newIcon("F7.gif"));

		this.mainWindow = mainWindow;

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("F7"));
	}

	public void actionPerformed(ActionEvent event) {
		FileListing listing = mainWindow.getSource();
		String name = DialogFactory.showInputDialog(listing.getParent(),
				"Directory name:");

		if (name == null)
			return;

		name = name.trim();
		if (name.isEmpty())
			return;

		String srcPath = listing.getPath();
		File file = new File(srcPath + File.separator + name);
		StringBuffer buffer = new StringBuffer();
		buffer.append("Creating directory [ ");
		buffer.append(name);
		buffer.append(" ]");
		LOGGER.info(buffer.toString());
		if (file.mkdir() == false) {
			StringBuffer errorBuffer = new StringBuffer();
			errorBuffer.append("Directory '");
			errorBuffer.append(name);
			errorBuffer.append("' could not be created!");
			DialogFactory.showErrorMessage(listing.getParent(),
					errorBuffer.toString());
			LOGGER.severe(errorBuffer.toString());
			return;
		}

		// Refresh the listing only if the creation succeeded.
		listing.refreshFiles();
	}
}
