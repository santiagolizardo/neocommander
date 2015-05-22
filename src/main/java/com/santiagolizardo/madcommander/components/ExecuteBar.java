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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.SystemUtil;

public class ExecuteBar extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2866538203429884941L;

	private JComboBox<String> commands;

	private JLabel currentPath;

	private DefaultComboBoxModel<String> model;

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
