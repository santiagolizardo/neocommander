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
