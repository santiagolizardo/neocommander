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


public class FullAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5935640263312602725L;

	public FullAction() {
		super(Translator._("Full"), IconFactory.newIcon("icon_full.gif"));
	}

	public void actionPerformed(ActionEvent event) {
		if (!MainGUI.app.getSource().getFormat().equals(Format.Full))
			MainGUI.app.getSource().setFormat(Format.Full);
	}
}
