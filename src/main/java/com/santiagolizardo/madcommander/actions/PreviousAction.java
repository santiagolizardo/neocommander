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

class PreviousAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3600368513651492859L;

	private MadCommander mainWindow;

	public PreviousAction(MadCommander mainWindow) {
		super(Translator._("Previous"), IconFactory.newIcon("prev.gif"));

		this.mainWindow = mainWindow;

		setEnabled(false);
	}

	public void actionPerformed(ActionEvent event) {
		String previous = mainWindow.getSource().historical.getPrevious();
		mainWindow.getSource().setPath(previous);
	}
}
