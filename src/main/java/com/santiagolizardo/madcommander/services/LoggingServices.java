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
package com.santiagolizardo.madcommander.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

import com.santiagolizardo.madcommander.MadCommander;

public class LoggingServices {

	public static void init() throws IOException {

		InputStream fis = null;

		File file = new File("logging.properties");
		if (file.exists()) {
			fis = new FileInputStream(file);
		} else {
			fis = MadCommander.class
					.getResourceAsStream("default-logging.properties");
		}

		if (fis == null) {
			System.err.println("Unable to find the logging properties file.");
			return;
		}

		LogManager logManager = LogManager.getLogManager();
		logManager.readConfiguration(fis);
		fis.close();
	}
}
