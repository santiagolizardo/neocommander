/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: NextAction.java,v 1.1 2006/03/21 17:25:37 slizardo Exp $
 */
package org.slizardo.madcommander.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;


class NextAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2403279687627821070L;

	public NextAction() {
		super(Translator.text("Next"), IconFactory.newIcon("next.gif"));
        
        setEnabled(false);
	}

	public void actionPerformed(ActionEvent event) {
		String next = MainGUI.app.getSource().historical.getNext();
        MainGUI.app.getSource().setPath(next);
	}
}
