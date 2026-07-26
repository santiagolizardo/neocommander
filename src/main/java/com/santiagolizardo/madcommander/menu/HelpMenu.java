/*
 * Copyright (C) 2026 Santiago Lizardo
 *
 * This file is part of NeoCommander.
 *
 * NeoCommander is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NeoCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.santiagolizardo.madcommander.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.santiagolizardo.madcommander.AppConstants;
import com.santiagolizardo.madcommander.dialogs.AboutDialog;
import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.SystemUtil;
import com.santiagolizardo.madcommander.util.UpdateManager;

public class HelpMenu extends JMenu implements ActionListener {

	private final JMenuItem visitJavaCommanderWebSite;
	private final JMenuItem checkForUpdate;
	private final JMenuItem aboutJavaCommander;

	private final JFrame mainWindow;

	public HelpMenu(JFrame mainWindow) {
		super(Translator.tr("Help"));
		setMnemonic(KeyEvent.VK_H);

		this.mainWindow = mainWindow;

		visitJavaCommanderWebSite = new JMenuItem(Translator.tr("Visit project website"));
		visitJavaCommanderWebSite.addActionListener(this);

		checkForUpdate = new JMenuItem(Translator.tr("Check for updates..."));
		checkForUpdate.addActionListener(this);

		aboutJavaCommander = new JMenuItem(Translator.tr("About this software"));
		aboutJavaCommander.addActionListener(this);

		add(visitJavaCommanderWebSite);
		addSeparator();
		add(checkForUpdate);
		add(aboutJavaCommander);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		Object source = ev.getSource();

		if (source == visitJavaCommanderWebSite) {
			SystemUtil.browse(this, AppConstants.APP_URL);
		} else if (source == checkForUpdate) {
			UpdateManager.checkForUpdate();
		} else if (source == aboutJavaCommander) {
			AboutDialog aboutDialog = new AboutDialog(mainWindow);
			aboutDialog.setVisible(true);
		}
	}
}
