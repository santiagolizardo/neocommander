/**
 * This file is part of MadCommander, a file manager with two panels.
 *
 * MadCommander is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MadCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MadCommander.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.actions.EqualizePanelsAction;
import com.santiagolizardo.madcommander.actions.HistoricalActions;
import com.santiagolizardo.madcommander.actions.HomeAction;
import com.santiagolizardo.madcommander.actions.InterchangePanelsAction;
import com.santiagolizardo.madcommander.actions.RefreshAction;
import com.santiagolizardo.madcommander.actions.SearchAction;
import com.santiagolizardo.madcommander.resources.languages.Translator;

public class CommandsMenu extends JMenu {

	private static final long serialVersionUID = -9047001930044127824L;
	
	private JMenuItem searchMenuItem;
	private JMenuItem gotoUserDirMenuItem;
	private JMenuItem gotoPreviousMenuItem;
	private JMenuItem gotoNextMenuItem;
	private JMenuItem interchangePanelsMenuItem;
	private JMenuItem targetEqualSourceMenuItem;
	private JMenuItem refreshMenuItem;

	public CommandsMenu(MainWindow mainWindow) {
		super(Translator.tr("Commands"));
		setMnemonic(KeyEvent.VK_C);

		searchMenuItem = new JMenuItem(new SearchAction(mainWindow));

		gotoUserDirMenuItem = new JMenuItem(new HomeAction(mainWindow));

		gotoPreviousMenuItem = new JMenuItem(HistoricalActions.getPreviousAction(mainWindow));
		gotoNextMenuItem = new JMenuItem(HistoricalActions.getNextAction(mainWindow));

		interchangePanelsMenuItem = new JMenuItem(new InterchangePanelsAction(mainWindow));
		targetEqualSourceMenuItem = new JMenuItem(new EqualizePanelsAction(mainWindow));

		refreshMenuItem = new JMenuItem(new RefreshAction(mainWindow));
		
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
