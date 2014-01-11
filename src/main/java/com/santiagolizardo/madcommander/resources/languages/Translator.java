/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.resources.languages;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class Translator {

	private static Logger logger = Logger.getLogger(Translator.class.getName());

	private static Properties properties;

	public static void init(String locale) {
		if (locale == null) {
			throw new IllegalArgumentException("Parameter \"locale\" is null.");
		}
		properties = new Properties();

		try {
			properties.load(Translator.class.getResourceAsStream("messages_"
					+ locale + ".properties"));
		} catch (IOException io) {
			logger.warning(io.getMessage());
		}
	}

	public static String _(String text) {
		String property = properties.getProperty(text);
		return (property == null ? text : property);
	}
}
