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

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.actions.HelpAction;
import com.santiagolizardo.madcommander.components.localized.LocalizedMenuItem;
import com.santiagolizardo.madcommander.dialogs.AboutDialog;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.SystemUtil;
import com.santiagolizardo.madcommander.util.UpdateManager;


public class HelpMenu extends JMenu implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8893380914412785609L;

	private JMenuItem helpIndex;

	private LocalizedMenuItem visitJavaCommanderWebSite;

	private LocalizedMenuItem checkForUpdate;

	private LocalizedMenuItem aboutJavaCommander;

	private JFrame mainWindow;
	
	public HelpMenu(JFrame mainWindow) {
		super(Translator._("Help"));
		setMnemonic(KeyEvent.VK_H);
		
		this.mainWindow = mainWindow;

		helpIndex = new JMenuItem(new HelpAction(mainWindow));

		visitJavaCommanderWebSite = new LocalizedMenuItem(
				"Visit project website");
		visitJavaCommanderWebSite.addActionListener(this);
		visitJavaCommanderWebSite.setIcon(IconFactory.newIcon("link.png"));

		checkForUpdate = new LocalizedMenuItem("Check for updates...");
		checkForUpdate.setIcon(IconFactory.newIcon("check.png"));
		checkForUpdate.addActionListener(this);

		aboutJavaCommander = new LocalizedMenuItem("About this software");
		aboutJavaCommander.addActionListener(this);

		add(helpIndex);
		add(visitJavaCommanderWebSite);
		addSeparator();
		add(checkForUpdate);
		add(aboutJavaCommander);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		Object source = ev.getSource();

		if (source == visitJavaCommanderWebSite) {
			SystemUtil.browse(this, MainWindow.APP_URL);
		} else if (source == checkForUpdate) {
			UpdateManager.checkForUpdate();
		} else if (source == aboutJavaCommander) {
			AboutDialog aboutDialog = new AboutDialog(mainWindow);
			aboutDialog.setVisible(true);
		}
	}
}
