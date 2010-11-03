/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: CopyAction.java,v 1.1 2006/03/21 17:25:36 slizardo Exp $
 */
package org.slizardo.madcommander.actions.fileops;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.slizardo.madcommander.dialogs.progressive.CopyProgressDialog;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;


class CopyAction extends AbstractAction {

	public CopyAction() {
		super(Translator.text("Copy"), IconFactory.newIcon("F5.gif"));
		
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("F5"));
	}

	public void actionPerformed(ActionEvent event) {
		CopyProgressDialog copyDialog = new CopyProgressDialog();
		copyDialog.begin();
	}
}
