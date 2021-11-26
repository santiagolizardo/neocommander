/*
  This file is part of MadCommander, a file manager with two panels.

  MadCommander is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  MadCommander is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with MadCommander.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.dialogs;

import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;
import com.santiagolizardo.madcommander.util.io.FileUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class UnpackDialog extends AbstractDialog implements ActionListener {

	
	private final JButton okButton;
	private final JButton browse;
	private final JLabel fileText;
	private final JTextField unpackOn;
	private final DefaultListModel<String> model;
	private final JList<String> list;
	private final JScrollPane scroll;

	private final String fileName;

	public UnpackDialog(String fileName) {
		super();

		File file = new File(fileName);
		File dir = new File(FileUtil.extractDirPart(file));

		this.fileName = fileName;

		setTitle(Translator.tr("Unpack..."));

		okButton = new JButton("Ok");
		okButton.requestFocus();
		okButton.addActionListener(this);
		getRootPane().setDefaultButton(okButton);

		final JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setCurrentDirectory(dir);

		fileText = new JLabel(fileName);
		unpackOn = new JTextField(20);
		unpackOn.setText(dir.getAbsolutePath());

		browse = new JButton("...");
		browse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int ret = chooser.showDialog((JComponent) event.getSource(),
						"Select directory...");
				if (ret == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					unpackOn.setText(file.getAbsolutePath());
				}
			}
		});

		model = new DefaultListModel<>();
		list = new JList<>(model);
		scroll = new JScrollPane(list);
		Dimension scrollSize = new Dimension(350, 70);
		scroll.setMinimumSize(scrollSize);
		scroll.setPreferredSize(scrollSize);

		try {
			try (ZipFile zipFile = new ZipFile(fileName)) {
				Enumeration<?> e = zipFile.entries();
				while (e.hasMoreElements()) {
					ZipEntry entry = (ZipEntry) e.nextElement();
					model.addElement(entry.getName());
				}
			}
		} catch (IOException io) {
			io.printStackTrace();
		}

		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		defineLayout();
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		File file = new File(unpackOn.getText());
		if (!file.isDirectory()) {
			DialogFactory.showErrorMessage(this,
					"You must select a correct directory first.");
			return;
		}
		try {
			ZipInputStream in = new ZipInputStream(
					new FileInputStream(fileName));
			ZipEntry entry = in.getNextEntry();
			while (entry != null) {
				String newFileName = unpackOn.getText() + File.separator
						+ entry.getName();
				if (entry.isDirectory()) {
					File dir = new File(newFileName);
					dir.mkdirs();
				} else {
					OutputStream os = new FileOutputStream(newFileName);
					byte[] buf = new byte[1024];
					int len = 0;
					while ((len = in.read(buf)) > 0) {
						os.write(buf, 0, len);
					}
					os.close();
				}
				entry = in.getNextEntry();
			}
			in.close();
		} catch (IOException io) {
			io.printStackTrace();
		}

		dispose();
	}

	private void defineLayout() {
		Container contentPane = getContentPane();
		SpringLayout layout = new SpringLayout();

		layout.putConstraint(SpringLayout.WEST, fileText, 5, SpringLayout.WEST,
				contentPane);
		layout.putConstraint(SpringLayout.NORTH, fileText, 5,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.NORTH, okButton, 5,
				SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.EAST, okButton, 0, SpringLayout.EAST,
				scroll);

		layout.putConstraint(SpringLayout.WEST, unpackOn, 5, SpringLayout.WEST,
				contentPane);
		layout.putConstraint(SpringLayout.NORTH, unpackOn, 5,
				SpringLayout.SOUTH, fileText);

		layout.putConstraint(SpringLayout.WEST, browse, 5, SpringLayout.EAST,
				unpackOn);
		layout.putConstraint(SpringLayout.NORTH, browse, 0, SpringLayout.NORTH,
				unpackOn);

		layout.putConstraint(SpringLayout.WEST, scroll, 5, SpringLayout.WEST,
				contentPane);
		layout.putConstraint(SpringLayout.NORTH, scroll, 5, SpringLayout.SOUTH,
				unpackOn);
		layout.putConstraint(SpringLayout.EAST, contentPane, 5,
				SpringLayout.EAST, scroll);
		layout.putConstraint(SpringLayout.SOUTH, contentPane, 5,
				SpringLayout.SOUTH, scroll);

		setLayout(layout);

		add(okButton);
		add(fileText);
		add(unpackOn);
		add(browse);
		add(scroll);

		pack();
	}
}
