/*
  This file is part of MadCommander, a file manager with two panels.

  MadCommander is free software: you can redistribute it and/or modify it under
  the terms of the GNU General Public License as published by the Free Software
  Foundation, either version 3 of the License, or (at your option) any later
  version.

  MadCommander is distributed in the hope that it will be useful, but WITHOUT
  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
  FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
  details.

  You should have received a copy of the GNU General Public License along with
  MadCommander. If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.config;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import com.santiagolizardo.madcommander.util.ListsUtils;

public class ConfigHandler {

	private final static Logger logger = Logger.getLogger(ConfigHandler.class.getName());

	private static final String DIRECTORY_NAME = ".neocommander";

	public void save(ConfigData configData) {
		var props = new Properties();
		props.setProperty(
				"bookmarks",
				String.join(File.pathSeparator,
						configData.getBookmarks()));

		props.setProperty("language", configData.getLanguage());

		props.setProperty("window.width", String.valueOf(configData.getWindowSize().getWidth()));
		props.setProperty("window.height", String.valueOf(configData.getWindowSize().getHeight()));

		props.setProperty("window.top", String.valueOf(configData.getWindowPosition().getY()));
		props.setProperty("window.left", String.valueOf(configData.getWindowPosition().getX()));

		var file = new File(System.getProperty("user.home"), DIRECTORY_NAME);

		try (var fos = new FileOutputStream(file)) {
			props.store(fos, "MadCommander configuration file");
		} catch (IOException e) {
			logger.warning(e.getMessage());
		}
	}

	public ConfigData read() {
		var file = new File(System.getProperty("user.home"), DIRECTORY_NAME);

		var configData = new ConfigData();

		try (var fis = new FileInputStream(file)) {

			var props = new Properties();
			props.load(fis);

			var bookmarksProp = props.getProperty("bookmarks");
			if (!bookmarksProp.isEmpty()) {
				configData.getBookmarks().addAll(ListsUtils.explode(File.pathSeparator,
						bookmarksProp));
			}

			var language = props.getProperty("language");
			if (null != language) {
				configData.setLanguage(language);
			}

			var windowWidth = props.getProperty("window.width");
			var windowHeight = props.getProperty("window.height");
			if (null != windowWidth && null != windowHeight) {
				var windowSize = new Dimension(
						Integer.parseInt(props.getProperty("window.width")),
						Integer.parseInt(props.getProperty("window.height")));
				configData.setWindowSize(windowSize);
			}

			var windowTop = props.getProperty("window.top");
			var windowLeft = props.getProperty("window.left");
			if (null != windowTop && null != windowLeft) {
				var windowPosition = new Point(
						Integer.parseInt(props.getProperty("window.left")),
						Integer.parseInt(props.getProperty("window.top")));
				configData.setWindowPosition(windowPosition);
			}
		} catch (IOException e) {
			logger.warning(e.getMessage());
		}

		return configData;
	}
}
