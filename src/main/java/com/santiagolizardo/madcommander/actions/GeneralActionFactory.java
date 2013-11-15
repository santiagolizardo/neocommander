/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions;

import javax.swing.Action;

public class GeneralActionFactory {

	public static Action getAddToBookmarksAction() {
		if(GeneralActionFactory.addToBookmarksAction == null) GeneralActionFactory.addToBookmarksAction = new AddToBookmarksAction();
		
		return GeneralActionFactory.addToBookmarksAction;
	}

	private static Action addToBookmarksAction;

}
