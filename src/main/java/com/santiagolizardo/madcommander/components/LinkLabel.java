/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import com.santiagolizardo.madcommander.util.SystemUtil;


public class LinkLabel extends JLabel {

	/**
	 * 
	 */
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
			public void mouseEntered(MouseEvent event) {
				setCursor(hand);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				setCursor(hand);
			}

			public void mouseClicked(MouseEvent event) {
				super.mouseClicked(event);
				SystemUtil.browse(getParent(), finalURL);
			}
		});
	}
}
