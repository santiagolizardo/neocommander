/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions.mark;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.components.filelisting.FileListing;
import com.santiagolizardo.madcommander.resources.languages.Translator;

class InvertSelectionAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2060474525811836981L;

	private MadCommander mainWindow;

	public InvertSelectionAction(MadCommander mainWindow) {
		super(Translator._("Invert selection"));

		this.mainWindow = mainWindow;
	}

	public void actionPerformed(ActionEvent event) {
		FileListing listing = mainWindow.getSource();
		listing.invertSelection();
	}
}
