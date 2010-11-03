/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: SearchAction.java,v 1.2 2009/11/20 00:24:15 slizardo Exp $
 */
package org.slizardo.madcommander.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.slizardo.madcommander.dialogs.SearchDialog;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;


public class SearchAction extends AbstractAction {

	private static final long serialVersionUID = -1957915845327720772L;

	public SearchAction() {
		super(Translator.text("Search..."), IconFactory.newIcon("find.gif"));

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F7,
				KeyEvent.ALT_MASK));
	}

	public void actionPerformed(ActionEvent event) {
		SearchDialog searchDialog = new SearchDialog();
		searchDialog.setVisible(true);
	}
}
