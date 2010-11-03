/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: PathLabel.java,v 1.1.1.1 2006/01/15 00:34:39 slizardo Exp $
 */
package org.slizardo.madcommander.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class PathLabel extends JLabel {

	public PathLabel() {
		super();

		setOpaque(true);
		setBackground(new Color(0, 0, 75));
		setForeground(Color.WHITE);
		setFont(new Font("Arial", Font.BOLD, 12));
		setBorder(BorderFactory.createEmptyBorder(0, 7, 0, 7));
	}
}
