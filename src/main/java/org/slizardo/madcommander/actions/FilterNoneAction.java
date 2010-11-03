/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: FilterNoneAction.java,v 1.2 2010/01/21 17:02:48 slizardo Exp $
 */
package org.slizardo.madcommander.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.resources.languages.Translator;


public class FilterNoneAction extends AbstractAction {

	public FilterNoneAction() {
		super(Translator.text("Filter_none"));
	}
	
	public void actionPerformed(ActionEvent event) {
		MainGUI.app.getSource().setFilter(null);
		MainGUI.app.getSource().refreshFiles();
	}
}
