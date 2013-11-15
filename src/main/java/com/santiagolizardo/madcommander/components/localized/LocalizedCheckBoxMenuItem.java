/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components.localized;

import javax.swing.JCheckBoxMenuItem;

import com.santiagolizardo.madcommander.resources.languages.Translator;


public class LocalizedCheckBoxMenuItem extends JCheckBoxMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3393460277864419269L;

	public LocalizedCheckBoxMenuItem(String label) {
		super(label);
	}

	public String getText() {
		return Translator._(super.getText());
	}
}
