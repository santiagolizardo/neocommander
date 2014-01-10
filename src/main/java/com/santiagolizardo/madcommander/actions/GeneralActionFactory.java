/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions;

import javax.swing.Action;

import com.santiagolizardo.madcommander.MadCommander;

public class GeneralActionFactory {

	public static Action getAddToBookmarksAction(MadCommander mainWindow) {
		if (GeneralActionFactory.addToBookmarksAction == null)
			GeneralActionFactory.addToBookmarksAction = new AddToBookmarksAction(
					mainWindow);

		return GeneralActionFactory.addToBookmarksAction;
	}

	private static Action addToBookmarksAction;

}
