/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: InvertSelectionAction.java,v 1.1 2006/03/23 08:18:05 slizardo Exp $
 */
package org.slizardo.madcommander.actions.mark;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.components.filelisting.FileListing;
import org.slizardo.madcommander.resources.languages.Translator;


class InvertSelectionAction extends AbstractAction {

	public InvertSelectionAction() {
		super(Translator.text("Invert_selection"));
	}
	
	public void actionPerformed(ActionEvent event) {
		FileListing listing = MainGUI.app.getSource();
		listing.invertSelection();
	}
}
