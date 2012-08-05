/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: Panels.java,v 1.10 2010/01/21 17:02:48 slizardo Exp $
 */
package org.slizardo.madcommander.components;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;

import javax.swing.JSplitPane;

import org.slizardo.madcommander.MainGUI;


public class Panels extends JSplitPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 284991976741039153L;

	public Panels() {
		super();

		setFocusable(false);
		setMinimumSize(new Dimension(400, 320));
		setPreferredSize(new Dimension(400, 320));
		enableEvents(ComponentEvent.COMPONENT_RESIZED);
	}

	public void loadProperties() {
//		changeOrientation(ConfigWrapper.getInstance().getIntProperty("panels.orientation"));
		changeOrientation(JSplitPane.HORIZONTAL_SPLIT);
	}

	public void changeOrientation(int orientation) {
		if (orientation == JSplitPane.HORIZONTAL_SPLIT) {
			setDividerLocation(getWidth() / 2);
			setLeftComponent(MainGUI.app.leftTabs);
			setRightComponent(MainGUI.app.rightTabs);
		} else { // JSplitPane.VERTICAL_SPLIT:
			setDividerLocation(getHeight() / 2);
			setTopComponent(MainGUI.app.leftTabs);
			setBottomComponent(MainGUI.app.rightTabs);
		}

		setOrientation(orientation);
	}

	protected void processComponentEvent(ComponentEvent event) {
		if (event.getID() == ComponentEvent.COMPONENT_RESIZED) {
			setDividerLocation(getParent().getWidth() / 2);
		} else {
			super.processComponentEvent(event);
		}
	}
}
