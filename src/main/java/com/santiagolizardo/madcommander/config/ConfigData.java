package com.santiagolizardo.madcommander.config;

import java.util.ArrayList;
import java.util.List;

public class ConfigData {

	public static final String DEFAULT_LANGUAGE = "en";
	
	private String language;
	private List<String> bookmarks;
	
	public ConfigData() {
		language = DEFAULT_LANGUAGE;
		bookmarks = new ArrayList<>();
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
}
