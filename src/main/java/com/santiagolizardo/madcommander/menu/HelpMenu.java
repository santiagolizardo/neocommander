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

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.santiagolizardo.madcommander.MadCommander;
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

	public HelpMenu() {
		super(Translator._("Help"));
		setMnemonic(KeyEvent.VK_H);

		helpIndex = new JMenuItem(new HelpAction());

		visitJavaCommanderWebSite = new LocalizedMenuItem(
				"Visit_MadCommander_web_site");
		visitJavaCommanderWebSite.addActionListener(this);
		visitJavaCommanderWebSite.setIcon(IconFactory.newIcon("link.png"));

		checkForUpdate = new LocalizedMenuItem("Check_for_update...");
		checkForUpdate.setIcon(IconFactory.newIcon("check.png"));
		checkForUpdate.addActionListener(this);

		aboutJavaCommander = new LocalizedMenuItem("About_MadCommander");
		aboutJavaCommander.addActionListener(this);

		add(helpIndex);
		add(visitJavaCommanderWebSite);
		addSeparator();
		add(checkForUpdate);
		add(aboutJavaCommander);
	}

	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();

		if (source == visitJavaCommanderWebSite) {
			SystemUtil.browse(this, MadCommander.APP_URL);
		} else if (source == checkForUpdate) {
			UpdateManager.checkForUpdate();
		} else if (source == aboutJavaCommander) {
			AboutDialog aboutDialog = new AboutDialog();
			aboutDialog.setVisible(true);
		}
	}
}
