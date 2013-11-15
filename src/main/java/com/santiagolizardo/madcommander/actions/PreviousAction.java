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


class PreviousAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3600368513651492859L;

	public PreviousAction() {
		super(Translator._("Previous"), IconFactory.newIcon("prev.gif"));

		setEnabled(false);
	}

	public void actionPerformed(ActionEvent event) {
		String previous = MainGUI.app.getSource().historical.getPrevious();
        MainGUI.app.getSource().setPath(previous);
	}
}
