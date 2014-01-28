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
import java.io.File;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.apache.commons.io.FileUtils;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.actions.fileops.PackAction;
import com.santiagolizardo.madcommander.components.filelisting.FileListing;
import com.santiagolizardo.madcommander.components.localized.LocalizedMenuItem;
import com.santiagolizardo.madcommander.dialogs.UnpackDialog;
import com.santiagolizardo.madcommander.dialogs.changeattributes.ChangeAttributesDialog;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;
import java.io.IOException;

public class FilesMenu extends JMenu implements ActionListener {

	private static final long serialVersionUID = -3242803672077641559L;

	private LocalizedMenuItem changeAttributesMenuItem;
	private JMenuItem packMenuItem;
	private LocalizedMenuItem unpackMenuItem;
	private LocalizedMenuItem compareByContent;
	private JMenuItem printMenuItem;
	private LocalizedMenuItem quitMenuItem;

	private MainWindow mainWindow;

	public FilesMenu(MainWindow mainWindow) {
		super(Translator._("Files"));
		setMnemonic(KeyEvent.VK_F);

		this.mainWindow = mainWindow;

		changeAttributesMenuItem = new LocalizedMenuItem("Change attributes...");
		changeAttributesMenuItem.addActionListener(this);
		packMenuItem = new JMenuItem(new PackAction(mainWindow));
		unpackMenuItem = new LocalizedMenuItem("Unpack...");
		unpackMenuItem.addActionListener(this);
		compareByContent = new LocalizedMenuItem("Compare by content...");
		compareByContent.addActionListener(this);

		printMenuItem = new JMenuItem(Translator._("Print file list..."));
		printMenuItem.setIcon(IconFactory.newIcon("print.png"));
		printMenuItem.addActionListener(this);

		quitMenuItem = new LocalizedMenuItem("Quit");
		quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,
				KeyEvent.ALT_MASK));
		quitMenuItem.addActionListener(this);

		add(changeAttributesMenuItem);
		add(packMenuItem);
		add(unpackMenuItem);
		add(compareByContent);
		add(printMenuItem);
		addSeparator();
		add(quitMenuItem);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		Object source = ev.getSource();
		if (source == changeAttributesMenuItem) {
			ChangeAttributesDialog changeAttributesDialog = new ChangeAttributesDialog(
					mainWindow);
			changeAttributesDialog.setVisible(true);
		} else if (source == unpackMenuItem) {
			List<File> list = mainWindow.getSource().getSelectedFiles();
			if (list.size() == 1) {
				File file = list.get(0);
				StringBuilder buffer = new StringBuilder();
				buffer.append(mainWindow.getSource().getPath());
				buffer.append(File.separator);
				buffer.append(file.getName());
				UnpackDialog unpackDialog = new UnpackDialog(buffer.toString());
				unpackDialog.setVisible(true);
			} else {
				DialogFactory.showErrorMessage(mainWindow,
						"Only pick one file to unpack at time.");
			}
		} else if (source == printMenuItem) {
			FileListing listing = mainWindow.getSource();
			listing.print();
		} else if (source == quitMenuItem) {
			mainWindow.quit();
		} else if (source == compareByContent) {
			List<File> files1 = mainWindow.getSource().getSelectedFiles();
			List<File> files2 = mainWindow.getDestination().getSelectedFiles();
			if (files1.isEmpty() || files2.isEmpty()) {
				DialogFactory
						.showInformationMessage(mainWindow,
								"Please select a file on each panel first to compare their contents.");
				return;
			}
			File file1 = files1.get(0);
			File file2 = files2.get(0);
			try {
				if (FileUtils.contentEquals(file1, file2) == true) {
					DialogFactory.showInformationMessage(mainWindow,
							"The content of the files is identical.");
				} else
					DialogFactory.showInformationMessage(mainWindow,
							"The content of the files is not the same.");
			} catch (IOException e) {
				DialogFactory.showErrorMessage(mainWindow,
						"Could not compare the contents of the files.");
			}
		}
	}

	public void refreshButtons(List<File> selectedFiles) {
		changeAttributesMenuItem.setEnabled(1 == selectedFiles.size());
		packMenuItem.setEnabled(selectedFiles.size() > 0);
		unpackMenuItem.setEnabled(1 == selectedFiles.size()
				&& selectedFiles.get(0).isFile());
	}
}
