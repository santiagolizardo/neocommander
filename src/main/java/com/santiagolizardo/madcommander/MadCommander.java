/**
 * MadCommander is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or any later version.
 *
 * MadCommander is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>
 * or the MadCommander website <http://sourceforge.net/projects/madcommander>.
 *
 * @author slizardo
 */
package com.santiagolizardo.madcommander;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.santiagolizardo.madcommander.components.BasicToolbar;
import com.santiagolizardo.madcommander.components.DrivesToolbar;
import com.santiagolizardo.madcommander.components.ExecuteBar;
import com.santiagolizardo.madcommander.components.MainMenu;
import com.santiagolizardo.madcommander.components.Panels;
import com.santiagolizardo.madcommander.components.ShortcutsPanel;
import com.santiagolizardo.madcommander.components.filelisting.FileListing;
import com.santiagolizardo.madcommander.components.filelisting.FileListing.Position;
import com.santiagolizardo.madcommander.components.filelisting.FileListingTabbed;
import com.santiagolizardo.madcommander.config.ConfigData;
import com.santiagolizardo.madcommander.config.ConfigHandler;
import com.santiagolizardo.madcommander.resources.images.IconFactory;

@SuppressWarnings("serial")
public class MadCommander extends JFrame {

	public final static String APP_NAME = "MadCommander";
	public final static String APP_VERSION = "1.4.4";
	public final static String APP_URL = "http://sourceforge.net/projects/madcommander";

	private static final Logger LOGGER = Logger.getLogger(MadCommander.class
			.getName());

	private BasicToolbar basicToolbar;

	private DrivesToolbar driveToolbar;

	private ShortcutsPanel shortcutsPanel;
	private JPanel executePanel;

	private Panels panels;

	public Position currentPanel;

	public Container contentPane;

	private Container container;

	private MainMenu mainMenu;

	public FileListingTabbed leftTabs;
	public FileListingTabbed rightTabs;

	private ConfigData configData;

	public MadCommander() {
		super();

		setTitle(APP_NAME + " " + APP_VERSION);
		setIconImage(IconFactory.newIcon("icon.png").getImage());

		currentPanel = Position.Left;

		leftTabs = new FileListingTabbed(this, Position.Left);
		rightTabs = new FileListingTabbed(this, Position.Right);

		panels = new Panels(this);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent ev) {
				super.windowClosing(ev);
				quit();
			}
		});

		defineLayout();
	}

	public void init() {
		mainMenu = new MainMenu(this);
		setJMenuBar(mainMenu);

		refreshButtons();

		panels.loadProperties();

		setSize(configData.getWindowSize());
		setLocation(configData.getWindowPosition());
	}

	private final void defineLayout() {
		contentPane = getContentPane();
		container = new JPanel();
		container.setLayout(new GridBagLayout());

		addBasicToolbar(false);
		addDrivesToolbar(false);
		addPanels();
		addExecutePanel(false);
		addShortcutsPanel(false);

		contentPane.add(container);
	}

	public void refreshButtons() {
		List<File> selectedFiles = getSource().getSelectedFiles();

		mainMenu.refreshButtons(selectedFiles);
		shortcutsPanel.refreshButtons(selectedFiles);
	}

	public void addBasicToolbar(boolean validate) {
		basicToolbar = new BasicToolbar(this);
		contentPane.add(basicToolbar, BorderLayout.NORTH);
		if (validate) {
			contentPane.validate();
		}
	}

	public void removeBasicToolbar() {
		contentPane.remove(basicToolbar);
		basicToolbar = null;
		contentPane.validate();
	}

	public void addDrivesToolbar(boolean validate) {
		driveToolbar = new DrivesToolbar(this);
		contentPane.add(driveToolbar, BorderLayout.SOUTH);
		if (validate) {
			contentPane.validate();
		}
	}

	public void removeDrivesToolbar() {
		contentPane.remove(driveToolbar);
		driveToolbar = null;
		contentPane.validate();
	}

	public void addPanels() {
		GridBagConstraints c = new GridBagConstraints();
		// w
		c.weightx = 1;
		// h
		c.weighty = 1;
		// %w %h
		c.fill = GridBagConstraints.BOTH;
		// x
		c.gridwidth = GridBagConstraints.REMAINDER;
		// y
		c.gridheight = 1;
		container.add(panels, c);
	}

	public void addExecutePanel(boolean validate) {
		GridBagConstraints c = new GridBagConstraints();
		// w
		c.weightx = 1;
		// h
		c.weighty = 0.01;
		// %w %h
		c.fill = GridBagConstraints.HORIZONTAL;
		// x
		c.gridwidth = GridBagConstraints.REMAINDER;
		// y
		c.gridheight = 2;
		executePanel = new ExecuteBar();
		container.add(executePanel, c);
		if (validate) {
			container.validate();
		}
	}

	public void removeExecutePanel() {
		container.remove(executePanel);
		executePanel = null;
		container.validate();
	}

	public void addShortcutsPanel(boolean validate) {
		GridBagConstraints c = new GridBagConstraints();
		// w
		c.weightx = 1;
		// h
		c.weighty = 0.01;
		// %w %h
		c.fill = GridBagConstraints.HORIZONTAL;
		// x
		c.gridwidth = GridBagConstraints.REMAINDER;
		// y
		c.gridheight = 3;
		shortcutsPanel = new ShortcutsPanel(this);
		container.add(shortcutsPanel, c);
		if (validate) {
			container.validate();
		}
	}

	public void removeShortcutsPanel() {
		container.remove(shortcutsPanel);
		shortcutsPanel = null;
		container.validate();
	}

	public FileListing getSource() {
		return (currentPanel == Position.Left ? getCurrentTab(leftTabs)
				: getCurrentTab(rightTabs));
	}

	public FileListing getDestination() {
		return (currentPanel == Position.Right ? getCurrentTab(leftTabs)
				: getCurrentTab(rightTabs));
	}

	public FileListing getCurrentTab(JTabbedPane tabbedPane) {
		if (tabbedPane.getTabCount() > 0) {
			return (FileListing) tabbedPane.getSelectedComponent();
		} else {
			return null;
		}
	}

	public void quit() {
		setVisible(false);

		configData.setWindowSize(getSize());
		configData.setWindowPosition(getLocation());

		ConfigHandler configHandler = new ConfigHandler();
		configHandler.save(configData);

		LOGGER.info("Stoping main...");

		dispose();
	}

	public void changeOrientation(int orientation) {
		panels.changeOrientation(orientation);
	}

	public MainMenu getMainMenu() {
		return mainMenu;
	}

	public void setConfigData(ConfigData configData) {
		this.configData = configData;
	}

	public ConfigData getConfigData() {
		return configData;
	}
}