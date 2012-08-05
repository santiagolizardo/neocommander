/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: CommandsMenu.java,v 1.13 2010/01/26 17:57:11 slizardo Exp $
 */
package org.slizardo.madcommander.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.slizardo.madcommander.actions.EqualizePanelsAction;
import org.slizardo.madcommander.actions.HistoricalActions;
import org.slizardo.madcommander.actions.HomeAction;
import org.slizardo.madcommander.actions.InterchangePanelsAction;
import org.slizardo.madcommander.actions.SearchAction;
import org.slizardo.madcommander.resources.languages.Translator;


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

	public CommandsMenu() {
		super(Translator.text("Commands"));
		setMnemonic(KeyEvent.VK_C);

		search = new JMenuItem(new SearchAction());

		gotoUserDir = new JMenuItem(new HomeAction());

		gotoPrevious = new JMenuItem(HistoricalActions.getPreviousAction());
		gotoNext = new JMenuItem(HistoricalActions.getNextAction());

		interchangePanels = new JMenuItem(new InterchangePanelsAction());
		targetEqualSource = new JMenuItem(new EqualizePanelsAction());

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
