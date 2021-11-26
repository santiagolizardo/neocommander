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
package com.santiagolizardo.madcommander.components;

import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.SystemUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExecuteBar extends JPanel implements ActionListener {



	private final JComboBox<String> commands;

	private final JLabel currentPath;

	private final DefaultComboBoxModel<String> model;

	public ExecuteBar() {
		super();

		model = new DefaultComboBoxModel<>();

		commands = new JComboBox<>(model);
		commands.setEditable(true);
		commands.addActionListener(this);

		currentPath = new JLabel(Translator.tr("Execute"));

		defineLayout();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if ("comboBoxChanged".equals(command)) {
			String text = commands.getSelectedItem().toString();
			commands.setSelectedItem("");
			model.addElement(text);
			SystemUtil.execute(this, text);
		}
	}

	private void defineLayout() {
		SpringLayout spring = new SpringLayout();
		JPanel panel = new JPanel();
		spring.putConstraint(SpringLayout.WEST, currentPath, 5,
				SpringLayout.WEST, panel);
		spring.putConstraint(SpringLayout.NORTH, currentPath, 5,
				SpringLayout.NORTH, panel);
		spring.putConstraint(SpringLayout.WEST, commands, 5, SpringLayout.EAST,
				currentPath);
		spring.putConstraint(SpringLayout.NORTH, commands, 5,
				SpringLayout.NORTH, panel);

		panel.setLayout(spring);

		panel.add(currentPath);
		panel.add(commands);

		setLayout(new BorderLayout());
		add(panel);
		validate();
	}
}
