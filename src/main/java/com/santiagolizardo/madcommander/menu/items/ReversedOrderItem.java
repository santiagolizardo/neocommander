/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.menu.items;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.resources.languages.Translator;

public class ReversedOrderItem extends JCheckBoxMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5744823152452602062L;

	private MadCommander mainWindow;

	public ReversedOrderItem(final MadCommander mainWindow) {
		super(Translator._("Reversed order"));

		this.mainWindow = mainWindow;

		addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				mainWindow.getSource().setReversedOrder(isSelected());
			}
		});
	}
}
