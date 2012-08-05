/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: SelectDriveAction.java,v 1.1 2006/03/21 17:25:37 slizardo Exp $
 */
package org.slizardo.madcommander.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.components.filelisting.FileListing;
import org.slizardo.madcommander.dialogs.SelectDrivePopup;


public class SelectDriveAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5388378675835343628L;

	public SelectDriveAction() {
		super("SelectDriveAction");
		
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_MASK));
	}

	public void actionPerformed(ActionEvent event) {
		FileListing fileListing = MainGUI.app.getSource();
		SelectDrivePopup popup = new SelectDrivePopup();
		popup.show(fileListing, fileListing.getLocation().x, fileListing.getLocation().y);
	}
}
