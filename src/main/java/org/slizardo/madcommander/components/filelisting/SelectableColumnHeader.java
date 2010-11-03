/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: SelectableColumnHeader.java,v 1.1 2010/01/21 17:02:45 slizardo Exp $
 */
package org.slizardo.madcommander.components.filelisting;

import org.slizardo.madcommander.resources.languages.Translator;

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
		return Translator.text(text);
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}
}
