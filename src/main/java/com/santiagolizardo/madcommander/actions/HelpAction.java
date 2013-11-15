/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import com.santiagolizardo.madcommander.dialogs.HelpDialog;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;


public class HelpAction extends AbstractAction {

	private static final long serialVersionUID = 8937302173564528682L;

	public HelpAction() {
		super(Translator._("Help"), IconFactory.newIcon("help.png"));

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("F1"));
	}

	public void actionPerformed(ActionEvent event) {
		HelpDialog dialog = new HelpDialog("HELP.txt");
		dialog.setVisible(true);
	}
}
