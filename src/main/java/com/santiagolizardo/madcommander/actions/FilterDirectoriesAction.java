/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.components.filelisting.filters.FilterDirectories;
import com.santiagolizardo.madcommander.resources.languages.Translator;

public class FilterDirectoriesAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4224205274635164359L;

	private MadCommander mainWindow;

	public FilterDirectoriesAction(MadCommander mainWindow) {
		super(Translator._("Filter directories"));

		this.mainWindow = mainWindow;
	}

	public void actionPerformed(ActionEvent event) {
		mainWindow.getSource().setFilter(new FilterDirectories());
		mainWindow.getSource().refreshFiles();
	}
}
