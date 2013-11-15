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


public class HomeAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 337608857452610788L;

	public HomeAction() {
		super(Translator._("Go_to_user_dir"), IconFactory
				.newIcon("home.gif"));
	}

	public void actionPerformed(ActionEvent event) {
		String userHome = System.getProperty("user.home");
		MainGUI.app.getSource().setPath(userHome);
	}
}
