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
package com.santiagolizardo.madcommander.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.actions.BriefAction;
import com.santiagolizardo.madcommander.actions.FilterDirectoriesAction;
import com.santiagolizardo.madcommander.actions.FilterHiddensAction;
import com.santiagolizardo.madcommander.actions.FilterNoneAction;
import com.santiagolizardo.madcommander.actions.FullAction;
import com.santiagolizardo.madcommander.actions.HistoricalActions;
import com.santiagolizardo.madcommander.actions.RefreshAction;
import com.santiagolizardo.madcommander.actions.SearchAction;
import com.santiagolizardo.madcommander.resources.images.IconFactory;

public class BasicToolbar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2081100107934692827L;

	public BasicToolbar(MainWindow mainWindow) {
		super();

		setRollover(true);

		JButton refresh = new JButton(new RefreshAction());
		JButton brief = new JButton(new BriefAction(mainWindow));
		JButton full = new JButton(new FullAction(mainWindow));

		JButton prev = new JButton(
				HistoricalActions.getPreviousAction(mainWindow));
		JButton next = new JButton(HistoricalActions.getNextAction(mainWindow));

		JRadioButtonMenuItem filterNone = new JRadioButtonMenuItem(
				new FilterNoneAction(mainWindow));
		filterNone.setSelected(true);
		JRadioButtonMenuItem filterDirectories = new JRadioButtonMenuItem(
				new FilterDirectoriesAction(mainWindow));
		JRadioButtonMenuItem filterHiddens = new JRadioButtonMenuItem(
				new FilterHiddensAction(mainWindow));

		final JPopupMenu popup = new JPopupMenu();
		popup.add(filterNone);
		popup.add(filterDirectories);
		popup.add(filterHiddens);

		ButtonGroup group = new ButtonGroup();
		group.add(filterNone);
		group.add(filterDirectories);
		group.add(filterHiddens);

		JButton find = new JButton(new SearchAction(mainWindow));

		final JButton filters = new JButton("Filter",
				IconFactory.newIcon("filter.gif"));
		filters.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				popup.show(filters, 0, filters.getY() + filters.getHeight() + 1);
			}
		});

		add(prev);
		add(next);
		addSeparator();
		add(refresh);
		addSeparator();
		add(brief);
		add(full);
		addSeparator();
		add(find);
		add(filters);
	}
}
