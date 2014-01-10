/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.controller;

import com.santiagolizardo.madcommander.MadCommander;

public class Controller {

	public static void selectGroup(MadCommander mainWindow, String type,
			String searchPattern, boolean caseSensitive) {
		mainWindow.getSource().selectGroup(type, searchPattern, caseSensitive);
	}

	public static void unselectGroup(MadCommander mainWindow, String type,
			String searchPattern, boolean caseSensitive) {
		mainWindow.getSource()
				.unselectGroup(type, searchPattern, caseSensitive);
	}
}
