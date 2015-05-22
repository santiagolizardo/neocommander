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
package com.santiagolizardo.madcommander.dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.util.PackTypes;
import com.santiagolizardo.madcommander.dialogs.progressive.PackProgressDialog;
import com.santiagolizardo.madcommander.resources.languages.Translator;

public class PackDialog extends AbstractDialog implements ActionListener {

	private static final long serialVersionUID = -3805898808819160470L;

	private JTextField fileName;

	private JLabel extension;

	private JRadioButton zip;

	private JRadioButton jar;

	private JRadioButton gzip;

	private JButton compressButton;

	private JPanel panel;

	private MainWindow mainWindow;

	public PackDialog(MainWindow mainWindow) {
		super();

		this.mainWindow = mainWindow;

		setTitle(Translator.tr("Pack..."));

		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		final String path = mainWindow.getSource().getPath();

		fileName = new JTextField(20);
		fileName.setText(path + File.separator + "archive");

		extension = new JLabel(".zip");

		panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Compression type "));
		panel.setLayout(new GridLayout(3, 1));

		ButtonGroup group = new ButtonGroup();

		zip = new JRadioButton("ZIP");
		zip.setSelected(true);
		zip.addActionListener((ActionEvent ev) -> {
			extension.setText(".zip");
		});
		group.add(zip);

		jar = new JRadioButton("JAR");
		jar.addActionListener((ActionEvent ev) -> {
			extension.setText(".jar");
		});
		group.add(jar);
		
		gzip = new JRadioButton("GZIP");
		gzip.addActionListener((ActionEvent ev) -> {
			extension.setText(".gzip");
		});
		group.add(gzip);

		panel.add(zip);
		panel.add(jar);
		panel.add(gzip);

		defineLayout();
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		PackTypes type;
		if (zip.isSelected())
			type = PackTypes.ZIP;
		else if (jar.isSelected())
			type = PackTypes.JAR;
		else
			type = PackTypes.GZIP;

		PackProgressDialog progressDialog = new PackProgressDialog(mainWindow,
				fileName.getText(), type);
		progressDialog.begin();

		dispose();
	}

	private void defineLayout() {
		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		add(new JLabel(Translator.tr("Filename:")), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridheight = 2;
		add(fileName, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 2;
		add(extension, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 3;
		add(panel, c);

		compressButton = new JButton(Translator.tr("Compress"));
		compressButton.addActionListener(this);
		getRootPane().setDefaultButton(compressButton);

		c.fill = GridBagConstraints.NONE;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 4;
		c.insets = new Insets(5, 5, 5, 5);
		add(compressButton, c);

		pack();
	}
}
