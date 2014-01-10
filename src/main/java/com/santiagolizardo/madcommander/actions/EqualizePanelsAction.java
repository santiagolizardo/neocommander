package com.santiagolizardo.madcommander.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.resources.languages.Translator;

public class EqualizePanelsAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5924873313089489091L;
	private MadCommander mainWindow;

	public EqualizePanelsAction(MadCommander mainWindow) {
		super(Translator._("Target equal to source"));

		this.mainWindow = mainWindow;
	}

	public void actionPerformed(ActionEvent event) {
		String srcPath = mainWindow.getSource().getPath();
		mainWindow.getDestiny().setPath(srcPath);
	}
}
