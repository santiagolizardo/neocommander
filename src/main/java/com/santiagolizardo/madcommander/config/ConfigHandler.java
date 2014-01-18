package com.santiagolizardo.madcommander.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
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
				ListsUtils.implode(File.pathSeparator,
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

			configData.setBookmarks(ListsUtils.explode(File.pathSeparator,
					props.getProperty("bookmarks")));

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
