/*
  This file is part of MadCommander, a file manager with two panels.

  MadCommander is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  MadCommander is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with MadCommander.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.components;

import com.santiagolizardo.madcommander.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;

public class Panels extends JSplitPane {



	private final MainWindow mainWindow;

	public Panels(MainWindow mainWindow) {
		super();

		this.mainWindow = mainWindow;

		setFocusable(false);
		setMinimumSize(new Dimension(400, 320));
		setPreferredSize(new Dimension(400, 320));
		enableEvents(ComponentEvent.COMPONENT_RESIZED);
	}

	public void loadProperties() {
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

	@Override
	protected void processComponentEvent(ComponentEvent event) {
		if (event.getID() == ComponentEvent.COMPONENT_RESIZED) {
			setDividerLocation(getParent().getWidth() / 2);
		} else {
			super.processComponentEvent(event);
		}
	}
}
