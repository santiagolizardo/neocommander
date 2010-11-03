/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: LocalizedMenuItem.java,v 1.2 2006/03/06 17:19:22 slizardo Exp $
 */
package org.slizardo.madcommander.components.localized;

import javax.swing.JMenuItem;

import org.slizardo.madcommander.resources.languages.Translator;


public class LocalizedMenuItem extends JMenuItem {

	public LocalizedMenuItem(String label) {
		super(label);
	}

	public String getText() {
		return Translator.text(super.getText());
	}
}
