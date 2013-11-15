/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.controller;

import com.santiagolizardo.madcommander.MainGUI;
import com.santiagolizardo.madcommander.components.filelisting.FileListing.Position;
import com.santiagolizardo.madcommander.dialogs.progressive.PackProgressDialog;

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
