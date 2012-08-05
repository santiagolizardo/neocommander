/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: LoggingServices.java,v 1.9 2010/01/22 17:57:54 slizardo Exp $
 */
package org.slizardo.madcommander.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

import org.slizardo.madcommander.MadCommander;

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
