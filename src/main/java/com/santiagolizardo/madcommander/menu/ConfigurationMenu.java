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

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.resources.languages.Translator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ConfigurationMenu extends JMenu implements ActionListener {


	private final MainWindow mainWindow;

	private final JMenu show;

	private final JCheckBoxMenuItem basicToolbar;
	private final JCheckBoxMenuItem executePanel;
	private final JCheckBoxMenuItem shortcutsPanel;

	public ConfigurationMenu(MainWindow mainWindow) {
		super(Translator.tr("Configuration"));

		this.mainWindow = mainWindow;

		setMnemonic(KeyEvent.VK_C);

		show = new JMenu(Translator.tr("Show"));

		basicToolbar = new JCheckBoxMenuItem(Translator.tr("Basic toolbar"));
		basicToolbar.addActionListener(this);
		basicToolbar.setSelected(true);

		executePanel = new JCheckBoxMenuItem(Translator.tr("Execute panel"));
		executePanel.addActionListener(this);
		executePanel.setSelected(true);

		shortcutsPanel = new JCheckBoxMenuItem(Translator.tr("Shortcuts panel"));
		shortcutsPanel.addActionListener(this);
		shortcutsPanel.setSelected(true);

		show.add(basicToolbar);
		show.add(executePanel);
		show.add(shortcutsPanel);

		add(show);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		Object source = ev.getSource();
		if (source == basicToolbar) {
			if (basicToolbar.isSelected()) {
				mainWindow.addBasicToolbar(true);
			} else {
				mainWindow.removeBasicToolbar();
			}
		} else if (source == executePanel) {
			if (executePanel.isSelected()) {
				mainWindow.addExecutePanel(true);
			} else {
				mainWindow.removeExecutePanel();
			}
		} else if (source == shortcutsPanel) {
			if (shortcutsPanel.isSelected()) {
				mainWindow.addShortcutsPanel(true);
			} else {
				mainWindow.removeShortcutsPanel();
			}
		}
	}
}
