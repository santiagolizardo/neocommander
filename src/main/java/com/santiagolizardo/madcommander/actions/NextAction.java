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

import javax.swing.AbstractAction;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;

class NextAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2403279687627821070L;

	private MainWindow mainWindow;

	public NextAction(MainWindow mainWindow) {
		super(Translator.tr("Next"), IconFactory.newIcon("next.gif"));

		setEnabled(false);

		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String next = mainWindow.getSource().historical.getNext();
		mainWindow.getSource().setPath(next);
	}
}
