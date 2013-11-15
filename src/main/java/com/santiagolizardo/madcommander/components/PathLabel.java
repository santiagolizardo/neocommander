/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class PathLabel extends JLabel {

	/**
	 * 
	 */
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
