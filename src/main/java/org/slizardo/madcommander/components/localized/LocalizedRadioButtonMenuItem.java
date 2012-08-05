/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: LocalizedRadioButtonMenuItem.java,v 1.2 2006/03/06 17:19:22 slizardo Exp $
 */
package org.slizardo.madcommander.components.localized;

import javax.swing.JRadioButtonMenuItem;

import org.slizardo.madcommander.resources.languages.Translator;


public class LocalizedRadioButtonMenuItem extends JRadioButtonMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8249523894693631489L;

	public LocalizedRadioButtonMenuItem(String label) {
		super(label);
	}

	public String getText() {
		return Translator.text(super.getText());
	}
}
