/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: SelectAllAction.java,v 1.1 2006/03/23 08:18:05 slizardo Exp $
 */
package org.slizardo.madcommander.actions.mark;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.components.filelisting.FileListing;
import org.slizardo.madcommander.resources.languages.Translator;


class SelectAllAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4495200194919712150L;

	public SelectAllAction() {
		super(Translator.text("Select_all"));
		
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.ALT_MASK));
	}
	
	public void actionPerformed(ActionEvent event) {
		FileListing listing = MainGUI.app.getSource();
		listing.selectAll();
	}
}
