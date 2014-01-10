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
import com.santiagolizardo.madcommander.components.filelisting.filters.FilterHiddens;
import com.santiagolizardo.madcommander.resources.languages.Translator;

public class FilterHiddensAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6269733626952310453L;

	private MadCommander mainWindow;

	public FilterHiddensAction(MadCommander mainWindow) {
		super(Translator._("Filter hiddens"));

		this.mainWindow = mainWindow;
	}

	public void actionPerformed(ActionEvent event) {
		mainWindow.getSource().setFilter(new FilterHiddens());
		mainWindow.getSource().refreshFiles();
	}
}
