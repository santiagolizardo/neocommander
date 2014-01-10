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
 */
package com.santiagolizardo.madcommander;

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

import com.santiagolizardo.madcommander.components.BasicToolbar;
import com.santiagolizardo.madcommander.components.DrivesToolbar;
import com.santiagolizardo.madcommander.components.ExecuteBar;
import com.santiagolizardo.madcommander.components.MainMenu;
import com.santiagolizardo.madcommander.components.Panels;
import com.santiagolizardo.madcommander.components.ShortcutsPanel;
import com.santiagolizardo.madcommander.components.filelisting.FileListing;
import com.santiagolizardo.madcommander.components.filelisting.FileListing.Position;
import com.santiagolizardo.madcommander.components.filelisting.FileListingTabbed;
import com.santiagolizardo.madcommander.components.filelisting.model.BookmarksModel;
import com.santiagolizardo.madcommander.resources.images.IconFactory;

@SuppressWarnings("serial")
public class MadCommander extends JFrame {

	public final static String APP_NAME = "MadCommander";
	public final static String APP_VERSION = "1.4.2";
	public final static String APP_URL = "http://sourceforge.net/projects/madcommander";

	private static final Logger LOGGER = Logger.getLogger(MadCommander.class
			.getName());

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

		setTitle(APP_NAME + " " + APP_VERSION);
		setIconImage(IconFactory.newIcon("icon.png").getImage());

		currentPanel = Position.Left;

		leftTabs = new FileListingTabbed(this, Position.Left);
		rightTabs = new FileListingTabbed(this, Position.Right);

		panels = new Panels(this);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				super.windowClosing(event);
				quit();
			}
		});

		defineLayout();
	}

	public void init() {
		mainMenu = new MainMenu(this);
		bookmarksModel = new BookmarksModel(mainMenu.bookmarksMenu.bookmarks);
		setJMenuBar(mainMenu);

		panels.loadProperties();

		setSize(640, 480);
		setLocation(80, 80);

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

		pack();
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

		LOGGER.info("Stoping main...");

		dispose();
	}
}
