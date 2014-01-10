/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;

class NextAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2403279687627821070L;

	private MadCommander mainWindow;

	public NextAction(MadCommander mainWindow) {
		super(Translator._("Next"), IconFactory.newIcon("next.gif"));

		setEnabled(false);

		this.mainWindow = mainWindow;
	}

	public void actionPerformed(ActionEvent event) {
		String next = mainWindow.getSource().historical.getNext();
		mainWindow.getSource().setPath(next);
	}
}
