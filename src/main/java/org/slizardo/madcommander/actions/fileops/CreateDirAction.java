/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: CreateDirAction.java,v 1.3 2010/01/22 10:03:54 slizardo Exp $
 */
package org.slizardo.madcommander.actions.fileops;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.components.filelisting.FileListing;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;
import org.slizardo.madcommander.util.gui.DialogFactory;


class CreateDirAction extends AbstractAction {

	private Logger logger = Logger.getLogger("CreateDirectoryAction");
	
	public CreateDirAction() {
		super(Translator.text("Create_dir"), IconFactory.newIcon("F7.gif"));
		
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("F7"));
	}

	public void actionPerformed(ActionEvent event) {
		FileListing listing = MainGUI.app.getSource();
		String srcPath = listing.getPath();
		String name = DialogFactory.showInputDialog(listing.getParent(),
				"Directory name:");

		if (name != null) {
			File file = new File(srcPath + File.separator + name);
			StringBuffer buffer = new StringBuffer();
			buffer.append("Creating directory [ ");
			buffer.append(name);
			buffer.append(" ]");
			logger.info(buffer.toString());
			if (file.mkdir() == false) {
				StringBuffer errorBuffer = new StringBuffer();
				errorBuffer.append("Directory '");
				errorBuffer.append(name);
				errorBuffer.append("' cannot be created!");
				DialogFactory.showErrorMessage(listing.getParent(), errorBuffer
						.toString());
				logger.severe(errorBuffer.toString());
			}
		}

		listing.refreshFiles();
	}
}
