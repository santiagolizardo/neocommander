/**
 * MadCommander is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * MadCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 * or the MadCommander website <http://sourceforge.net/projects/madcommander>. 
 * 
 * @author slizardo
 * @version $Id: MadCommander.java,v 1.18 2010/01/21 17:02:48 slizardo Exp $
 */
package org.slizardo.madcommander;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.slizardo.madcommander.components.BasicToolbar;
import org.slizardo.madcommander.components.DrivesToolbar;
import org.slizardo.madcommander.components.ExecuteBar;
import org.slizardo.madcommander.components.MainMenu;
import org.slizardo.madcommander.components.Panels;
import org.slizardo.madcommander.components.ShortcutsPanel;
import org.slizardo.madcommander.components.filelisting.FileListing;
import org.slizardo.madcommander.components.filelisting.FileListingTabbed;
import org.slizardo.madcommander.components.filelisting.FileListing.Position;
import org.slizardo.madcommander.components.filelisting.model.BookmarksModel;
import org.slizardo.madcommander.config.ConfigWrapper;
import org.slizardo.madcommander.resources.images.IconFactory;


@SuppressWarnings("serial")
public class MadCommander extends JFrame {

	public final static String APP_NAME = "MadCommander";
	public final static String APP_VERSION = "1.2";
	public final static String APP_URL = "http://sourceforge.net/projects/madcommander";
	
	private static Logger logger = Logger.getLogger("Main");

	public BasicToolbar basicToolbar;

	public DrivesToolbar driveToolbar;

	public JPanel shortcutsPanel;

	public JPanel executePanel;

	public Panels panels;

	public Position currentPanel;

	public Container contentPane;

	public Container container;

	public MainMenu mainMenu;

	public FileListingTabbed leftTabs;
	public FileListingTabbed rightTabs;

	public BookmarksModel bookmarksModel;

	public MadCommander() {
		super();

		setTitle(APP_NAME.concat(" ").concat(APP_VERSION));
		setIconImage(IconFactory.newIcon("icon.gif").getImage());

		currentPanel = Position.Left;

		leftTabs = new FileListingTabbed(Position.Left);
		rightTabs = new FileListingTabbed(Position.Right);

		panels = new Panels();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				super.windowClosing(event);
				quit();
			}
		});

		bookmarksModel = new BookmarksModel();

		defineLayout();
	}

	public void init() {
		mainMenu = new MainMenu();
		setJMenuBar(mainMenu);

		setSize(ConfigWrapper.getIntProperty("app.width"), ConfigWrapper
				.getIntProperty("app.height"));
		setLocation(ConfigWrapper.getIntProperty("app.x"), ConfigWrapper
				.getIntProperty("app.y"));

		mainMenu.bookmarksMenu.loadProperties();
		panels.loadProperties();

		mainMenu.configurationMenu.loadProperties();
	}

	public void saveProperties() {
		ConfigWrapper.setIntProperty("app.width", getWidth());
		ConfigWrapper.setIntProperty("app.height", getHeight());
		ConfigWrapper.setIntProperty("app.x", getX());
		ConfigWrapper.setIntProperty("app.y", getY());

		mainMenu.configurationMenu.saveProperties();
	}

	private final void defineLayout() {
		contentPane = getContentPane();
		container = new JPanel();
		container.setLayout(new GridBagLayout());

		if (ConfigWrapper.getBooleanProperty("show.basic.toolbar"))
			addBasicToolbar(false);
		if (ConfigWrapper.getBooleanProperty("show.drives.toolbar"))
			addDrivesToolbar(false);
		addPanels();
		if (ConfigWrapper.getBooleanProperty("show.execute.panel"))
			addExecutePanel(false);
		if (ConfigWrapper.getBooleanProperty("show.shortcuts.panel"))
			addShortcutsPanel(false);

		contentPane.add(container);

		pack();
	}

	public void addBasicToolbar(boolean validate) {
		basicToolbar = new BasicToolbar();
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
		driveToolbar = new DrivesToolbar();
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
		shortcutsPanel = new ShortcutsPanel();
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

	public FileListing getDestiny() {
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

		saveProperties();
		try {
			ConfigWrapper.save();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		logger.info("Stoping main...");

		dispose();
	}
}
