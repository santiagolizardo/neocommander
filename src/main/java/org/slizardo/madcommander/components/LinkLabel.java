/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: LinkLabel.java,v 1.5 2006/03/06 17:19:22 slizardo Exp $
 */
package org.slizardo.madcommander.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import org.slizardo.madcommander.util.SystemUtil;


public class LinkLabel extends JLabel {

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
