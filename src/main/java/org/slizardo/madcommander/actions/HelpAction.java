/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: HelpAction.java,v 1.2 2009/11/20 00:24:15 slizardo Exp $
 */
package org.slizardo.madcommander.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.slizardo.madcommander.dialogs.HelpDialog;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;


public class HelpAction extends AbstractAction {

	private static final long serialVersionUID = 8937302173564528682L;

	public HelpAction() {
		super(Translator.text("Help"), IconFactory.newIcon("help.png"));

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("F1"));
	}

	public void actionPerformed(ActionEvent event) {
		HelpDialog dialog = new HelpDialog("HELP.txt");
		dialog.setVisible(true);
	}
}
