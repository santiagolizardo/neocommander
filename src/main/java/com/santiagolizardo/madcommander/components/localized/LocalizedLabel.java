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
package com.santiagolizardo.madcommander.components.localized;

import javax.swing.JLabel;

import com.santiagolizardo.madcommander.resources.languages.Translator;


public class LocalizedLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 834947778773364181L;

	public LocalizedLabel(String text) {
		super(text);
	}

	public String getText() {
		return Translator._(super.getText());
	}
}
