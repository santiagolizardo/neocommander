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
package com.santiagolizardo.madcommander.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class PathLabel extends JLabel {

	private static final long serialVersionUID = 2723429904792232585L;

	public PathLabel() {
		super();

		setOpaque(true);
		setBackground(new Color(0, 0, 75));
		setForeground(Color.WHITE);
		setFont(new Font("Arial", Font.BOLD, 12));
		setBorder(BorderFactory.createEmptyBorder(0, 7, 0, 7));
	}
}
