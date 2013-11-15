/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.dialogs.search;

import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.santiagolizardo.madcommander.MainGUI;
import com.santiagolizardo.madcommander.components.localized.LocalizedLabel;


public class GeneralTab extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8599436433134195796L;

	private JLabel labelSearchFor;

	private JTextField searchFor;

	private JLabel labelSearchIn;

	private JTextField searchIn;

	private JLabel labelRecursive;

	private JCheckBox recursive;

	public GeneralTab() {
		super();

		Dimension panelSize = new Dimension(400, 100);
		setMinimumSize(panelSize);
		setPreferredSize(panelSize);

		initComponents();
		defineLayout();
	}

	public String getSearchFor() {
		return searchFor.getText();
	}

	public String getSearchIn() {
		return searchIn.getText();
	}

	public boolean isRecursive() {
		return recursive.isSelected();
	}

	private void initComponents() {
		labelSearchFor = new LocalizedLabel("Search_for");
		searchFor = new JTextField(20);
		labelSearchIn = new LocalizedLabel("Search_in");
		searchIn = new JTextField(40);
		searchIn.setText(MainGUI.app.getSource().getPath());

		labelRecursive = new LocalizedLabel("Recursive");
		recursive = new JCheckBox();
		recursive.setSelected(true);
	}

	private void defineLayout() {
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		add(labelSearchFor);
		add(searchFor);
		add(labelSearchIn);
		add(searchIn);
		add(labelRecursive);
		add(recursive);

		// labels
		layout.putConstraint(SpringLayout.WEST, labelSearchFor, 5,
				SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, labelSearchFor, 5,
				SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, labelSearchIn, 5,
				SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, labelSearchIn, 15,
				SpringLayout.SOUTH, labelSearchFor);

		layout.putConstraint(SpringLayout.WEST, labelRecursive, 5,
				SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, labelRecursive, 15,
				SpringLayout.SOUTH, labelSearchIn);

		// controls
		layout.putConstraint(SpringLayout.WEST, searchFor, 5,
				SpringLayout.EAST, labelSearchFor);
		layout.putConstraint(SpringLayout.NORTH, searchFor, 5,
				SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, searchIn, 0, SpringLayout.WEST,
				searchFor);
		layout.putConstraint(SpringLayout.NORTH, searchIn, 0,
				SpringLayout.NORTH, labelSearchIn);

		layout.putConstraint(SpringLayout.WEST, recursive, 0,
				SpringLayout.WEST, searchFor);
		layout.putConstraint(SpringLayout.NORTH, recursive, 0,
				SpringLayout.NORTH, labelRecursive);
	}
}
