/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: ConfigurationMenu.java,v 1.12 2010/01/22 17:57:54 slizardo Exp $
 */
package org.slizardo.madcommander.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;

import org.slizardo.madcommander.MadCommander;
import org.slizardo.madcommander.components.localized.LocalizedCheckBoxMenuItem;
import org.slizardo.madcommander.config.ConfigWrapper;
import org.slizardo.madcommander.config.Configurable;
import org.slizardo.madcommander.resources.languages.Translator;


public class ConfigurationMenu extends JMenu implements ActionListener,
		Configurable {

	private MadCommander app;

	private JMenu show;

	private JCheckBoxMenuItem basicToolbar;
	private JCheckBoxMenuItem drivesToolbar;
	private JCheckBoxMenuItem executePanel;
	private JCheckBoxMenuItem shortcutsPanel;

	public ConfigurationMenu(MadCommander app) {
		super(Translator.text("Configuration"));

		this.app = app;

		setMnemonic(KeyEvent.VK_C);

		show = new JMenu(Translator.text("Show"));

		basicToolbar = new LocalizedCheckBoxMenuItem("Basic_toolbar");
		basicToolbar.addActionListener(this);

		drivesToolbar = new LocalizedCheckBoxMenuItem("Drives_toolbar");
		drivesToolbar.addActionListener(this);

		executePanel = new LocalizedCheckBoxMenuItem("Execute_panel");
		executePanel.addActionListener(this);

		shortcutsPanel = new LocalizedCheckBoxMenuItem("Shortcuts_panel");
		shortcutsPanel.addActionListener(this);

		show.add(basicToolbar);
		show.add(drivesToolbar);
		show.add(executePanel);
		show.add(shortcutsPanel);

		add(show);
	}

	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
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

	public void loadProperties() {
		basicToolbar.setSelected(ConfigWrapper
				.getBooleanProperty("show.basic.toolbar"));
		drivesToolbar.setSelected(ConfigWrapper
				.getBooleanProperty("show.drives.toolbar"));
		executePanel.setSelected(ConfigWrapper
				.getBooleanProperty("show.execute.panel"));
		shortcutsPanel.setSelected(ConfigWrapper
				.getBooleanProperty("show.shortcuts.panel"));
	}

	public void saveProperties() {
		ConfigWrapper.setBooleanProperty("show.basic.toolbar", basicToolbar
				.isSelected());
		ConfigWrapper.setBooleanProperty("show.drives.toolbar", drivesToolbar
				.isSelected());
		ConfigWrapper.setBooleanProperty("show.execute.panel", executePanel
				.isSelected());
		ConfigWrapper.setBooleanProperty("show.shortcuts.panel", shortcutsPanel
				.isSelected());
	}
}
