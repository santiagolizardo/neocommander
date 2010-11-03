/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: PreviousAction.java,v 1.1 2006/03/21 17:25:38 slizardo Exp $
 */
package org.slizardo.madcommander.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;


class PreviousAction extends AbstractAction {

	public PreviousAction() {
		super(Translator.text("Previous"), IconFactory.newIcon("prev.gif"));

		setEnabled(false);
	}

	public void actionPerformed(ActionEvent event) {
		String previous = MainGUI.app.getSource().historical.getPrevious();
        MainGUI.app.getSource().setPath(previous);
	}
}
