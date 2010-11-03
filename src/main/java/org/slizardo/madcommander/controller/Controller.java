/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: Controller.java,v 1.12 2010/01/21 17:02:49 slizardo Exp $
 */
package org.slizardo.madcommander.controller;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.components.filelisting.FileListing.Position;
import org.slizardo.madcommander.dialogs.progressive.PackProgressDialog;

public class Controller {

	public static void compress(String fileName, PackTypes type) {
		PackProgressDialog progressDialog = new PackProgressDialog(fileName,
				type);
		progressDialog.begin();
	}

	public static void invertSelection() {
		MainGUI.app.getSource().invertSelection();
	}

	public static void swapPanels() {
		MainGUI.app.currentPanel = MainGUI.app.currentPanel == Position.Left ? Position.Right : Position.Left;
		MainGUI.app.getSource().requestFocus();
	}

	public static void execute() {
		MainGUI.app.getSource().execute();
	}

	public static void selectGroup(String type, String searchPattern,
			boolean caseSensitive) {
		MainGUI.app.getSource().selectGroup(type, searchPattern, caseSensitive);
	}

	public static void unselectGroup(String type, String searchPattern,
			boolean caseSensitive) {
		MainGUI.app.getSource().unselectGroup(type, searchPattern, caseSensitive);
	}
}
