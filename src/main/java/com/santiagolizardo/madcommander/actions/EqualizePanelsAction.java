package com.santiagolizardo.madcommander.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.santiagolizardo.madcommander.MainGUI;
import com.santiagolizardo.madcommander.resources.languages.Translator;


public class EqualizePanelsAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5924873313089489091L;

	public EqualizePanelsAction() {
		super(Translator._("Target_equal_to_source"));
	}

	public void actionPerformed(ActionEvent event) {
		String srcPath = MainGUI.app.getSource().getPath();
		MainGUI.app.getDestiny().setPath(srcPath);
	}
}
