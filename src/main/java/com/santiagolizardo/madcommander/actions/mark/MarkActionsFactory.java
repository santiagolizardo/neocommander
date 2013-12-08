/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions.mark;

import javax.swing.Action;
import javax.swing.JFrame;

public class MarkActionsFactory {

	private static SelectGroupAction selectGroupAction;
	private static UnselectGroupAction unselectGroupAction;
	
	private static SelectAllAction selectAllAction;
	private static UnselectAllAction unselectAllAction;
	private static InvertSelectionAction invertSelectionAction;
	
	public static Action getSelectGroupAction(JFrame mainWindow) {
		if(selectGroupAction == null) selectGroupAction = new SelectGroupAction(mainWindow);
		
		return selectGroupAction;
	}
	
	public static Action getUnselectGroupAction(JFrame mainWindow) {
		if(unselectGroupAction == null) unselectGroupAction = new UnselectGroupAction(mainWindow);
		
		return unselectGroupAction;
	}
	
	public static Action getSelectAllAction() {
		if(selectAllAction == null) selectAllAction = new SelectAllAction();
		
		return selectAllAction;
	}
	
	public static Action getUnselectAllAction() {
		if(unselectAllAction == null) unselectAllAction = new UnselectAllAction();
		
		return unselectAllAction;
	}
	
	public static Action getInvertSelectionAction() {
		if(invertSelectionAction == null) invertSelectionAction = new InvertSelectionAction();
		
		return invertSelectionAction;
	}
}
