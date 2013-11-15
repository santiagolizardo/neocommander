/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components.localized;

import javax.swing.JLabel;

import com.santiagolizardo.madcommander.resources.languages.Translator;


public class LocalizedLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 834947778773364181L;

	public LocalizedLabel(String text) {
		super(text);
	}

	public String getText() {
		return Translator._(super.getText());
	}
}
