/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;

import javax.swing.JSplitPane;

import com.santiagolizardo.madcommander.MadCommander;

public class Panels extends JSplitPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 284991976741039153L;

	private MadCommander mainWindow;

	public Panels(MadCommander mainWindow) {
		super();

		this.mainWindow = mainWindow;

		setFocusable(false);
		setMinimumSize(new Dimension(400, 320));
		setPreferredSize(new Dimension(400, 320));
		enableEvents(ComponentEvent.COMPONENT_RESIZED);
	}

	public void loadProperties() {
		// changeOrientation(ConfigWrapper.getInstance().getIntProperty("panels.orientation"));
		changeOrientation(JSplitPane.HORIZONTAL_SPLIT);
	}

	public void changeOrientation(int orientation) {
		if (orientation == JSplitPane.HORIZONTAL_SPLIT) {
			setDividerLocation(getWidth() / 2);
			setLeftComponent(mainWindow.leftTabs);
			setRightComponent(mainWindow.rightTabs);
		} else { // JSplitPane.VERTICAL_SPLIT:
			setDividerLocation(getHeight() / 2);
			setTopComponent(mainWindow.leftTabs);
			setBottomComponent(mainWindow.rightTabs);
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
