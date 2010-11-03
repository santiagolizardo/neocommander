package org.slizardo.madcommander.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.resources.languages.Translator;


public class EqualizePanelsAction extends AbstractAction {

	public EqualizePanelsAction() {
		super(Translator.text("Target_equal_to_source"));
	}

	public void actionPerformed(ActionEvent event) {
		String srcPath = MainGUI.app.getSource().getPath();
		MainGUI.app.getDestiny().setPath(srcPath);
	}
}
