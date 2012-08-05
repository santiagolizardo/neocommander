package org.slizardo.madcommander.actions.fileops;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;

import org.slizardo.madcommander.MadCommander;
import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;
import org.slizardo.madcommander.util.gui.DialogFactory;


public class CreateEmptyFileAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8545331012599545142L;
	private MadCommander mc;

	public CreateEmptyFileAction(MadCommander mc) {
		super(Translator.text("Create empty file..."), IconFactory
				.newIcon("page_white.png"));

		this.mc = mc;
	}

	public void actionPerformed(ActionEvent arg0) {
		mc = MainGUI.app;
		String fileName = DialogFactory.showInputDialog(mc, "File name:");
		String path = mc.getSource().getPath();
		File dir = new File(path, fileName);
		if (dir == null || dir.exists()) {
			DialogFactory.showErrorMessage(mc, "The file already exists.");
		} else {
			try {
				dir.createNewFile();
				mc.getSource().refreshFiles();
			} catch (Exception e) {
				DialogFactory.showErrorMessage(mc,
						"MadCommander could not create the file.\n"
								+ e.getMessage());
			}
		}
	}
}
