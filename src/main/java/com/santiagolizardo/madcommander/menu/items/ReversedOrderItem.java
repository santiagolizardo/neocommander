/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.menu.items;

import java.awt.Graphics;

import javax.swing.JCheckBoxMenuItem;

import com.santiagolizardo.madcommander.MainGUI;
import com.santiagolizardo.madcommander.resources.languages.Translator;


public class ReversedOrderItem extends JCheckBoxMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5744823152452602062L;

	public ReversedOrderItem() {
		super(Translator._("Reversed_order"));
	}

	public void paint(Graphics g) {
		super.paint(g);

		setSelected(MainGUI.app.getSource().isReversedOrder());
	}
}
