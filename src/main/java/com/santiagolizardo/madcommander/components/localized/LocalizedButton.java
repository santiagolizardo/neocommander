/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components.localized;

import javax.swing.JButton;

import com.santiagolizardo.madcommander.resources.languages.Translator;


public class LocalizedButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4786363889379424251L;

	public LocalizedButton(String label) {
		super(label);
	}

	public String getText() {
		return Translator._(super.getText());
	}
}
