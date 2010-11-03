/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: MarkActionsFactory.java,v 1.1 2006/03/23 08:18:05 slizardo Exp $
 */
package org.slizardo.madcommander.actions.mark;

import javax.swing.Action;

public class MarkActionsFactory {

	private static SelectGroupAction selectGroupAction;
	private static UnselectGroupAction unselectGroupAction;
	
	private static SelectAllAction selectAllAction;
	private static UnselectAllAction unselectAllAction;
	private static InvertSelectionAction invertSelectionAction;
	
	public static Action getSelectGroupAction() {
		if(selectGroupAction == null) selectGroupAction = new SelectGroupAction();
		
		return selectGroupAction;
	}
	
	public static Action getUnselectGroupAction() {
		if(unselectGroupAction == null) unselectGroupAction = new UnselectGroupAction();
		
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
