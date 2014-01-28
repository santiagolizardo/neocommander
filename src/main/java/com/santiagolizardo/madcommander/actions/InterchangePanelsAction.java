package com.santiagolizardo.madcommander.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.components.filelisting.FileListing;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;

public class InterchangePanelsAction extends AbstractAction {

	private static final long serialVersionUID = 2374316824230143289L;

	private MadCommander mainWindow;

	public InterchangePanelsAction(MadCommander mainWindow) {
		super(Translator._("Interchange panels"));

		this.mainWindow = mainWindow;

		putValue(AbstractAction.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_MASK));
		putValue(AbstractAction.SMALL_ICON, IconFactory.newIcon("swap.png"));
	}

	public void actionPerformed(ActionEvent event) {
		FileListing sourceListing = mainWindow
				.getCurrentTab(mainWindow.leftTabs);
		FileListing destListing = mainWindow
				.getCurrentTab(mainWindow.rightTabs);

		String sourcePath = sourceListing.getPath();
		String destPath = destListing.getPath();

		sourceListing.setPath(destPath);
		destListing.setPath(sourcePath);
	}
}