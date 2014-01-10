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
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;

public class HomeAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 337608857452610788L;

	private MadCommander mainWindow;

	public HomeAction(MadCommander mainWindow) {
		super(Translator._("Go to user dir"), IconFactory.newIcon("home.gif"));

		this.mainWindow = mainWindow;
	}

	public void actionPerformed(ActionEvent event) {
		String userHome = System.getProperty("user.home");
		mainWindow.getSource().setPath(userHome);
	}
}
