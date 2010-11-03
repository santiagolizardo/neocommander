/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: InputMapUtil.java,v 1.2 2006/03/21 17:25:40 slizardo Exp $
 */
package org.slizardo.madcommander.util.actions;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class InputMapUtil {

	public static void putAction(JComponent component, Action action) {
		KeyStroke keyStroke = (KeyStroke)action.getValue(Action.ACCELERATOR_KEY);
		String name = (String)action.getValue(Action.NAME);
		
		component.getInputMap().put(keyStroke, name);
		component.getActionMap().put(name, action);
	}
}
