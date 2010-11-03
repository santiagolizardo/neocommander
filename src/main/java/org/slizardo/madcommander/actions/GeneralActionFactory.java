/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: GeneralActionFactory.java,v 1.1 2006/03/21 17:25:37 slizardo Exp $
 */
package org.slizardo.madcommander.actions;

import javax.swing.Action;

public class GeneralActionFactory {

	public static Action getAddToBookmarksAction() {
		if(GeneralActionFactory.addToBookmarksAction == null) GeneralActionFactory.addToBookmarksAction = new AddToBookmarksAction();
		
		return GeneralActionFactory.addToBookmarksAction;
	}

	private static Action addToBookmarksAction;

}
