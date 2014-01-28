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
package com.santiagolizardo.madcommander.controller;

import com.santiagolizardo.madcommander.MadCommander;

public class Controller {

	public static void selectGroup(MadCommander mainWindow, String type,
			String searchPattern, boolean caseSensitive) {
		mainWindow.getSource().selectGroup(type, searchPattern, caseSensitive);
	}

	public static void unselectGroup(MadCommander mainWindow, String type,
			String searchPattern, boolean caseSensitive) {
		mainWindow.getSource()
				.unselectGroup(type, searchPattern, caseSensitive);
	}
}
