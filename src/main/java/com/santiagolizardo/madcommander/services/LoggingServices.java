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

package com.santiagolizardo.madcommander.services;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.util.Os;
import com.santiagolizardo.madcommander.util.OsDetector;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.logging.LogManager;

public class LoggingServices {

	public static void init() throws IOException {
		var file = new File("logging.properties");
		try (var fis = file.exists() ? new FileInputStream(file) : MainWindow.class.getResourceAsStream("default-logging.properties")) {
			if (fis == null) {
				System.err.println("Unable to find the logging properties file.");
				return;
			}

			// Load properties so we can override the log file location
			var props = new Properties();
			props.load(fis);

			// Override FileHandler.pattern with an OS-appropriate log path
			Path logDir = getLogDirectory();
			Files.createDirectories(logDir);
			String logFilePath = logDir.resolve("NeoCommander.log").toString();
			props.setProperty("java.util.logging.FileHandler.pattern", logFilePath);

			// Write the modified properties to a stream and feed it to LogManager
			var baos = new ByteArrayOutputStream();
			props.store(baos, null);
			var logManager = LogManager.getLogManager();
			logManager.readConfiguration(new ByteArrayInputStream(baos.toByteArray()));
		}
	}

	private static Path getLogDirectory() {
		String userHome = System.getProperty("user.home");

		Os os;
		try {
			os = OsDetector.get();
		} catch (Exception e) {
			return Path.of(userHome, ".neocommander", "logs");
		}

		return switch (os) {
			case Linux -> {
				String xdgState = System.getenv("XDG_STATE_HOME");
				yield (xdgState != null && !xdgState.isBlank())
						? Path.of(xdgState, "NeoCommander")
						: Path.of(userHome, ".local", "state", "NeoCommander");
			}
			case Osx -> Path.of(userHome, "Library", "Logs", "NeoCommander");
			case Windows -> {
				String localAppData = System.getenv("LOCALAPPDATA");
				yield (localAppData != null && !localAppData.isBlank())
						? Path.of(localAppData, "NeoCommander", "logs")
						: Path.of(userHome, "AppData", "Local", "NeoCommander", "logs");
			}
		};
	}
}
