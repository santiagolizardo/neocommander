/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: LocalizedLabel.java,v 1.2 2006/03/06 17:19:22 slizardo Exp $
 */
package org.slizardo.madcommander.components.localized;

import javax.swing.JLabel;

import org.slizardo.madcommander.resources.languages.Translator;


public class LocalizedLabel extends JLabel {

	public LocalizedLabel(String text) {
		super(text);
	}

	public String getText() {
		return Translator.text(super.getText());
	}
}
