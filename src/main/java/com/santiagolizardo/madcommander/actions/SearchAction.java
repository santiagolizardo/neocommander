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
package com.santiagolizardo.madcommander.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.dialogs.SearchDialog;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;

public class SearchAction extends AbstractAction {

	private static final long serialVersionUID = -1957915845327720772L;

	private MainWindow mainWindow;

	public SearchAction(MainWindow mainWindow) {
		super(Translator.tr("Search..."), IconFactory.newIcon("find.gif"));

		this.mainWindow = mainWindow;

		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_F7, KeyEvent.ALT_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		SearchDialog searchDialog = new SearchDialog(mainWindow);
		searchDialog.setVisible(true);
	}
}
