package com.santiagolizardo.madcommander.config;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class ConfigData {

	public static final String DEFAULT_LANGUAGE = "en";
	
	private String language;
	private List<String> bookmarks;
	
	private Dimension windowSize;
	private Point windowPosition;
	
	public ConfigData() {
		language = DEFAULT_LANGUAGE;
		
		bookmarks = new ArrayList<>();
		
		windowSize = new Dimension(800, 600);
		windowPosition = new Point(100, 100);
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	public List<String> getBookmarks() {
		return bookmarks;
	}
	
	public void setBookmarks(List<String> bookmarks) {
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
