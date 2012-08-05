/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: LocalizedCheckBoxMenuItem.java,v 1.2 2006/03/06 17:19:22 slizardo Exp $
 */
package org.slizardo.madcommander.components.localized;

import javax.swing.JCheckBoxMenuItem;

import org.slizardo.madcommander.resources.languages.Translator;


public class LocalizedCheckBoxMenuItem extends JCheckBoxMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3393460277864419269L;

	public LocalizedCheckBoxMenuItem(String label) {
		super(label);
	}

	public String getText() {
		return Translator.text(super.getText());
	}
}
