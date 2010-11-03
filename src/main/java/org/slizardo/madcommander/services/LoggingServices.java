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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.LogManager;

public class LoggingServices {

	public static void init() throws IOException {
		File dir = new File("logs");
		if (!dir.exists())
			if (!dir.mkdir())
				throw new IOException("Unable to create logs directory.");

		LogManager logManager = LogManager.getLogManager();

		StringBuffer buffer = new StringBuffer();
		buffer.append(System.getProperty("user.home")).append(File.separator);
		buffer.append(".madcommander").append(File.separator);
		buffer.append("log.properties");

		String filePath = buffer.toString();

		File file = new File(filePath);
		if (file.exists()) {
			FileInputStream fis = new FileInputStream(file);
			logManager.readConfiguration(fis);
			fis.close();
		} else {
			throw new FileNotFoundException(String.format(
					"Log configuration file (%s) not found.%n", filePath));
		}
	}
}
