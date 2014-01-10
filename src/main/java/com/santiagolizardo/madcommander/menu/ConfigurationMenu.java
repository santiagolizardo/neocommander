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
