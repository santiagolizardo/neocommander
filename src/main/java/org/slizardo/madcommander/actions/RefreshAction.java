/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: RefreshAction.java,v 1.2 2010/01/21 17:02:48 slizardo Exp $
 */
package org.slizardo.madcommander.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;


public class RefreshAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3329520213155594135L;

	public RefreshAction() {
		super(Translator.text("Refresh"), IconFactory.newIcon("refresh.gif"));
	}

	public void actionPerformed(ActionEvent event) {
		MainGUI.app.getSource().refreshFiles();
	}
}
