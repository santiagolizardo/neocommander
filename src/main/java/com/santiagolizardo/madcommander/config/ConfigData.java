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
package com.santiagolizardo.madcommander.config;

import java.awt.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class ConfigData {

	public static final String DEFAULT_LANGUAGE = "en";
	
	/**
	 * TODO Check if userLanguage is supported by the app.
	 */
	private static String getDefaultLanguage() {
		String userLanguage = System.getProperty("user.language");
		return ( null == userLanguage ? DEFAULT_LANGUAGE : userLanguage );
	}
	
	private String language;
	private Set<String> bookmarks;
	
	private Dimension windowSize;
	private Point windowPosition;
	
	public ConfigData() {
		language = ConfigData.getDefaultLanguage();
		
		bookmarks = new LinkedHashSet<>();
		
		windowSize = new Dimension(800, 600);
		windowPosition = new Point(100, 100);
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	public Set<String> getBookmarks() {
		return bookmarks;
	}
	
	public void setBookmarks(Set<String> bookmarks) {
		this.bookmarks = bookmarks;
	}

	public Dimension getWindowSize() {
		return windowSize;
	}

	public void setWindowSize(Dimension windowSize) {
		this.windowSize = windowSize;
	}
	
	public Point getWindowPosition() {
		return windowPosition;
	}

	public void setWindowPosition(Point windowPosition) {
		this.windowPosition = windowPosition;
	}
}
