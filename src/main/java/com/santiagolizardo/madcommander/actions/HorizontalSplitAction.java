/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JSplitPane;

import com.santiagolizardo.madcommander.MainGUI;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;


public class HorizontalSplitAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2941630640706613242L;

	public HorizontalSplitAction() {
		super(Translator._("Horizontal_split"), IconFactory
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
