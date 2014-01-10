/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components.filelisting;

import java.io.File;

import javax.swing.JTabbedPane;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.components.filelisting.FileListing.Position;

public class FileListingTabbed extends JTabbedPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6010265431790349455L;
	private Position panelID;

	private MadCommander mainWindow;

	public FileListingTabbed(MadCommander mainWindow, Position panelID) {
		super();

		this.panelID = panelID;

		this.mainWindow = mainWindow;

		addFileListingTab();
	}

	public void addFileListingTab() {
		FileListing fileListing = new FileListing(mainWindow, panelID);

		File file = new File(fileListing.getPath());
		addTab(file.getName(), fileListing);
	}

	public void removeCurrentTab() {
		remove(getSelectedIndex());
	}
}
