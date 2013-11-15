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
import com.santiagolizardo.madcommander.components.filelisting.filters.FilterDirectories;
import com.santiagolizardo.madcommander.resources.languages.Translator;


public class FilterDirectoriesAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4224205274635164359L;

	public FilterDirectoriesAction() {
		super(Translator._("Filter_directories"));
	}
	
	public void actionPerformed(ActionEvent event) {      
		MainGUI.app.getSource().setFilter(new FilterDirectories());
		MainGUI.app.getSource().refreshFiles();
	}
}
