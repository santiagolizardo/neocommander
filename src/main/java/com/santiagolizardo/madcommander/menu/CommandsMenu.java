/*
  This file is part of MadCommander, a file manager with two panels.

  MadCommander is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  MadCommander is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with MadCommander.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.menu;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.actions.*;
import com.santiagolizardo.madcommander.resources.languages.Translator;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class CommandsMenu extends JMenu {

	
	private final JMenuItem searchMenuItem;
	private final JMenuItem gotoUserDirMenuItem;
	private final JMenuItem gotoPreviousMenuItem;
	private final JMenuItem gotoNextMenuItem;
	private final JMenuItem interchangePanelsMenuItem;
	private final JMenuItem targetEqualSourceMenuItem;
	private final JMenuItem refreshMenuItem;

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
