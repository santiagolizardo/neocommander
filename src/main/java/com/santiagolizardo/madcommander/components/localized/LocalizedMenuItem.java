/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components.localized;

import javax.swing.JMenuItem;


public class LocalizedMenuItem extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3681966305092756332L;

	public LocalizedMenuItem(String label) {
		super(label);
	}
//
//	public String getText() {
//		return Translator.text(super.getText());
//	}
}
