/*
  This file is part of MadCommander, a file manager with two panels.

  MadCommander is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  MadCommander is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with MadCommander.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.menu;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.actions.*;
import com.santiagolizardo.madcommander.components.filelisting.filters.FilterCustom;
import com.santiagolizardo.madcommander.menu.items.ReversedOrderItem;
import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ShowMenu extends JMenu implements ActionListener {



	private final JRadioButtonMenuItem brief;

	private final JRadioButtonMenuItem full;

	private final JCheckBoxMenuItem horizontalSplit;

	private final JCheckBoxMenuItem reversedOrder;

	private final JRadioButtonMenuItem filterNone;

	private final JRadioButtonMenuItem filterDirectories;

	private final JRadioButtonMenuItem filterHiddens;

	private final JRadioButtonMenuItem filterCustom;

	private final MainWindow mainWindow;

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
        horizontalSplit.setSelected(false);

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
