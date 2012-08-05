/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: ReversedOrderItem.java,v 1.5 2006/03/06 17:19:24 slizardo Exp $
 */
package org.slizardo.madcommander.menu.items;

import java.awt.Graphics;

import javax.swing.JCheckBoxMenuItem;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.resources.languages.Translator;


public class ReversedOrderItem extends JCheckBoxMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5744823152452602062L;

	public ReversedOrderItem() {
		super(Translator.text("Reversed_order"));
	}

	public void paint(Graphics g) {
		super.paint(g);

		setSelected(MainGUI.app.getSource().isReversedOrder());
	}
}
