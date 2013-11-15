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
import com.santiagolizardo.madcommander.components.filelisting.filters.FilterHiddens;
import com.santiagolizardo.madcommander.resources.languages.Translator;


public class FilterHiddensAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6269733626952310453L;

	public FilterHiddensAction() {
		super(Translator._("Filter_hiddens"));
	}
	
	public void actionPerformed(ActionEvent event) {
		MainGUI.app.getSource().setFilter(new FilterHiddens());
		MainGUI.app.getSource().refreshFiles();
	}
}
