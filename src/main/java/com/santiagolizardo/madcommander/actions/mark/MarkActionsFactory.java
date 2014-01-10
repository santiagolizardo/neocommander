/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions.mark;

import javax.swing.Action;

import com.santiagolizardo.madcommander.MadCommander;

public class MarkActionsFactory {

	private static SelectGroupAction selectGroupAction;
	private static UnselectGroupAction unselectGroupAction;

	private static SelectAllAction selectAllAction;
	private static UnselectAllAction unselectAllAction;
	private static InvertSelectionAction invertSelectionAction;

	public static Action getSelectGroupAction(MadCommander mainWindow) {
		if (selectGroupAction == null)
			selectGroupAction = new SelectGroupAction(mainWindow);

		return selectGroupAction;
	}

	public static Action getUnselectGroupAction(MadCommander mainWindow) {
		if (unselectGroupAction == null)
			unselectGroupAction = new UnselectGroupAction(mainWindow);

		return unselectGroupAction;
	}

	public static Action getSelectAllAction(MadCommander mainWindow) {
		if (selectAllAction == null)
			selectAllAction = new SelectAllAction(mainWindow);

		return selectAllAction;
	}

	public static Action getUnselectAllAction(MadCommander mainWindow) {
		if (unselectAllAction == null)
			unselectAllAction = new UnselectAllAction(mainWindow);

		return unselectAllAction;
	}

	public static Action getInvertSelectionAction(MadCommander mainWindow) {
		if (invertSelectionAction == null)
			invertSelectionAction = new InvertSelectionAction(mainWindow);

		return invertSelectionAction;
	}
}
