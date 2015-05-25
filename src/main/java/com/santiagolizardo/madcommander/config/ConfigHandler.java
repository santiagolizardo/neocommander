/**
 * This file is part of MadCommander, a file manager with two panels.
 *
 * MadCommander is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * MadCommander is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * MadCommander. If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import com.santiagolizardo.madcommander.util.ListsUtils;
import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.util.logging.Logger;

public class ConfigHandler {

	private final static Logger logger = Logger.getLogger(ConfigHandler.class.getName());

	public void save(ConfigData configData) {
		Properties props = new Properties();
		props.setProperty(
				"bookmarks",
				String.join(File.pathSeparator,
						configData.getBookmarks()));

		props.setProperty("language", configData.getLanguage());

		props.setProperty("window.width", String.valueOf(configData.getWindowSize().getWidth()));
		props.setProperty("window.height", String.valueOf(configData.getWindowSize().getHeight()));

		props.setProperty("window.top", String.valueOf(configData.getWindowPosition().getY()));
		props.setProperty("window.left", String.valueOf(configData.getWindowPosition().getX()));

		File file = new File(System.getProperty("user.home"), ".madcommander");

		try {
			FileOutputStream fos = new FileOutputStream(file);
			props.store(fos, "MadCommander configuration file");
		} catch (IOException e) {
			logger.warning(e.getMessage());
		}
	}

	public ConfigData read() {
		File file = new File(System.getProperty("user.home"), ".madcommander");

		ConfigData configData = new ConfigData();

		try {
			FileInputStream fis = new FileInputStream(file);

			Properties props = new Properties();
			props.load(fis);

			String bookmarksProp = props.getProperty("bookmarks");
			if (!bookmarksProp.isEmpty()) {
				configData.getBookmarks().addAll(ListsUtils.explode(File.pathSeparator,
						bookmarksProp));
			}

			String language = props.getProperty("language");
			if (null != language) {
				configData.setLanguage(language);
			}

			String windowWidth = props.getProperty("window.width");
			String windowHeight = props.getProperty("window.height");
			if (null != windowWidth && null != windowHeight) {
				Dimension windowSize = new Dimension(
						Float.valueOf(props.getProperty("window.width")).intValue(),
						Float.valueOf(props.getProperty("window.height")).intValue()
				);
				configData.setWindowSize(windowSize);
			}

			String windowTop = props.getProperty("window.top");
			String windowLeft = props.getProperty("window.left");
			if (null != windowTop && null != windowLeft) {
				Point windowPosition = new Point(
						Float.valueOf(props.getProperty("window.left")).intValue(),
						Float.valueOf(props.getProperty("window.top")).intValue()
				);
				configData.setWindowPosition(windowPosition);
			}
		} catch (IOException e) {
			logger.warning(e.getMessage());
		}

		return configData;
	}
}
