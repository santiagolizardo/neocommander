/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions.mark;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.santiagolizardo.madcommander.MainGUI;
import com.santiagolizardo.madcommander.components.filelisting.FileListing;
import com.santiagolizardo.madcommander.resources.languages.Translator;


class InvertSelectionAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2060474525811836981L;

	public InvertSelectionAction() {
		super(Translator._("Invert_selection"));
	}
	
	public void actionPerformed(ActionEvent event) {
		FileListing listing = MainGUI.app.getSource();
		listing.invertSelection();
	}
}
