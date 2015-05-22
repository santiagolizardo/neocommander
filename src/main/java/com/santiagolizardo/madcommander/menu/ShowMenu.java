/**
 * This file is part of MadCommander, a file manager with two panels.
 *
 * MadCommander is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MadCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MadCommander.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSplitPane;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.actions.BriefAction;
import com.santiagolizardo.madcommander.actions.FilterDirectoriesAction;
import com.santiagolizardo.madcommander.actions.FilterHiddensAction;
import com.santiagolizardo.madcommander.actions.FilterNoneAction;
import com.santiagolizardo.madcommander.actions.FullAction;
import com.santiagolizardo.madcommander.actions.HorizontalSplitAction;
import com.santiagolizardo.madcommander.components.filelisting.filters.FilterCustom;
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

	private JRadioButtonMenuItem filterNone;

	private JRadioButtonMenuItem filterDirectories;

	private JRadioButtonMenuItem filterHiddens;

	private JRadioButtonMenuItem filterCustom;

	private MainWindow mainWindow;

	public ShowMenu(MainWindow mainWindow) {
		super(Translator.tr("Show"));
		setMnemonic(KeyEvent.VK_S);

		this.mainWindow = mainWindow;

		brief = new JRadioButtonMenuItem(new BriefAction(mainWindow));
		full = new JRadioButtonMenuItem(new FullAction(mainWindow));
		full.setSelected(true);

		ButtonGroup group = new ButtonGroup();
		group.add(brief);
		group.add(full);

		horizontalSplit = new JCheckBoxMenuItem(new HorizontalSplitAction(
				mainWindow));
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
		filterCustom = new JRadioButtonMenuItem(Translator.tr("Filter custom..."));
		filterCustom.addActionListener(this);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(filterNone);
		buttonGroup.add(filterDirectories);
		buttonGroup.add(filterHiddens);
		buttonGroup.add(filterCustom);

		reversedOrder = new ReversedOrderItem(mainWindow);

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
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		Object source = ev.getSource();
		if (source == filterCustom) {
			String pattern = DialogFactory.showInputDialog(mainWindow,
					"Pattern:");
			if (pattern == null)
				return;
			mainWindow.getSource().setFilter(new FilterCustom(pattern));
			mainWindow.getSource().refreshFiles();
		}
	}
}
