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

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.resources.languages.Translator;

import javax.swing.*;
import java.awt.*;


public class GeneralTab extends JPanel {

	/**
	 * 
	 */

	private JLabel labelSearchFor;

	private JTextField searchFor;

	private JLabel labelSearchIn;

	private JTextField searchIn;

	private JLabel labelRecursive;

	private JCheckBox recursive;

	private final MainWindow mainWindow;
	
	public GeneralTab(MainWindow mainWindow) {
		super();

		this.mainWindow = mainWindow;
		
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
		labelSearchFor = new JLabel(Translator.tr("Search for"));
		searchFor = new JTextField(20);
		labelSearchIn = new JLabel(Translator.tr("Search in"));
		searchIn = new JTextField(40);
		searchIn.setText(mainWindow.getSource().getPath());

		labelRecursive = new JLabel(Translator.tr("Recursive"));
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
