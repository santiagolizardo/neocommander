/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.actions.mark.MarkActionsFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;

public class MarkMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 753419026893214433L;

	public MarkMenu(MadCommander mainWindow) {
		super(Translator._("Mark"));
		setMnemonic(KeyEvent.VK_M);

		JMenuItem selectGroup = new JMenuItem(
				MarkActionsFactory.getSelectGroupAction(mainWindow));
		JMenuItem unselectGroup = new JMenuItem(
				MarkActionsFactory.getUnselectGroupAction(mainWindow));
		JMenuItem selectAll = new JMenuItem(
				MarkActionsFactory.getSelectAllAction(mainWindow));
		JMenuItem unselectAll = new JMenuItem(
				MarkActionsFactory.getUnselectAllAction(mainWindow));
		JMenuItem invertSelection = new JMenuItem(
				MarkActionsFactory.getInvertSelectionAction(mainWindow));

		add(selectGroup);
		add(unselectGroup);
		addSeparator();
		add(selectAll);
		add(unselectAll);
		add(invertSelection);
	}
}
