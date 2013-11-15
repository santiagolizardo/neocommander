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
import com.santiagolizardo.madcommander.components.filelisting.FileListing.Format;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;


public class BriefAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 963345464924990261L;

	public BriefAction() {
		super(Translator._("Brief"), IconFactory.newIcon("icon_brief.gif"));
	}

	public void actionPerformed(ActionEvent event) {
		if (!MainGUI.app.getSource().getFormat().equals(Format.Brief))
			MainGUI.app.getSource().setFormat(Format.Brief);
	}
}
