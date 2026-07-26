/*
 * Copyright (C) 2026 Santiago Lizardo
 *
 * This file is part of NeoCommander.
 *
 * NeoCommander is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NeoCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.santiagolizardo.madcommander.config;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.util.prefs.Preferences;
import java.util.logging.Logger;

import com.santiagolizardo.madcommander.util.ListsUtils;

public class ConfigHandler {

	private static final Logger logger = Logger.getLogger(ConfigHandler.class.getName());

	private final Preferences prefs = Preferences.userRoot().node("neocommander");

	public void save(ConfigData configData) {
		prefs.put("bookmarks", String.join(File.pathSeparator, configData.getBookmarks()));
		prefs.put("language", configData.getLanguage());

		prefs.putInt("window.width", configData.getWindowSize().width);
		prefs.putInt("window.height", configData.getWindowSize().height);

		prefs.putInt("window.top", configData.getWindowPosition().y);
		prefs.putInt("window.left", configData.getWindowPosition().x);

		try {
			prefs.flush();
		} catch (Exception e) {
			logger.warning(e.getMessage());
		}
	}

	public ConfigData read() {
		var configData = new ConfigData();

		var bookmarksProp = prefs.get("bookmarks", "");
		if (!bookmarksProp.isEmpty()) {
			configData.getBookmarks().addAll(ListsUtils.explode(File.pathSeparator, bookmarksProp));
		}

		var language = prefs.get("language", null);
		if (null != language) {
			configData.setLanguage(language);
		}

		var defaultSize = configData.getWindowSize();
		var windowSize = new Dimension(
				prefs.getInt("window.width", defaultSize.width),
				prefs.getInt("window.height", defaultSize.height));
		configData.setWindowSize(windowSize);

		var defaultPos = configData.getWindowPosition();
		var windowPosition = new Point(
				prefs.getInt("window.left", defaultPos.x),
				prefs.getInt("window.top", defaultPos.y));
		configData.setWindowPosition(windowPosition);

		return configData;
	}
}
