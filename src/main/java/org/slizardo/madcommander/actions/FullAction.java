/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: FullAction.java,v 1.2 2010/01/21 17:02:48 slizardo Exp $
 */
package org.slizardo.madcommander.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.components.filelisting.FileListing.Format;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;


public class FullAction extends AbstractAction {

	public FullAction() {
		super(Translator.text("Full"), IconFactory.newIcon("icon_full.gif"));
	}

	public void actionPerformed(ActionEvent event) {
		if (!MainGUI.app.getSource().getFormat().equals(Format.Full))
			MainGUI.app.getSource().setFormat(Format.Full);
	}
}
