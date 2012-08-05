package org.slizardo.madcommander.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.slizardo.madcommander.MadCommander;
import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.components.filelisting.FileListing;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;


public class InterchangePanelsAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2374316824230143289L;

	public InterchangePanelsAction() {
		super(Translator.text("Interchange_panels"));

		putValue(AbstractAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke(
				KeyEvent.VK_U, KeyEvent.CTRL_MASK));
		putValue(AbstractAction.SMALL_ICON, IconFactory.newIcon("swap.png"));
	}

	public void actionPerformed(ActionEvent event) {
		MadCommander app = MainGUI.app;
		FileListing sourceListing = app.getCurrentTab(MainGUI.app.leftTabs);
		FileListing destListing = app.getCurrentTab(MainGUI.app.rightTabs);

		String sourcePath = sourceListing.getPath();
		String destPath = destListing.getPath();

		sourceListing.setPath(destPath);
		destListing.setPath(sourcePath);
	}
}
