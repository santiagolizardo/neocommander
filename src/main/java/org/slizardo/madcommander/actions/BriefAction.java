/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: BriefAction.java,v 1.2 2010/01/21 17:02:48 slizardo Exp $
 */
package org.slizardo.madcommander.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.components.filelisting.FileListing.Format;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;


public class BriefAction extends AbstractAction {

	public BriefAction() {
		super(Translator.text("Brief"), IconFactory.newIcon("icon_brief.gif"));
	}

	public void actionPerformed(ActionEvent event) {
		if (!MainGUI.app.getSource().getFormat().equals(Format.Brief))
			MainGUI.app.getSource().setFormat(Format.Brief);
	}
}
