/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: HorizontalSplitAction.java,v 1.1 2006/03/21 17:25:37 slizardo Exp $
 */
package org.slizardo.madcommander.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JSplitPane;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;


public class HorizontalSplitAction extends AbstractAction {

	public HorizontalSplitAction() {
		super(Translator.text("Horizontal_split"), IconFactory
				.newIcon("horizontal_split.png"));
	}

	public void actionPerformed(ActionEvent event) {
		JCheckBoxMenuItem horizontalSplit = (JCheckBoxMenuItem) event
				.getSource();
		int orientation = (horizontalSplit.isSelected() ? JSplitPane.VERTICAL_SPLIT
				: JSplitPane.HORIZONTAL_SPLIT);
		MainGUI.app.panels.changeOrientation(orientation);
	}
}
