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

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.components.localized.LocalizedCheckBoxMenuItem;
import com.santiagolizardo.madcommander.resources.languages.Translator;

public class ConfigurationMenu extends JMenu implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4098318863664780142L;

	private MadCommander app;

	private JMenu show;

	private JCheckBoxMenuItem basicToolbar;
	private JCheckBoxMenuItem drivesToolbar;
	private JCheckBoxMenuItem executePanel;
	private JCheckBoxMenuItem shortcutsPanel;

	public ConfigurationMenu(MadCommander app) {
		super(Translator._("Configuration"));

		this.app = app;

		setMnemonic(KeyEvent.VK_C);

		show = new JMenu(Translator._("Show"));

		basicToolbar = new LocalizedCheckBoxMenuItem("Basic toolbar");
		basicToolbar.addActionListener(this);
		basicToolbar.setSelected(true);

		drivesToolbar = new LocalizedCheckBoxMenuItem("Drives toolbar");
		drivesToolbar.addActionListener(this);
		drivesToolbar.setSelected(true);

		executePanel = new LocalizedCheckBoxMenuItem("Execute panel");
		executePanel.addActionListener(this);
		executePanel.setSelected(true);

		shortcutsPanel = new LocalizedCheckBoxMenuItem("Shortcuts panel");
		shortcutsPanel.addActionListener(this);
		shortcutsPanel.setSelected(true);

		show.add(basicToolbar);
		show.add(drivesToolbar);
		show.add(executePanel);
		show.add(shortcutsPanel);

		add(show);
	}

	public void actionPerformed(ActionEvent ev) {
		Object source = ev.getSource();
		if (source == basicToolbar) {
			if (basicToolbar.isSelected()) {
				app.addBasicToolbar(true);
			} else {
				app.removeBasicToolbar();
			}
		} else if (source == drivesToolbar) {
			if (drivesToolbar.isSelected()) {
				app.addDrivesToolbar(true);
			} else {
				app.removeDrivesToolbar();
			}
		} else if (source == executePanel) {
			if (executePanel.isSelected()) {
				app.addExecutePanel(true);
			} else {
				app.removeExecutePanel();
			}
		} else if (source == shortcutsPanel) {
			if (shortcutsPanel.isSelected()) {
				app.addShortcutsPanel(true);
			} else {
				app.removeShortcutsPanel();
			}
		}
	}
}
