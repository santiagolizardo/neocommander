/*
  This file is part of MadCommander, a file manager with two panels.

  MadCommander is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  MadCommander is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with MadCommander.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.resources.languages;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xnap.commons.i18n.I18n;
import org.xnap.commons.i18n.I18nFactory;

public class Translator {

	private static final Logger logger = Logger.getLogger(Translator.class
			.getName());

	private static I18n i18n;

	public static void start(String language) {
		Locale locale = Locale.forLanguageTag(language);

		try {
			i18n = I18nFactory.getI18n(Translator.class, locale);
		} catch (MissingResourceException mre) {
			logger.warning(mre.getMessage());
		}
	}

	public static String tr(String text) {
		if (null == i18n)
			return text;

		try {
			return i18n.tr(text);
		} catch (MissingResourceException mre) {
			logger.log(Level.WARNING, "Missing translation: {0}", mre.getKey());
			return text;
		}
	}

	public static String trn(String text, String pluralText, long number) {
		if (null == i18n)
			return text;

		try {
			return i18n.trn(text, pluralText, number);
		} catch (MissingResourceException mre) {
			logger.log(Level.WARNING, "Missing translation: {0}", mre.getKey());
			return text;
		}
	}
}
