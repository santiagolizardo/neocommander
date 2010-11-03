/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: FileListingTabbed.java,v 1.5 2010/01/22 10:03:54 slizardo Exp $
 */
package org.slizardo.madcommander.components.filelisting;

import java.io.File;

import javax.swing.JTabbedPane;

import org.slizardo.madcommander.components.filelisting.FileListing.Position;



public class FileListingTabbed extends JTabbedPane {

	private Position panelID;

	public FileListingTabbed(Position panelID) {
		super();

		this.panelID = panelID;

		addFileListingTab();
	}

	public void addFileListingTab() {
		FileListing fileListing = new FileListing(panelID);

		File file = new File(fileListing.getPath());
		addTab(file.getName(), fileListing);
	}

	public void removeCurrentTab() {
		remove(getSelectedIndex());
	}
}
