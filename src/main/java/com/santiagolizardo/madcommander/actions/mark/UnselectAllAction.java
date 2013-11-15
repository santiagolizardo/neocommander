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


class UnselectAllAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6152445946994801060L;

	public UnselectAllAction() {
		super(Translator._("Unselect_all"));
	}
	
	public void actionPerformed(ActionEvent event) {
		FileListing listing = MainGUI.app.getSource();
		listing.unselectAll();
	}
}
