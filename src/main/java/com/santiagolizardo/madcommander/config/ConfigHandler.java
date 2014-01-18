package com.santiagolizardo.madcommander.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Properties;

import com.santiagolizardo.madcommander.util.ListsUtils;
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

		File file = new File(System.getProperty("user.home"), ".madcommander");

		try {
			FileOutputStream fos = new FileOutputStream(file);
			props.store(fos, "MadCommander configuration file - Updated on "
					+ new Date().toString());
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
			if (null == language) {
				language = "en";
			}
			configData.setLanguage(language);
		} catch (IOException e) {
			logger.warning(e.getMessage());
		}

		return configData;
	}
}
