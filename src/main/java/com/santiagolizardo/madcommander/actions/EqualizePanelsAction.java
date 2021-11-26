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

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.resources.languages.Translator;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EqualizePanelsAction extends AbstractAction {

	private final MainWindow mainWindow;

	public EqualizePanelsAction(MainWindow mainWindow) {
		super(Translator.tr("Target equal to source"));

		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String srcPath = mainWindow.getSource().getPath();
		mainWindow.getDestination().setPath(srcPath);
	}
}
