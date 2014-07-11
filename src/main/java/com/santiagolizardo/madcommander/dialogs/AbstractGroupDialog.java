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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.santiagolizardo.madcommander.components.localized.LocalizedButton;
import com.santiagolizardo.madcommander.resources.languages.Translator;


public abstract class AbstractGroupDialog extends AbstractDialog implements
		ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7833199314557405992L;

	private JComboBox<String> type;

	private JTextField searchPattern;

	private JButton okButton;
	private JButton cancelButton;

	private JCheckBox caseSensitive;

	private JPanel mainPanel;

	private JPanel buttonsPanel;

	public AbstractGroupDialog(String title, JFrame mainWindow) {
		super();

		setTitle(title);
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		String[] types = new String[] { "Contains", "Starts with", "Ends with",
				"Is" };
		type = new JComboBox<String>(types);

		searchPattern = new JTextField(17);
		searchPattern.setText(".txt");

		okButton = new LocalizedButton("Ok");
		okButton.addActionListener(this);
		getRootPane().setDefaultButton(okButton);

		cancelButton = new LocalizedButton("Cancel");
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
			dispose();
		} else {
			dispose();
		}
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
