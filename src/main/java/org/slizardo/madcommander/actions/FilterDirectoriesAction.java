/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: FilterDirectoriesAction.java,v 1.2 2010/01/21 17:02:48 slizardo Exp $
 */
package org.slizardo.madcommander.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.components.filelisting.filters.FilterDirectories;
import org.slizardo.madcommander.resources.languages.Translator;


public class FilterDirectoriesAction extends AbstractAction {

	public FilterDirectoriesAction() {
		super(Translator.text("Filter_directories"));
	}
	
	public void actionPerformed(ActionEvent event) {      
		MainGUI.app.getSource().setFilter(new FilterDirectories());
		MainGUI.app.getSource().refreshFiles();
	}
}
