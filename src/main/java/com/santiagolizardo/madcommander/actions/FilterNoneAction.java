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
import com.santiagolizardo.madcommander.resources.languages.Translator;


public class FilterNoneAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1396404929925611587L;

	public FilterNoneAction() {
		super(Translator._("Filter_none"));
	}
	
	public void actionPerformed(ActionEvent event) {
		MainGUI.app.getSource().setFilter(null);
		MainGUI.app.getSource().refreshFiles();
	}
}
