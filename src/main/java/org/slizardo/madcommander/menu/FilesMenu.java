/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: FilesMenu.java,v 1.15 2010/01/22 17:57:54 slizardo Exp $
 */
package org.slizardo.madcommander.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.apache.commons.io.FileUtils;
import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.actions.fileops.PackAction;
import org.slizardo.madcommander.components.filelisting.FileListing;
import org.slizardo.madcommander.components.localized.LocalizedMenuItem;
import org.slizardo.madcommander.dialogs.UnpackDialog;
import org.slizardo.madcommander.dialogs.changeattributes.ChangeAttributesDialog;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;
import org.slizardo.madcommander.util.gui.DialogFactory;


public class FilesMenu extends JMenu implements ActionListener {

	private LocalizedMenuItem changeAttributes;

	private JMenuItem pack;

	private LocalizedMenuItem unpack;

	private LocalizedMenuItem compareByContent;

	private JMenuItem print;

	private LocalizedMenuItem quit;

	public FilesMenu() {
		super(Translator.text("Files"));
		setMnemonic(KeyEvent.VK_F);

		changeAttributes = new LocalizedMenuItem("Change_attributes...");
		changeAttributes.addActionListener(this);
		pack = new JMenuItem(new PackAction());
		unpack = new LocalizedMenuItem("Unpack...");
		unpack.addActionListener(this);
		compareByContent = new LocalizedMenuItem("Compare_by_content...");
		compareByContent.addActionListener(this);

		print = new JMenuItem(Translator.text("Print file list..."));
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

	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == changeAttributes) {
			ChangeAttributesDialog changeAttributesDialog = new ChangeAttributesDialog();
			changeAttributesDialog.setVisible(true);
		} else if (source == unpack) {
			ArrayList<File> list = MainGUI.app.getSource().getSelectedFiles();
			if (list.size() == 1) {
				File file = list.get(0);
				StringBuffer buffer = new StringBuffer();
				buffer.append(MainGUI.app.getSource().getPath());
				buffer.append(File.separator);
				buffer.append(file.getName());
				UnpackDialog unpackDialog = new UnpackDialog(buffer.toString());
				unpackDialog.setVisible(true);
			} else {
				DialogFactory.showErrorMessage(MainGUI.app,
						"Only pick one file to unpack at time.");
			}
		} else if (source == print) {
			FileListing listing = MainGUI.app.getSource();
			listing.print();
		} else if (source == quit) {
			MainGUI.app.quit();
		} else if (source == compareByContent) {
			File file1 = MainGUI.app.getSource().getSelectedFiles().get(0);
			File file2 = MainGUI.app.getDestiny().getSelectedFiles().get(0);
			try {
				if (FileUtils.contentEquals(file1, file2) == true) {
					DialogFactory.showInformationMessage(MainGUI.app,
							"The content of the files is identical.");
				} else
					DialogFactory.showInformationMessage(MainGUI.app,
							"The content of the files is not the same.");
			} catch (Exception e) {
				DialogFactory.showErrorMessage(MainGUI.app,
						"Could not compare the contents of the files.");
			}
		}
	}
}
