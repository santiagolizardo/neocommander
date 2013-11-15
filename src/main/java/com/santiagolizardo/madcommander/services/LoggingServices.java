/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
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
