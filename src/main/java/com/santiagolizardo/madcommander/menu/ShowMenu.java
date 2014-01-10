/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSplitPane;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.actions.BriefAction;
import com.santiagolizardo.madcommander.actions.FilterDirectoriesAction;
import com.santiagolizardo.madcommander.actions.FilterHiddensAction;
import com.santiagolizardo.madcommander.actions.FilterNoneAction;
import com.santiagolizardo.madcommander.actions.FullAction;
import com.santiagolizardo.madcommander.actions.HorizontalSplitAction;
import com.santiagolizardo.madcommander.actions.RefreshAction;
import com.santiagolizardo.madcommander.components.filelisting.filters.FilterCustom;
import com.santiagolizardo.madcommander.components.localized.LocalizedRadioButtonMenuItem;
import com.santiagolizardo.madcommander.menu.items.ReversedOrderItem;
import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;

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

	private MadCommander mainWindow;

	public ShowMenu(MadCommander mainWindow) {
		super(Translator._("Show"));
		setMnemonic(KeyEvent.VK_S);

		this.mainWindow = mainWindow;

		brief = new JRadioButtonMenuItem(new BriefAction(mainWindow));
		full = new JRadioButtonMenuItem(new FullAction(mainWindow));
		full.setSelected(true);

		ButtonGroup group = new ButtonGroup();
		group.add(brief);
		group.add(full);

		horizontalSplit = new JCheckBoxMenuItem(new HorizontalSplitAction(mainWindow));
		horizontalSplit.addActionListener(this);
		int panelsOrientation = JSplitPane.HORIZONTAL_SPLIT;
		if (panelsOrientation == JSplitPane.HORIZONTAL_SPLIT) {
			horizontalSplit.setSelected(false);
		} else {
			horizontalSplit.setSelected(true);
		}

		filterNone = new JRadioButtonMenuItem(new FilterNoneAction(mainWindow));
		filterNone.setSelected(true);
		filterDirectories = new JRadioButtonMenuItem(
				new FilterDirectoriesAction(mainWindow));
		filterHiddens = new JRadioButtonMenuItem(new FilterHiddensAction(
				mainWindow));
		filterCustom = new LocalizedRadioButtonMenuItem("Filter custom...");
		filterCustom.addActionListener(this);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(filterNone);
		buttonGroup.add(filterDirectories);
		buttonGroup.add(filterHiddens);
		buttonGroup.add(filterCustom);

		reversedOrder = new ReversedOrderItem(mainWindow);
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
			mainWindow.getSource().setReversedOrder(reversedOrder.isSelected());
		} else if (source == filterCustom) {
			String pattern = DialogFactory.showInputDialog(mainWindow,
					"Pattern:");
			if (pattern == null)
				return;
			mainWindow.getSource().setFilter(new FilterCustom(pattern));
			mainWindow.getSource().refreshFiles();
		}
	}
}
