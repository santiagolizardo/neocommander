/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.menu.items;

import java.awt.Graphics;

import javax.swing.JCheckBoxMenuItem;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.resources.languages.Translator;

public class ReversedOrderItem extends JCheckBoxMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5744823152452602062L;

	private MadCommander mainWindow;

	public ReversedOrderItem(MadCommander mainWindow) {
		super(Translator._("Reversed order"));

		this.mainWindow = mainWindow;
	}

	public void paint(Graphics g) {
		super.paint(g);

		setSelected(mainWindow.getSource().isReversedOrder());
	}
}
