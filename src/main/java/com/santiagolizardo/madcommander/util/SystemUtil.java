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
package com.santiagolizardo.madcommander.util;

import java.awt.Component;
import java.awt.Desktop;
import java.net.URI;

import com.santiagolizardo.madcommander.util.gui.DialogFactory;


public class SystemUtil {

	public static void execute(Component component, String command) {
		Runtime runtime = Runtime.getRuntime();
		String[] args = new String[3];
		args[0] = "cmd.exe";
		args[1] = "/C";
		args[2] = command;

		try {
			runtime.exec(args);
		} catch (Exception e) {
			DialogFactory.showErrorMessage(component, e.getMessage());
		}
	}

	public static void browse(Component component, String address) {
		Desktop desktop = Desktop.getDesktop();

		try {
			desktop.browse(new URI(address));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
