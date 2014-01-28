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
package com.santiagolizardo.madcommander.menu.items;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.resources.languages.Translator;

public class ReversedOrderItem extends JCheckBoxMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5744823152452602062L;

	private MadCommander mainWindow;

	public ReversedOrderItem(final MadCommander mainWindow) {
		super(Translator._("Reversed order"));

		this.mainWindow = mainWindow;

		addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				mainWindow.getSource().setReversedOrder(isSelected());
			}
		});
	}
}
