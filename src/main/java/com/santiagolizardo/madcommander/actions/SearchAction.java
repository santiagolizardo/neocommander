/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import com.santiagolizardo.madcommander.dialogs.SearchDialog;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;


public class SearchAction extends AbstractAction {

	private static final long serialVersionUID = -1957915845327720772L;

	private JFrame mainWindow;
	
	public SearchAction(JFrame mainWindow) {
		super(Translator._("Search..."), IconFactory.newIcon("find.gif"));

		this.mainWindow = mainWindow;
		
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F7,
				KeyEvent.ALT_MASK));
	}

	public void actionPerformed(ActionEvent event) {
		SearchDialog searchDialog = new SearchDialog(mainWindow);
		searchDialog.setVisible(true);
	}
}
