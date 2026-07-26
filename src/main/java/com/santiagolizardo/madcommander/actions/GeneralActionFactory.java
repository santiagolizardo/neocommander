/*
 * Copyright (C) 2026 Santiago Lizardo
 *
 * This file is part of NeoCommander.
 *
 * NeoCommander is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NeoCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.santiagolizardo.madcommander.actions;

import com.santiagolizardo.madcommander.MainWindow;

import javax.swing.*;

public class GeneralActionFactory {

	public static Action getAddToBookmarksAction(MainWindow mainWindow) {
		if (GeneralActionFactory.addToBookmarksAction == null)
			GeneralActionFactory.addToBookmarksAction = new AddToBookmarksAction(
					mainWindow);

		return GeneralActionFactory.addToBookmarksAction;
	}

	private static Action addToBookmarksAction;

}
