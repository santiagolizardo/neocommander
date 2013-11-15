/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components.localized;

import javax.swing.JRadioButtonMenuItem;

import com.santiagolizardo.madcommander.resources.languages.Translator;


public class LocalizedRadioButtonMenuItem extends JRadioButtonMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8249523894693631489L;

	public LocalizedRadioButtonMenuItem(String label) {
		super(label);
	}

	public String getText() {
		return Translator._(super.getText());
	}
}
