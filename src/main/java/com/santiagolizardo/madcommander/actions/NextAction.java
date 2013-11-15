/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.santiagolizardo.madcommander.MainGUI;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;


class NextAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2403279687627821070L;

	public NextAction() {
		super(Translator._("Next"), IconFactory.newIcon("next.gif"));
        
        setEnabled(false);
	}

	public void actionPerformed(ActionEvent event) {
		String next = MainGUI.app.getSource().historical.getNext();
        MainGUI.app.getSource().setPath(next);
	}
}
