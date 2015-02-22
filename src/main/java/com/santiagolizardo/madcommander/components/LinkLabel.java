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
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import com.santiagolizardo.madcommander.util.SystemUtil;

public class LinkLabel extends JLabel {

	private static final long serialVersionUID = 8488272749111338872L;

	public LinkLabel(String url) {
		super();

		final String finalURL = url;

		// final Cursor normal = Cursor.getDefaultCursor();
		final Cursor hand = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);

		setText(url);
		setForeground(Color.BLUE);
		setCursor(hand);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent ev) {
				setCursor(hand);
			}

			@Override
			public void mouseExited(MouseEvent ev) {
				setCursor(hand);
			}

			@Override
			public void mouseClicked(MouseEvent ev) {
				SystemUtil.browse(getParent(), finalURL);
			}
		});
	}
}
