/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.santiagolizardo.madcommander.MainGUI;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;


public class RefreshAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3329520213155594135L;

	public RefreshAction() {
		super(Translator._("Refresh"), IconFactory.newIcon("refresh.gif"));
	}

	public void actionPerformed(ActionEvent event) {
		MainGUI.app.getSource().refreshFiles();
	}
}
