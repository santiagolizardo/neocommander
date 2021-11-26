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
package com.santiagolizardo.madcommander.dialogs.search;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class AdvancedTab extends JPanel implements ActionListener {

	/**
	 * 
	 */

	private final JCheckBox fileSize;

	private final JComboBox<String> conditionList;

	private final JTextField size;

	private final JComboBox<String> measureList;

	public AdvancedTab() {
		super();

		fileSize = new JCheckBox("File size:");
		fileSize.addActionListener(this);
		Vector<String> conditions = new Vector<String>();
		conditions.add("Equal");
		conditions.add("Less");
		conditions.add("More");
		conditionList = new JComboBox<String>(conditions);
		conditionList.setEnabled(false);
		size = new JTextField(20);
		size.setEnabled(false);
		Vector<String> measures = new Vector<String>();
		measures.add("bytes");
		measures.add("kbytes");
		measures.add("mbytes");
		measureList = new JComboBox<String>(measures);
		measureList.setEnabled(false);

		defineLayout();
	}

	public boolean fileSizeIsSelected() {
		return fileSize.isSelected();
	}

	public String getCondition() {
		return conditionList.getItemAt(conditionList.getSelectedIndex());
	}

	public long getSearchedSize() {
		return Long.valueOf(size.getText()).longValue();
	}

	public String getMeasure() {
		return measureList.getItemAt(measureList.getSelectedIndex());
	}

	public void actionPerformed(ActionEvent event) {
		boolean enabled = fileSize.isSelected();
		measureList.setEnabled(enabled);
		size.setEnabled(enabled);
		conditionList.setEnabled(enabled);
	}

	private void defineLayout() {
		SpringLayout spring = new SpringLayout();
		setLayout(spring);

		add(fileSize);
		add(conditionList);
		add(size);
		add(measureList);

		spring.putConstraint(SpringLayout.NORTH, fileSize, 5,
				SpringLayout.NORTH, this);
		spring.putConstraint(SpringLayout.WEST, fileSize, 5, SpringLayout.WEST,
				this);

		spring.putConstraint(SpringLayout.NORTH, conditionList, 5,
				SpringLayout.NORTH, this);
		spring.putConstraint(SpringLayout.WEST, conditionList, 5,
				SpringLayout.EAST, fileSize);

		spring.putConstraint(SpringLayout.NORTH, size, 5, SpringLayout.NORTH,
				this);
		spring.putConstraint(SpringLayout.WEST, size, 5, SpringLayout.EAST,
				conditionList);

		spring.putConstraint(SpringLayout.NORTH, measureList, 5,
				SpringLayout.NORTH, this);
		spring.putConstraint(SpringLayout.WEST, measureList, 5,
				SpringLayout.EAST, size);
	}
}
