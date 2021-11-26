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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public abstract class AbstractGroupDialog extends AbstractDialog implements
		ActionListener {



	private final JComboBox<String> type;

	private final JTextField searchPattern;

	private final JButton okButton;
	private final JButton cancelButton;

	private final JCheckBox caseSensitive;

	private final JPanel mainPanel;

	private final JPanel buttonsPanel;

	public AbstractGroupDialog(String title, JFrame mainWindow) {
		super();

		setTitle(title);
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		String[] types = new String[] { "Contains", "Starts with", "Ends with",
				"Is" };
		type = new JComboBox<>(types);

		searchPattern = new JTextField(17);
		searchPattern.setText(".txt");

		okButton = new JButton(Translator.tr("Ok"));
		okButton.addActionListener(this);
		getRootPane().setDefaultButton(okButton);

		cancelButton = new JButton(Translator.tr("Cancel"));
		cancelButton.addActionListener(this);

		caseSensitive = new JCheckBox(Translator.tr("Case sensitive"));

		mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(type, BorderLayout.WEST);
		mainPanel.add(searchPattern, BorderLayout.EAST);
		mainPanel.add(caseSensitive, BorderLayout.SOUTH);

		buttonsPanel = new JPanel();
		buttonsPanel.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));
		buttonsPanel.setLayout(new BorderLayout());
		buttonsPanel.add(okButton, BorderLayout.WEST);
		buttonsPanel.add(cancelButton, BorderLayout.EAST);

		defineLayout();
		setLocationRelativeTo(mainWindow);
	}

	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == okButton) {
			applyPattern(type.getSelectedItem().toString(), searchPattern
					.getText(), caseSensitive.isSelected());
		}
		dispose();
	}

	protected abstract void applyPattern(String type, String searchPattern,
			boolean caseSensitive);

	private void defineLayout() {
		setLayout(new BorderLayout());

		add(mainPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);

		pack();
	}
}
