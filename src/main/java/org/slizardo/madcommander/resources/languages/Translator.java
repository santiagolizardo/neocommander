/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: Translator.java,v 1.2 2009/11/20 00:24:15 slizardo Exp $
 */
package org.slizardo.madcommander.resources.languages;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import org.slizardo.madcommander.config.ConfigWrapper;


public class Translator {

	private static Logger logger = Logger.getLogger(Translator.class.getName());

	private static Properties properties;

	public static void init() {
		String locale = ConfigWrapper.getProperty("app.locale");
		properties = new Properties();

		try {
			properties.load(Translator.class.getResourceAsStream("messages_"
					+ locale + ".properties"));
		} catch (IOException io) {
			logger.warning(io.getMessage());
		}
	}

	public static String text(String text) {
		String property = properties.getProperty(text);
		if (property == null) {
			logger.warning("Translation " + text + " does not exist.");
			property = text;
		}

		return property;
	}
}
