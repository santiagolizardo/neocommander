/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components.filelisting;

import com.santiagolizardo.madcommander.resources.languages.Translator;

public class SelectableColumnHeader {

	private String text;

	private boolean active;

	public SelectableColumnHeader(String text, boolean active) {
		this.text = text;
		this.active = active;
	}

	public SelectableColumnHeader(String text) {
		this(text, false);
	}

	public String getText() {
		return Translator._(text);
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}
}
