/**
 * This file is part of MadCommander, a file manager with two panels.
 *
 * MadCommander is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * MadCommander is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * MadCommander. If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.dialogs.changeattributes;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.dialogs.AbstractDialog;
import com.santiagolizardo.madcommander.resources.languages.Translator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class ChangeAttributesDialog extends AbstractDialog implements
		ActionListener {


	private final AttributesPanel attributesPanel;

	private final JButton okButton;
	private final JButton cancelButton;

	private final MainWindow mainWindow;

	public ChangeAttributesDialog(MainWindow mainWindow) {
		super();

		this.mainWindow = mainWindow;

		setTitle(Translator.tr("Change attributes..."));
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		attributesPanel = new AttributesPanel();

		okButton = new JButton(Translator.tr("Ok"));
		okButton.addActionListener(this);
		getRootPane().setDefaultButton(okButton);

		cancelButton = new JButton(Translator.tr("Cancel"));
		cancelButton.addActionListener(this);

		defineLayout();
		setSize(240, 350);
		setLocationRelativeTo(mainWindow);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		Object source = ev.getSource();
		if (source == okButton) {
			List<File> selectedFiles = mainWindow.getSource()
					.getSelectedFiles();

			for (File file : selectedFiles) {
				file.setReadable(attributesPanel.isReadPermissionSelected());
				file.setWritable(attributesPanel.isWritePermissionSelected());
				file.setExecutable(attributesPanel.isExecutePermissionSelected());

				if (attributesPanel.isReadOnlyPermissionSelected() ){
					file.setReadOnly();
				}
			}
			mainWindow.getSource().refreshFiles();
			dispose();
		} else if (source == cancelButton) {
			dispose();
		}
	}

	private void defineLayout() {
		Container contentPane = getContentPane();

		SpringLayout layout = new SpringLayout();
		layout.putConstraint(SpringLayout.WEST, attributesPanel, 5,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, attributesPanel, 5,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, okButton, 5, SpringLayout.WEST,
				contentPane);
		layout.putConstraint(SpringLayout.NORTH, okButton, 5,
				SpringLayout.SOUTH, attributesPanel);

		layout.putConstraint(SpringLayout.WEST, cancelButton, 5,
				SpringLayout.EAST, okButton);
		layout.putConstraint(SpringLayout.NORTH, cancelButton, 5,
				SpringLayout.SOUTH, attributesPanel);

		contentPane.setLayout(layout);

		contentPane.add(attributesPanel);

		contentPane.add(okButton);
		contentPane.add(cancelButton);

		pack();
	}
}
