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
import com.santiagolizardo.madcommander.resources.languages.Translator;

public class FilterNoneAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1396404929925611587L;

	private MadCommander mainWindow;

	public FilterNoneAction(MadCommander mainWindow) {
		super(Translator._("Filter none"));

		this.mainWindow = mainWindow;
	}

	public void actionPerformed(ActionEvent event) {
		mainWindow.getSource().setFilter(null);
		mainWindow.getSource().refreshFiles();
	}
}
