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

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.components.filelisting.filters.FilterHiddens;
import com.santiagolizardo.madcommander.resources.languages.Translator;

public class FilterHiddensAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6269733626952310453L;

	private MadCommander mainWindow;

	public FilterHiddensAction(MadCommander mainWindow) {
		super(Translator._("Filter hiddens"));

		this.mainWindow = mainWindow;
	}

	public void actionPerformed(ActionEvent event) {
		mainWindow.getSource().setFilter(new FilterHiddens());
		mainWindow.getSource().refreshFiles();
	}
}
