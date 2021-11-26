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
package com.santiagolizardo.madcommander.actions.mark;

import com.santiagolizardo.madcommander.MainWindow;

import javax.swing.*;

public class MarkActionsFactory {

	private static SelectGroupAction selectGroupAction;
	private static UnselectGroupAction unselectGroupAction;

	private static SelectAllAction selectAllAction;
	private static UnselectAllAction unselectAllAction;
	private static InvertSelectionAction invertSelectionAction;

	public static Action getSelectGroupAction(MainWindow mainWindow) {
		if (selectGroupAction == null)
			selectGroupAction = new SelectGroupAction(mainWindow);

		return selectGroupAction;
	}

	public static Action getUnselectGroupAction(MainWindow mainWindow) {
		if (unselectGroupAction == null)
			unselectGroupAction = new UnselectGroupAction(mainWindow);

		return unselectGroupAction;
	}

	public static Action getSelectAllAction(MainWindow mainWindow) {
		if (selectAllAction == null)
			selectAllAction = new SelectAllAction(mainWindow);

		return selectAllAction;
	}

	public static Action getUnselectAllAction(MainWindow mainWindow) {
		if (unselectAllAction == null)
			unselectAllAction = new UnselectAllAction(mainWindow);

		return unselectAllAction;
	}

	public static Action getInvertSelectionAction(MainWindow mainWindow) {
		if (invertSelectionAction == null)
			invertSelectionAction = new InvertSelectionAction(mainWindow);

		return invertSelectionAction;
	}
}
