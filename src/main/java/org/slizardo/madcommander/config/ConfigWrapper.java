/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: ConfigWrapper.java,v 1.10 2009/11/20 00:24:15 slizardo Exp $
 */
package org.slizardo.madcommander.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.util.io.FileOperations;


public class ConfigWrapper {

	private static Properties properties;

	private static String configurationFile;

	public static void init() throws FileNotFoundException, IOException {
		StringBuffer buffer = new StringBuffer();
		buffer.append(System.getProperty("user.home")).append(File.separator);
		buffer.append(".madcommander").append(File.separator);
		buffer.append("configuration.properties");
		configurationFile = buffer.toString();

		File file = new File(configurationFile);
		if (!file.exists()) {
			createConfigurationFiles();
		}

		properties = new Properties();
		properties.load(new FileInputStream(configurationFile));
	}

	public static void createConfigurationFiles() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(System.getProperty("user.home")).append(File.separator);
		buffer.append(".madcommander");
		File dir = new File(buffer.toString());
		if (!dir.exists())
			dir.mkdir();

		buffer.append(File.separator);

		String configuration = buffer.toString().concat(
				"configuration.properties");
		String log = buffer.toString().concat("log.properties");

		FileOperations.copy(ConfigWrapper.class
				.getResourceAsStream("configuration.properties"), new File(
				configuration));
		FileOperations.copy(ConfigWrapper.class
				.getResourceAsStream("log.properties"), new File(log));
	}

	public static void setProperty(String name, String value) {
		properties.put(name, value);
	}

	public static String getProperty(String name) {
		return properties.getProperty(name);
	}

	public static void setBooleanProperty(String name, boolean property) {
		properties.put(name, String.valueOf(property));
	}

	public static boolean getBooleanProperty(String name) {
		return Boolean.valueOf(properties.getProperty(name)).booleanValue();
	}

	public static void setIntProperty(String name, int value) {
		properties.put(name, String.valueOf(value));
	}

	public static int getIntProperty(String name) {
		return Integer.valueOf(properties.getProperty(name)).intValue();
	}

	public static void setProperties(String name, String[] values) {
		StringBuffer buffer = new StringBuffer();
		for (String value : values) {
			buffer.append(value).append("@");
		}
		properties.put(name, buffer.toString());
	}

	public static String[] getProperties(String name) {
		String property = properties.get(name).toString();
		if (property.length() == 0 || property.indexOf('@') == -1) {
			return new String[] {};
		} else {
			return property.split("@");
		}
	}

	public static void save() throws FileNotFoundException, IOException {
		MainGUI.app.getSource().saveProperties();
		MainGUI.app.getDestiny().saveProperties();

		MainGUI.app.mainMenu.bookmarksMenu.saveProperties();

		setProperty("panels.orientation", String.valueOf(MainGUI.app.panels
				.getOrientation()));
		properties.store(new FileOutputStream(configurationFile),
				" MadCommander");
	}
}
