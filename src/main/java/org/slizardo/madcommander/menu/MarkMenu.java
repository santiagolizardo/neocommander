/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: MarkMenu.java,v 1.9 2006/03/23 08:18:15 slizardo Exp $
 */
package org.slizardo.madcommander.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.slizardo.madcommander.actions.mark.MarkActionsFactory;
import org.slizardo.madcommander.resources.languages.Translator;


public class MarkMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 753419026893214433L;

	public MarkMenu() {
		super(Translator.text("Mark"));
		setMnemonic(KeyEvent.VK_M);

		JMenuItem selectGroup = new JMenuItem(MarkActionsFactory.getSelectGroupAction());
		JMenuItem unselectGroup = new JMenuItem(MarkActionsFactory.getUnselectGroupAction());
		JMenuItem selectAll = new JMenuItem(MarkActionsFactory.getSelectAllAction());
		JMenuItem unselectAll = new JMenuItem(MarkActionsFactory.getUnselectAllAction());
		JMenuItem invertSelection = new JMenuItem(MarkActionsFactory.getInvertSelectionAction());

		add(selectGroup);
		add(unselectGroup);
		addSeparator();
		add(selectAll);
		add(unselectAll);
		add(invertSelection);
	}
}
