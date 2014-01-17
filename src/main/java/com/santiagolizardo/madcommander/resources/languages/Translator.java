/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.resources.languages;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.logging.Logger;

import org.xnap.commons.i18n.I18n;
import org.xnap.commons.i18n.I18nFactory;

public class Translator {

	private static final Logger logger = Logger.getLogger(Translator.class
			.getName());

	private static I18n i18n;

	public static void start(String language) {
		Locale locale = new Locale(language);

		try {
			i18n = I18nFactory.getI18n(Translator.class, locale);
		} catch (MissingResourceException mre) {
			logger.warning(mre.getMessage());
		}
	}

	public static String _(String key) {
		if (null == i18n)
			return key;

		try {
			return i18n.tr(key);
		} catch (MissingResourceException mre) {
			logger.warning("Missing translation: " + key);
			return key;
		}
	}
}
