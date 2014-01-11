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
import com.santiagolizardo.madcommander.actions.RefreshAction;
import com.santiagolizardo.madcommander.actions.SearchAction;
import com.santiagolizardo.madcommander.resources.languages.Translator;

public class CommandsMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9047001930044127824L;
	private JMenuItem searchMenuItem;
	private JMenuItem gotoUserDirMenuItem;
	private JMenuItem gotoPreviousMenuItem;
	private JMenuItem gotoNextMenuItem;
	private JMenuItem interchangePanelsMenuItem;
	private JMenuItem targetEqualSourceMenuItem;
	private JMenuItem refreshMenuItem;

	public CommandsMenu(MadCommander mainWindow) {
		super(Translator._("Commands"));
		setMnemonic(KeyEvent.VK_C);

		searchMenuItem = new JMenuItem(new SearchAction(mainWindow));

		gotoUserDirMenuItem = new JMenuItem(new HomeAction(mainWindow));

		gotoPreviousMenuItem = new JMenuItem(HistoricalActions.getPreviousAction(mainWindow));
		gotoNextMenuItem = new JMenuItem(HistoricalActions.getNextAction(mainWindow));

		interchangePanelsMenuItem = new JMenuItem(new InterchangePanelsAction(mainWindow));
		targetEqualSourceMenuItem = new JMenuItem(new EqualizePanelsAction(mainWindow));

		refreshMenuItem = new JMenuItem(new RefreshAction());
		
		add(searchMenuItem);
		addSeparator();
		add(gotoUserDirMenuItem);
		add(gotoPreviousMenuItem);
		add(gotoNextMenuItem);
		addSeparator();
		add(interchangePanelsMenuItem);
		add(targetEqualSourceMenuItem);
		add(refreshMenuItem);
	}
}
