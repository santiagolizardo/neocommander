/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions;

import javax.swing.Action;

import com.santiagolizardo.madcommander.MadCommander;

public class HistoricalActions {

	private static PreviousAction previousAction;
	private static NextAction nextAction;

	public static Action getPreviousAction(MadCommander mainWindow) {
		if (previousAction == null)
			previousAction = new PreviousAction(mainWindow);

		return previousAction;
	}

	public static Action getNextAction(MadCommander mainWindow) {
		if (nextAction == null)
			nextAction = new NextAction(mainWindow);

		return nextAction;
	}
}
