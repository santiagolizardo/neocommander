/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: HomeAction.java,v 1.1 2006/03/21 17:25:38 slizardo Exp $
 */
package org.slizardo.madcommander.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;


public class HomeAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 337608857452610788L;

	public HomeAction() {
		super(Translator.text("Go_to_user_dir"), IconFactory
				.newIcon("home.gif"));
	}

	public void actionPerformed(ActionEvent event) {
		String userHome = System.getProperty("user.home");
		MainGUI.app.getSource().setPath(userHome);
	}
}
