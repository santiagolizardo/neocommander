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
import java.io.File;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.apache.commons.io.FileUtils;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.actions.fileops.PackAction;
import com.santiagolizardo.madcommander.components.filelisting.FileListing;
import com.santiagolizardo.madcommander.components.localized.LocalizedMenuItem;
import com.santiagolizardo.madcommander.dialogs.UnpackDialog;
import com.santiagolizardo.madcommander.dialogs.changeattributes.ChangeAttributesDialog;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;

public class FilesMenu extends JMenu implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3242803672077641559L;

	private LocalizedMenuItem changeAttributes;

	private JMenuItem pack;

	private LocalizedMenuItem unpack;

	private LocalizedMenuItem compareByContent;

	private JMenuItem print;

	private LocalizedMenuItem quit;
	
	private MadCommander mainWindow;
	
	public FilesMenu(MadCommander mainWindow) {
		super(Translator._("Files"));
		setMnemonic(KeyEvent.VK_F);

		this.mainWindow = mainWindow;
		
		changeAttributes = new LocalizedMenuItem("Change attributes...");
		changeAttributes.addActionListener(this);
		pack = new JMenuItem(new PackAction(mainWindow));
		unpack = new LocalizedMenuItem("Unpack...");
		unpack.addActionListener(this);
		compareByContent = new LocalizedMenuItem("Compare by content...");
		compareByContent.addActionListener(this);

		print = new JMenuItem(Translator._("Print file list..."));
		print.setIcon(IconFactory.newIcon("print.png"));
		print.addActionListener(this);

		quit = new LocalizedMenuItem("Quit");
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,
				KeyEvent.ALT_MASK));
		quit.addActionListener(this);

		add(changeAttributes);
		add(pack);
		add(unpack);
		add(compareByContent);
		add(print);
		addSeparator();
		add(quit);
	}

	public void actionPerformed(ActionEvent ev) {
		Object source = ev.getSource();
		if (source == changeAttributes) {
			ChangeAttributesDialog changeAttributesDialog = new ChangeAttributesDialog(mainWindow);
			changeAttributesDialog.setVisible(true);
		} else if (source == unpack) {
			List<File> list = mainWindow.getSource().getSelectedFiles();
			if (list.size() == 1) {
				File file = list.get(0);
				StringBuffer buffer = new StringBuffer();
				buffer.append(mainWindow.getSource().getPath());
				buffer.append(File.separator);
				buffer.append(file.getName());
				UnpackDialog unpackDialog = new UnpackDialog(buffer.toString());
				unpackDialog.setVisible(true);
			} else {
				DialogFactory.showErrorMessage(mainWindow,
						"Only pick one file to unpack at time.");
			}
		} else if (source == print) {
			FileListing listing = mainWindow.getSource();
			listing.print();
		} else if (source == quit) {
			mainWindow.quit();
		} else if (source == compareByContent) {
			List<File> files1 = mainWindow.getSource().getSelectedFiles();
			List<File> files2 = mainWindow.getDestiny().getSelectedFiles();
			if (files1.size() == 0 || files2.size() == 0) {
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
			} catch (Exception e) {
				DialogFactory.showErrorMessage(mainWindow,
						"Could not compare the contents of the files.");
			}
		}
	}
}
