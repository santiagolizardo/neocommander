package com.santiagolizardo.madcommander.actions.fileops;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;

public class CreateEmptyFileAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8545331012599545142L;
	private MadCommander mainWindow;

	public CreateEmptyFileAction(MadCommander mc) {
		super(Translator._("Create empty file..."), IconFactory
				.newIcon("page_white.png"));

		this.mainWindow = mc;
	}

	public void actionPerformed(ActionEvent arg0) {
		String fileName = DialogFactory.showInputDialog(mainWindow,
				"File name:");
		String path = mainWindow.getSource().getPath();
		File dir = new File(path, fileName);
		if (dir == null || dir.exists()) {
			DialogFactory.showErrorMessage(mainWindow,
					"The file already exists.");
		} else {
			try {
				dir.createNewFile();
				mainWindow.getSource().refreshFiles();
			} catch (Exception e) {
				DialogFactory.showErrorMessage(
						mainWindow,
						"MadCommander could not create the file.\n"
								+ e.getMessage());
			}
		}
	}
}
