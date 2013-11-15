/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.dialogs.search;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class AdvancedTab extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2068980899563559757L;

	private JCheckBox fileSize;

	private JComboBox conditionList;

	private JTextField size;

	private JComboBox measureList;

	public AdvancedTab() {
		super();

		fileSize = new JCheckBox("File size:");
		fileSize.addActionListener(this);
		Vector<String> conditions = new Vector<String>();
		conditions.add("Equal");
		conditions.add("Less");
		conditions.add("More");
		conditionList = new JComboBox(conditions);
		conditionList.setEnabled(false);
		size = new JTextField(20);
		size.setEnabled(false);
		Vector<String> measures = new Vector<String>();
		measures.add("bytes");
		measures.add("kbytes");
		measures.add("mbytes");
		measureList = new JComboBox(measures);
		measureList.setEnabled(false);

		defineLayout();
	}

	public boolean fileSizeIsSelected() {
		return fileSize.isSelected();
	}

	public String getCondition() {
		return conditionList.getItemAt(conditionList.getSelectedIndex())
				.toString();
	}

	public long getSearchedSize() {
		return Long.valueOf(size.getText()).longValue();
	}

	public String getMeasure() {
		return measureList.getItemAt(measureList.getSelectedIndex()).toString();
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
