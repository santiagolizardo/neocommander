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
package com.santiagolizardo.madcommander;

import javax.swing.SwingUtilities;

import com.santiagolizardo.madcommander.config.ConfigData;
import com.santiagolizardo.madcommander.config.ConfigHandler;
import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.services.LoggingServices;
import com.santiagolizardo.madcommander.util.Os;
import com.santiagolizardo.madcommander.util.OsDetector;
import com.santiagolizardo.madcommander.util.gui.SwingUtil;
import java.io.IOException;

/**
 * Application entry point.
 */
public class Main {

	public static void main(String[] args) {

		try {
			LoggingServices.init();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		ConfigHandler configHandler = new ConfigHandler();
		final ConfigData configData = configHandler.read();

		Translator.start(configData.getLanguage());

		// LockManager.check();
		
		try {
			if (OsDetector.get().equals(Os.Osx)) {
				System.setProperty("apple.laf.useScreenMenuBar", "true");
				System.setProperty("com.apple.mrj.application.apple.menu.about.name", AppConstants.APP_NAME);
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}

		/*
		 * We put the main frame in the event-dispatching thread
		 */
		SwingUtilities.invokeLater(() -> {
			SwingUtil.setSystemLookAndFeel();

			MainWindow app = new MainWindow();
			app.setConfigData(configData);
			app.init();
			app.setVisible(true);
		});
	}
}
