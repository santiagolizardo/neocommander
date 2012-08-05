/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: ShowMenu.java,v 1.13 2010/01/21 17:02:48 slizardo Exp $
 */
package org.slizardo.madcommander.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSplitPane;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.actions.BriefAction;
import org.slizardo.madcommander.actions.FilterDirectoriesAction;
import org.slizardo.madcommander.actions.FilterHiddensAction;
import org.slizardo.madcommander.actions.FilterNoneAction;
import org.slizardo.madcommander.actions.FullAction;
import org.slizardo.madcommander.actions.HorizontalSplitAction;
import org.slizardo.madcommander.actions.RefreshAction;
import org.slizardo.madcommander.components.filelisting.filters.FilterCustom;
import org.slizardo.madcommander.components.localized.LocalizedRadioButtonMenuItem;
import org.slizardo.madcommander.menu.items.ReversedOrderItem;
import org.slizardo.madcommander.resources.languages.Translator;
import org.slizardo.madcommander.util.gui.DialogFactory;

public class ShowMenu extends JMenu implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8123523245285098209L;

	private JRadioButtonMenuItem brief;

	private JRadioButtonMenuItem full;

	private JCheckBoxMenuItem horizontalSplit;

	private JCheckBoxMenuItem reversedOrder;

	private JMenuItem refresh;

	private JRadioButtonMenuItem filterNone;

	private JRadioButtonMenuItem filterDirectories;

	private JRadioButtonMenuItem filterHiddens;

	private JRadioButtonMenuItem filterCustom;

	public ShowMenu() {
		super(Translator.text("Show"));
		setMnemonic(KeyEvent.VK_S);

		brief = new JRadioButtonMenuItem(new BriefAction());
		full = new JRadioButtonMenuItem(new FullAction());
		full.setSelected(true);

		ButtonGroup group = new ButtonGroup();
		group.add(brief);
		group.add(full);

		horizontalSplit = new JCheckBoxMenuItem(new HorizontalSplitAction());
		horizontalSplit.addActionListener(this);
		int panelsOrientation = JSplitPane.HORIZONTAL_SPLIT;
		if (panelsOrientation == JSplitPane.HORIZONTAL_SPLIT) {
			horizontalSplit.setSelected(false);
		} else {
			horizontalSplit.setSelected(true);
		}

		filterNone = new JRadioButtonMenuItem(new FilterNoneAction());
		filterNone.setSelected(true);
		filterDirectories = new JRadioButtonMenuItem(
				new FilterDirectoriesAction());
		filterHiddens = new JRadioButtonMenuItem(new FilterHiddensAction());
		filterCustom = new LocalizedRadioButtonMenuItem("Filter_custom...");
		filterCustom.addActionListener(this);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(filterNone);
		buttonGroup.add(filterDirectories);
		buttonGroup.add(filterHiddens);
		buttonGroup.add(filterCustom);

		reversedOrder = new ReversedOrderItem();
		reversedOrder.addActionListener(this);

		refresh = new JMenuItem(new RefreshAction());

		add(brief);
		add(full);
		add(horizontalSplit);
		addSeparator();
		add(filterNone);
		add(filterDirectories);
		add(filterHiddens);
		add(filterCustom);
		addSeparator();
		add(reversedOrder);
		add(refresh);
	}

	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == reversedOrder) {
			MainGUI.app.getSource()
					.setReversedOrder(reversedOrder.isSelected());
		} else if (source == filterCustom) {
			String pattern = DialogFactory.showInputDialog(MainGUI.app,
					"Pattern:");
			if (pattern == null)
				return;
			MainGUI.app.getSource().setFilter(new FilterCustom(pattern));
			MainGUI.app.getSource().refreshFiles();
		}
	}
}
