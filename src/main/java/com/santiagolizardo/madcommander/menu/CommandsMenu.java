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
import com.santiagolizardo.madcommander.actions.EqualizePanelsAction;
import com.santiagolizardo.madcommander.actions.HistoricalActions;
import com.santiagolizardo.madcommander.actions.HomeAction;
import com.santiagolizardo.madcommander.actions.InterchangePanelsAction;
import com.santiagolizardo.madcommander.actions.SearchAction;
import com.santiagolizardo.madcommander.resources.languages.Translator;

public class CommandsMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9047001930044127824L;
	private JMenuItem search;
	private JMenuItem gotoUserDir;
	private JMenuItem gotoPrevious;
	private JMenuItem gotoNext;
	private JMenuItem interchangePanels;
	private JMenuItem targetEqualSource;

	public CommandsMenu(MadCommander mainWindow) {
		super(Translator._("Commands"));
		setMnemonic(KeyEvent.VK_C);

		search = new JMenuItem(new SearchAction(mainWindow));

		gotoUserDir = new JMenuItem(new HomeAction(mainWindow));

		gotoPrevious = new JMenuItem(HistoricalActions.getPreviousAction(mainWindow));
		gotoNext = new JMenuItem(HistoricalActions.getNextAction(mainWindow));

		interchangePanels = new JMenuItem(new InterchangePanelsAction(mainWindow));
		targetEqualSource = new JMenuItem(new EqualizePanelsAction(mainWindow));

		add(search);
		addSeparator();
		add(gotoUserDir);
		add(gotoPrevious);
		add(gotoNext);
		addSeparator();
		add(interchangePanels);
		add(targetEqualSource);
	}
}
