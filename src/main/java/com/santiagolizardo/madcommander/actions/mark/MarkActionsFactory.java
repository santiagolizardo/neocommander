/**
 * This file is part of MadCommander, a file manager with two panels.
 *
 * MadCommander is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MadCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MadCommander.  If not, see <http://www.gnu.org/licenses/>.
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
