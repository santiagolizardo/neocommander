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
import com.santiagolizardo.madcommander.actions.mark.MarkActionsFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MarkMenu extends JMenu {



	public MarkMenu(MainWindow mainWindow) {
		super(Translator.tr("Mark"));
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
